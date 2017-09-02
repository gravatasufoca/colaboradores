define([
        'angular'
    ],
    function () {
        'use strict';
        try {
            console.info("appController.js...........")
            return angular.module('appController', []);
        }
        catch (e) {
            $log.error(e);
        }

    });