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
        "$stateParams",
        function ($scope,
                  $rootScope,
                  $timeout,
                  colaboradorService,
                  apoioService,
                  $notifyService,
                  NgMap,
                  $stateParams) {


            $scope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams, error) {
                if ($stateParams.id) {
                      colaboradorService.recuperarColaborador($stateParams.id);
                  }
                  apoioService.recuperarCargos().then(function (resultado) {
                      $scope.cargos=resultado.resultado;
                  });
                  apoioService.recuperarUnidades().then(function (resultado) {
                      $scope.unidades=resultado.resultado;
                  });
                  apoioService.recuperarTiposCompetencias().then(function (resultado) {
                      tipoCompetencias=resultado.resultado;
                  });
                  apoioService.recuperarTiposContato().then(function (resultado) {
                      $scope.tiposContatos=resultado.resultado;
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

                    fixCompetencias();
                   colaboradorService.salvar($scope.colaborador) .then(function (data) {
                       $notifyService.close();
                       $scope.showMsg('S', data.mensagens[0].texto);
                       $state.go("colaborador");
                   }, function (e) {
                       $notifyService.close();
                       $scope.showMsg('E', e.data.mensagens[0].texto);
                   });
               }
            };

            var fixCompetencias=function () {
                $scope.colaborador.competencias=[];
                if(!window.geral.isEmpty($scope.competencias)){
                    angular.forEach($scope.competencias,function (value,key) {
                      $scope.colaborador.competencias.push({
                          id:null,
                          tipoCompetencia:value,
                          colaborador:null
                      });
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