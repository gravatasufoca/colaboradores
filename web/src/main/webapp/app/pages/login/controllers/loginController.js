define(['msAppJs'], function (app) {

    app.controller('loginController', ['ngTableParams',
        '$cookieStore',
        '$scope',
        'msAutenticacaoService',
        'msSegurancaService',
        '$rootScope',
        '$timeout',
        'msModalService',
        'loginService',
        '$translatePartialLoader',
        '$msNotifyService',
        "$state",
        function (ngTableParams,
                  $cookieStore,
                  $scope,
                  msAutenticacaoService,
                  msSegurancaService,
                  $rootScope,
                  $timeout,
                  msModalService,
                  loginService,
                  $translatePartialLoader,
                  $msNotifyService,
                  $state) {

            $translatePartialLoader.addPart('login');

            /**
             * Dados do login e senha
             */
            $scope.formLogin = {
                email: null,
                password: null
            };

            $scope.formLogin.email = "rodrigo.neves@ctis.com.br";
            $scope.formLogin.password = "rodrigo123";


            /**
             * Metodo que efetua o login do usuario com base nas informacoes inseridas no formulario
             */
            $scope.login = function () {
                if (isDadosAcessoValido()) {

                    $msNotifyService.loading();

                    msAutenticacaoService.autenticar($scope.formLogin.email, $scope.formLogin.password)
                        .then(function (msSegurancaService) {

                            msAutenticacaoService.recuperarDadosUsuario()
                                .then(function (usuario) {

                                    $msNotifyService.close();

                                    if (!window.geral.isEmpty(usuario.perfis) && usuario.perfis.length > 1) {
                                        $cookieStore.put('isUsuarioAutenticado', false);
                                        $scope.exibirPerfis(usuario);
                                    } else {
                                        if (usuario.perfil !== null && usuario.perfil.listaEsferas && usuario.perfil.listaEsferas.length > 1) {
                                            $cookieStore.put('isUsuarioAutenticado', false);
                                            $scope.exibirEsferas(usuario.perfil);
                                        } else {
                                            setUsuarioScope(usuario);
                                            redirecionarAcesso(true);
                                        }
                                    }
                                });
                        }, function (error) {
                            $msNotifyService.close();
                            $scope.showMsg('E', error.data.mensagens[0].texto);
                        });
                }
            };


            /**
             * Valida se os dados de acesso obrigatorios foram preenchidos
             */
            var isDadosAcessoValido = function () {
                if (window.geral.isEmpty($scope.formLogin.email)
                    || window.geral.isEmpty($scope.formLogin.password)) {
                    $scope.showMsg('E', "necessario-informar-campos-obrigatorios-login");
                    $scope.formLogin.mostrarMsgErro = true;
                    return false;
                } else if (!$scope.formLogin.email.isValidEmail()) {
                    $scope.showMsg('E', "email-invalido");
                    $scope.formLogin.mostrarMsgErro = true;
                    return false;
                }

                return true;
            };


            /**
             * Exibe uma modal com os perfis disponiveis para escolha do usuario
             */
            $scope.exibirPerfis = function (usuario) {
                msModalService.setConfig({
                    backdrop: true,
                    keyboard: false,
                    modalFade: true,
//				windowClass : 'modalWidth800',
                    template: null,
                    templateUrl: 'modalPerfil', //esta dentro de login.tpl.html
                    controller: ['$scope',
                        'ngTableParams',
                        '$modalInstance',
                        'loginService',
                        'perfis',
                        '$msNotifyService',
                        function ($scope,
                                  ngTableParams,
                                  $modalInstance,
                                  loginService,
                                  perfis,
                                  $msNotifyService) {

                            $scope.perfis = perfis;


                            /**
                             * Monta a tabela de perfis
                             */
                            $scope.tabelaPerfil = new ngTableParams({
                                page: 1,
                                count: 5
                            }, {
                                counts: [], // hides page sizes
                                total: perfis.length, // length of data
                                getData: function ($defer, params) {
                                    $defer.resolve(perfis.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                                }
                            });


                            /**
                             * Seleciona um perfil na tela e verifa se o mesmo tem esferas selecionaveis
                             */
                            $scope.selecionarPerfil = function (perfilAcesso) {
                                if (perfilAcesso.listaEsferas && (perfilAcesso.listaEsferas.length === 0 || perfilAcesso.listaEsferas.length === 1)) {
                                    $msNotifyService.loading();

                                    if (perfilAcesso.listaEsferas.length === 1) {
                                        perfilAcesso.esferaPerfil = perfilAcesso.listaEsferas[0];
                                    }

                                    loginService.selecionarPerfil(perfilAcesso)
                                        .then(function (resposta) {
                                            $msNotifyService.close();
                                            $modalInstance.close(resposta.resultado.usuario);
                                        });
                                } else {
                                    $modalInstance.close(perfilAcesso);
                                }
                            };

                        }],
                    resolve: {
                        perfis: function () {
                            return usuario.perfis;
                        }
                    }
                }).open();


                /**
                 * Apos selecionar o perfil, define os dados retornando da modal
                 */
                msModalService.modalInstance.result.then(function (resultado) {
                    if (resultado && resultado.listaEsferas) {
                        $scope.exibirEsferas(resultado); //mostra lista de esferas
                    } else {
                        setUsuarioScope(resultado);
                        redirecionarAcesso(true);
                    }
                });
            };


            /**
             * Exibe uma modal com as esferas do perfil que o usuario escolheu
             */
            $scope.exibirEsferas = function (perfilSelecionado) {
                msModalService.setConfig({
                    backdrop: true,
                    keyboard: false,
                    modalFade: true,
//				windowClass : 'modalWidth700',
                    template: null,
                    templateUrl: 'modalEsfera', //esta dentro de login.tpl.html
                    controller: ['$scope',
                        'ngTableParams',
                        '$modalInstance',
                        'loginService',
                        'perfilAcesso',
                        '$msNotifyService',
                        function ($scope,
                                  ngTableParams,
                                  $modalInstance,
                                  loginService,
                                  perfilAcesso,
                                  $msNotifyService) {

                            $scope.perfilAcesso = perfilAcesso;

                            /**
                             * Monta a tabela de perfis
                             */
                            $scope.tabelaEsfera = new ngTableParams({
                                page: 1,
                                count: 5
                            }, {
                                counts: [], // hides page sizes
                                total: perfilAcesso.listaEsferas.length, // length of data
                                getData: function ($defer, params) {
                                    $defer.resolve(perfilAcesso.listaEsferas.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                                }
                            });


                            /**
                             * Aciona o metodo que seleciona a efera clicada em tela
                             * Faz a autenticaçao e retorna o resultado para o controller principal
                             */
                            $scope.selecionarEsfera = function (esfera) {
                                $msNotifyService.loading();

                                var perfilAcessoRetorno = {
                                    perfil: perfilAcesso.perfil,
                                    esferaPerfil: esfera,
                                    listaEsferas: null
                                };

                                loginService.selecionarPerfil(perfilAcessoRetorno)
                                    .then(function (resposta) {
                                        $msNotifyService.close();
                                        $modalInstance.close(resposta.resultado.usuario);
                                    });
                            };
                        }],
                    resolve: {
                        perfilAcesso: function () {
                            return perfilSelecionado;
                        }
                    }
                }).open();


                /**
                 * Apos selecionar a esfera desejada, define os dados do usuario retornado da modal
                 */
                msModalService.modalInstance.result.then(function (usuario) {
                    setUsuarioScope(usuario);
                    redirecionarAcesso(true);
                });
            };


            /**
             * Privates
             */
            function setUsuarioScope(usuario) {
                msSegurancaService.setUsuario(usuario);
                msSegurancaService.setUsuarioAutenticado(true);
                $rootScope.usuarioAutenticado = usuario;
                $rootScope.perfilSelecionado = usuario.perfil !== undefined;
            }

            function redirecionarAcesso(atualizarMenu) {
                $state.go("inicio");
            }
        }]);


    return app;
});