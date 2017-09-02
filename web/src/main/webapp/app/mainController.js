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

        }]);

    return app;
});