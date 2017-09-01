define([
        'angular',
        "restangular"
    ],
    function () {
        'use strict';

        /*
         * Create the module
         */
        console.info("app.js...........")
        var app = angular.module('msApp', [
             'restangular'
        ]);

        return app;

    }
);
