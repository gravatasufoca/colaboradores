define(['msAppJs'], function (app) {

    app.controller('consultaController', [
        '$scope',
        '$rootScope',
        '$timeout',
        'colaboradorService',
        '$notifyService',
        "$state", "NgTableParams", "$q",
        function ($scope,
                  $rootScope,
                  $timeout,
                  colaboradorService,
                  $notifyService,
                  $state, NgTableParams, $q) {


            $scope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams, error) {

            });

            $scope.pesquisa={}

            $scope.consultar=function () {
                if(window.geral.isEmpty($scope.pesquisa.criterio)){
                    $scope.showMsg('E', "Crit\u00E9rio de pesquisa n\u00E3o informado");
                }else{
                    $scope.tabela.reload();
                }
            }

            $scope.tabela = new NgTableParams({count:10}, {
                getData: function (params) {
                    $notifyService.loading();
                    return colaboradorService.pesquisar($scope.pesquisa.criterio).then(function (resultado) {
                        console.info("rsulado",resultado);
                        params.total(resultado.resultado.length);
                        $notifyService.close();
                        return resultado.resultado;
                    });
                },
                counts: [], // hide page counts control
                total: 1,  // value less than count hide pagination
            });

            // $scope.tabela.reload();


        }]);


    return app;
});