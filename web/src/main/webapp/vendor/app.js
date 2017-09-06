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
        'angularUiBootstrap',
        'angularUiBootstrap2',
        'ngMap', 'angularConfirm','ngFileUpload','ngFileUploadShim'
    ],
    function () {
        'use strict';

        /*
         * Create the module
         */
        var app = angular.module('mApp', ["appController", "route",
            'restangular',
            'notify',
            "ngTagsInput",
            'ngTable',
            'ngMap',
            'ngSanitize', 'cp.ngConfirm','ui.bootstrap','ngFileUpload'
        ]);

        app.run(function ($rootScope, routeService) {
            app.routeService = routeService;

        });

        return app;

    }
);
