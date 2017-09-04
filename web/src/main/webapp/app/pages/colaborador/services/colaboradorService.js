define(['msAppJs'],
    function (app) {
        app.factory("colaboradorService", ['resourceRest',
            '$rootScope',
            function (resourceRest,
                      $rootScope) {

                var mock={
                  colaboradores:[{
                      id: 1,
                      nome: "Bruno",
                      resumo: "Lorem ipsum, bala bla bla",
                      endereco: "Rua 9 Sul, 15 - Águas Claras, Brasília - DF",
                      cargo: {id:2,nome:"Arquiteto"},
                      unidade: {id:2,nome:"F\u00E1brica"},
                      competencias: [{id:1,nome:"jQuery"},{id:2,nome:"Java"}],
                      contatos: [{id:1,tipoContato:{id:1,nome:"Celular"},contato:"6188888888"},{id:1,tipoContato:{id:1,nome:"E-mail"},contato:"bruno@teste.com.br"}],
                  },{
                      id: 2,
                      nome: "Gleice",
                      resumo: "Lorem ipsum, bala bla bla",
                      endereco: "Rua 9 Sul, 15 - Águas Claras, Brasília - DF",
                      cargo: {id:2,nome:"Arquiteto"},
                      unidade: {id:2,nome:"F\u00E1brica"},
                      competencias: [{id:1,nome:"jQuery"},{id:2,nome:"Java"}],
                      contatos: [{id:1,tipoContato:{id:1,nome:"Celular"},contato:"6188888888"},{id:1,tipoContato:{id:1,nome:"E-mail"},contato:"bruno@teste.com.br"}],
                  }],
                    tiposContatos:[{id:1,nome:"Celular"},{id:2,nome:"E-mail"}],
                    cargos:[{id:1,nome:"Desenvolvedor"},{id:2,nome:"Arquiteto"}],
                    unidades:[{id:1,nome:"RH"},{id:2,nome:"F\u00E1brica"}],
                    tipoCompetencias: [{id:1,nome:"JQuery"},{id:2,nome:"Java"}]
                };

                var salvar=function (colaborador) {
                    return resourceRest.colaboradores.post(colaborador);
                }

                var recuperarColaborador=function (id) {
                    return _.find(mock.colaboradores,function (colaborador) {
                        return colaborador.id==id;
                    })
                };

                var pesquisar=function (filtro) {
                    return resourceRest.colaboradores.one("consulta",filtro).getList();
                }

                var recuperarTipoContatos=function () {
                  return mock.tiposContatos;
                };

                var recuperarCargos=function () {
                    return mock.cargos;
                };

                var recuperarUnidades=function () {
                    return mock.unidades;
                };

                var recuperarTiposCompetencias=function () {
                    return resourceRest.colaboradores.one("tipoCompetencias").getList();
                };

                return {
                    mock:mock,
                    salvar:salvar,
                    recuperarColaborador:recuperarColaborador,
                    recuperarTipoContatos:recuperarTipoContatos,
                    recuperarCargos:recuperarCargos,
                    recuperarUnidades:recuperarUnidades,
                    recuperarTiposCompetencias:recuperarTiposCompetencias,
                    pesquisar:pesquisar
                };

            }]);

        return app;
    });