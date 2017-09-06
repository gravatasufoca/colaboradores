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
                    return resourceRest.colaboradores.get(id);
                };

                var pesquisar=function (pagina,filtro) {
                    return resourceRest.colaboradores.one("pagina",pagina).one("consulta",filtro==null?"null":filtro).getList();
                }



                return {
                    salvar:salvar,
                    recuperarColaborador:recuperarColaborador,
                    pesquisar:pesquisar
                };

            }]);

        return app;
    });