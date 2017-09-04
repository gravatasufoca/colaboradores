/**
 * Created by bruno on 31/08/17.
 */
define(['msAppJs',
    'pages/colaborador/routes/colaboradorRoute',
    'pages/colaborador/colaborador',
    'componentes/../resourceRest'

], function (app,colaboradorRoute) {
    'use strict';

    /**
     * Controller da aplicação
     */
    console.info("mainController.......")
    app.controller('mainController', ['$scope',
        "$timeout",
        '$filter',
        '$state',
        '$rootScope',
        '$http',
        'routeService',
        function ($scope,
                  $timeout,
                  $filter,
                  $state,
                  $rootScope,
                  $http,
                  routeService) {


            routeService.create(colaboradorRoute);



            /**
             * Faz um wrapper para exbir mensagens na tela
             */
            var timeout = null;

            if (!$rootScope.showMsg) {
                $rootScope.showMsg = function (tipo, msg, manterAntigas) {
                    var op = "";
                    var icon = "";

                    var removeMsgs = function (tempoEmMillis) {
                        if (tempoEmMillis == null) {
                            $scope.$alert.clear();
                        } else {
                            $timeout.cancel(timeout);
                            timeout = $timeout(function () {
                                $scope.$alert.clear();
                            }, tempoEmMillis);
                        }
                    };

                    if (tipo === "E" || tipo === "e") {
                        op = "error";
                        icon = "<i class=\"font15px fa fa-times-circle\"></i>";
                    } else if (tipo === "S" || tipo === "s") {
                        op = "success";
                        icon = "<i class=\"font15px fa fa-check\"></i>";
                    } else if (tipo === "W" || tipo === "w" || tipo == "A" || tipo == "a") { //warning/alert
                        op = "warning";
                        icon = "<i class=\"font15px fa fa-exclamation-triangle\"></i>";
                    } else if (tipo === "I" || tipo === "i") {
                        op = "info";
                        icon = "<i class=\"font15px fa fa-info-circle\"></i>";
                    }

                    $timeout(function () {
                        msg = msg.replaceAll("'", "\'");

                        try {

                            if (window.geral.isEmpty(manterAntigas) || manterAntigas === false) {
                                removeMsgs();
                            }

                            eval("$scope.$alert." + op + "('" + icon + " " + msg + "');");
                        } catch (e) {
                            $scope.$alert.error(msg + "");
                        }

                        removeMsgs(20000);
                        $('#topoAbsoluto').scrollTop('#topoAbsoluto'); //rola para o topo da tela
                    }, 100);
                };
            }

        }]);

    return app;
});