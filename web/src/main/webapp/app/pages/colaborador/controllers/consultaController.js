define(['msAppJs'], function (app) {

    app.controller('consultaController', [
        '$scope',
        '$rootScope',
        '$timeout',
        'colaboradorService',
        '$notifyService',
        "$state",
        function ($scope,
                  $rootScope,
                  $timeout,
                  colaboradorService,
                  $notifyService,
                  $state) {


            /**
             * Dados do login e senha
             */
            $scope.formLogin = {
                email: null,
                password: null
            };

            $scope.formLogin.email = "rodrigo.neves@ctis.com.br";
            $scope.formLogin.password = "rodrigo123";



        }]);


    return app;
});