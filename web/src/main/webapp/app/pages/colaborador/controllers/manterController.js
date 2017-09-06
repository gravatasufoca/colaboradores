require(['msAppJs',
], function (app) {

    app.controller('manterController', [
        '$scope',
        '$rootScope',
        '$timeout',
        'colaboradorService',
        'apoioService',
        '$notifyService',
        "NgMap",
        "$stateParams", "$ngConfirm", "$state",
        function ($scope,
                  $rootScope,
                  $timeout,
                  colaboradorService,
                  apoioService,
                  $notifyService,
                  NgMap,
                  $stateParams, $ngConfirm, $state) {


            $scope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams, error) {
                if ($stateParams.id) {
                    colaboradorService.recuperarColaborador($stateParams.id).then(function (resultado) {
                        $scope.colaborador = resultado.resultado;
                        montarCompetencias();
                    });
                }
                /*apoioService.recuperarCargos().then(function (resultado) {
                    $scope.cargos = resultado.resultado;
                });*/
                apoioService.recuperarUnidades().then(function (resultado) {
                    $scope.unidades = resultado.resultado;
                });
                apoioService.recuperarTiposCompetencias().then(function (resultado) {
                    tipoCompetencias = resultado.resultado;
                });
                apoioService.recuperarTiposContato().then(function (resultado) {
                    $scope.tiposContatos = resultado.resultado;
                });

            });

            $scope.tiposContatos = [];
            /**
             * Dados do login e senha
             */
            $scope.colaborador = {
                id: null,
                nome: null,
                resumo: null,
                endereco: null,
                cargo: null,
                unidade: null,
                competencias: [],
                contatos: [],
            };

            var tipoCompetencias = [];

            $scope.competencias = [];
            $scope.googlekey = "AIzaSyANNPnS32ki7cbp5JbfEPlWG-f9slrQMTQ";


            $scope.loadCompetencias = function (query) {
                return tipoCompetencias;
            }

            var montarCompetencias = function () {
                angular.forEach($scope.colaborador.competencias, function (value, key) {
                    $scope.competencias.push(value.tipoCompetencia);
                });
            }

            $scope.enderecoMudou = function () {
                $scope.place = this.getPlace();
                console.log('location', $scope.place.geometry.location);
                $scope.map.setCenter($scope.place.geometry.location);
            }
            NgMap.getMap().then(function (map) {
                $scope.map = map;
            });


            $scope.novoContato = function () {
                var contato = {
                    tipoContato: null,
                    contato: null,
                    excluido:false
                };
                $scope.colaborador.contatos.push(contato);
            }


            $scope.salvarColaborador = function () {
                if (validaObrigatorios()) {

                    fixCompetencias();
                    colaboradorService.salvar($scope.colaborador).then(function (data) {
                        $notifyService.close();
                        if (data.mensagemSucesso != null) {
                            $scope.showMsg('S', data.mensagemSucesso);
                        } else {
                            mostrarErros(data.mensagens);
                        }

                        $state.go("colaborador", {});
                    }, function (e) {
                        $notifyService.close();
                        $scope.showMsg('E', e.data.mensagens[0].texto);
                    });
                }
            };

            var mostrarMensagens = function (mensagens) {
                angular.forEach(mensagens, function (value) {
                    $scope.showMsg('E', value);
                });
            };

            var fixCompetencias = function () {
                $scope.colaborador.competencias = [];
                if (!window.geral.isEmpty($scope.competencias)) {
                    angular.forEach($scope.competencias, function (value, key) {
                        $scope.colaborador.competencias.push({
                            id: null,
                            tipoCompetencia: value,
                            colaborador: null
                        });
                    });
                }
            }

            var validaObrigatorios = function () {
                if (window.geral.isEmpty($scope.colaborador.nome)
                    || window.geral.isEmpty($scope.colaborador.cargo)) {
                    $scope.showMsg('E', "Campos obrigat\u00F3rios não preechidos");
                    return false;
                }

                return true;
            }

            $scope.apagarContato = function (contato) {
                $scope.contato = contato;
                $ngConfirm({
                    title: 'Confirmar',
                    content: '<strong>Confirma a exclus\u00E3o</strong>',
                    scope: $scope,
                    buttons: {
                        sim: {
                            text: 'Sim',
                            btnClass: 'btn-warning',
                            action: function (scope, button) {
                                console.info("contato", contato)
                                contato.excluido = true;
                                $scope.$apply();
                            }
                        },
                        nao: {
                            text: 'N\u00E3o',
                            btnClass: 'btn-default',
                            action: function (scope, button) {
                            }
                        }
                    }
                });
            }


            $scope.selecionaCargo=function () {

            };

            $scope.consultaCargo=function (nomeParcial) {
                if(nomeParcial && nomeParcial !== null){
                    nomeParcial = nomeParcial.toString();
                }

                if (nomeParcial !== ''){
                    return apoioService.recuperarCargos(nomeParcial)
                        .then(function (data){
                           return _.map(data.resultado,function (valor) {
                                valor.texto=valor.nome;
                                return valor;
                            });
                        });
                } else {
                    return [];
                }
            }

        }]);


    return app;
});