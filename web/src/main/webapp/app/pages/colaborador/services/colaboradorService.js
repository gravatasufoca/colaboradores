define(['msAppJs'],
    function (app) {
        app.factory("colaboradorService", ['resourceRest',
            '$rootScope',"$http",
            function (resourceRest,
                      $rootScope,$http) {


                var salvar=function (colaborador,imagem) {
                    // return resourceRest.colaboradores.post(colaborador);
                    return $http({
                        method: 'POST',
                        url: "./api/colaborador",
                        headers: {'Content-Type': undefined},
                        transformRequest: function (data) {
                            var formData = new FormData();
                            formData.append('colaborador', new Blob([JSON.stringify(data.colaborador)], {type: "application/json"}));
                            formData.append("imagem", data.imagem);

                            return formData;
                        },
                        data: {colaborador: colaborador, imagem: imagem}
                    });
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