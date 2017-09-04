require(['msAppJs',
], function (app) {

    app.controller('manterController', [
        '$scope',
        '$rootScope',
        '$timeout',
        'colaboradorService',
        '$notifyService',
        "NgMap",
        "$stateParams",
        function ($scope,
                  $rootScope,
                  $timeout,
                  colaboradorService,
                  $notifyService,
                  NgMap,
                  $stateParams) {




            $scope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams, error) {
                if ($stateParams.id) {
                      colaboradorService.recuperarColaborador($stateParams.id);
                  }
                  $scope.tiposContatos = colaboradorService.recuperarTipoContatos();
                  $scope.cargos = colaboradorService.recuperarCargos();
                  $scope.unidades = colaboradorService.recuperarUnidades();
                  tipoCompetencias=colaboradorService.recuperarTiposCompetencias()
                  console.info($scope.tiposContatos)

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

            var tipoCompetencias=[];

            $scope.competencias = [];
            $scope.googlekey = "AIzaSyANNPnS32ki7cbp5JbfEPlWG-f9slrQMTQ";


            $scope.loadCompetencias = function (query) {
                return tipoCompetencias;
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
                    contato: null
                };
                $scope.colaborador.contatos.push(contato);
            }
            
            
            $scope.salvarColaborador=function () {
               if(validaObrigatorios()){

                   if(!window.geral.isEmpty($scope.competencias)){
                       $scope.colaborador.competencias=$scope.competencias;
                   }

                   colaboradorService.salvar($scope.colaborador) .then(function (data) {
                       $notifyService.close();
                       $scope.showMsg('S', data.mensagens[0].texto);
                       $state.go("colaborador");
                   }, function (e) {
                       $notifyService.close();
                       $scope.showMsg('E', e.data.mensagens[0].texto);
                   });
               }
            }

            var validaObrigatorios=function () {
                if(window.geral.isEmpty($scope.colaborador.nome)
                    || window.geral.isEmpty($scope.colaborador.cargo)){
                    $scope.showMsg('E', "Campos obrigat\u00F3rios não preechidos");
                    return false;
                }

                return true;

            }


        }]);


    return app;
});