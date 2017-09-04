define([
        'controllers/mController',
        "restangular",
        'angularSanitize',
        'componentes/route/route',
        'componentes/notify/services/notifyService',
        'componentes/notify/services/alertService',
        'componentes/route/services/routeService',
        'componentes/notify/directives/alert',
        'utils/functions',
        "ngtagsinput",
        'angularNgTable',
    'ngMap'
    ],
    function () {
        'use strict';

        /*
         * Create the module
         */
        console.info("app.js...........")
        var app = angular.module('mApp', ["appController","route",
             'restangular',
             'notify',
             "ngTagsInput",
            'ngTable',
            'ngMap',
            'ngSanitize'
        ]);

        app.run(function ($rootScope, routeService) {
            app.routeService = routeService;

        });

        return app;

    }
);
