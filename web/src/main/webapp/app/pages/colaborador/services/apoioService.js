define(['msAppJs'],
    function (app) {
        app.factory("apoioService", ['resourceRest',
            '$rootScope',
            function (resourceRest,
                      $rootScope) {


                var recuperarTiposCompetencias=function () {
                    return resourceRest.apoio.one("tipoCompetencias").getList();
                };

                var recuperarTiposContato=function () {
                    return resourceRest.apoio.one("tipoContatos").getList();
                };

                var recuperarCargos=function () {
                    return resourceRest.apoio.one("cargos").getList();
                };

                var recuperarUnidades=function () {
                    return resourceRest.apoio.one("unidades").getList();
                };

                return {
                    recuperarTiposCompetencias:recuperarTiposCompetencias,
                    recuperarTiposContato:recuperarTiposContato,
                    recuperarUnidades:recuperarUnidades,
                    recuperarCargos:recuperarCargos
                };

            }]);

        return app;
    });