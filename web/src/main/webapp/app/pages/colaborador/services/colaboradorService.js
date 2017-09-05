define(['msAppJs'],
    function (app) {
        app.factory("colaboradorService", ['resourceRest',
            '$rootScope',
            function (resourceRest,
                      $rootScope) {


                var salvar=function (colaborador) {
                    return resourceRest.colaboradores.post(colaborador);
                }

                var recuperarColaborador=function (id) {
                    return resourceRest.colaboradores.one("id",id).get();
                };

                var pesquisar=function (filtro) {
                    return resourceRest.colaboradores.one("consulta",filtro).getList();
                }



                return {
                    salvar:salvar,
                    recuperarColaborador:recuperarColaborador,
                    pesquisar:pesquisar
                };

            }]);

        return app;
    });