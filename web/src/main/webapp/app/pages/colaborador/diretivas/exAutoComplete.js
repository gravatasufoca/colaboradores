define(['msAppJs'], function (app) {
    'use strict';

    app.directive('exAutoComplete',['$timeout',
        function($timeout){

            function link(scope,element, a){
                /**
                 * Completa o nome da Instituicao de Monitoramento a medida em que o usuario vai digitando
                 */
                scope.completar = scope.consultaFn;

                /***
                 * Verifica se uma localidade foi selecionada ao perder o foco do campo,
                 * caso nao tenha sido selecionada da lista, resetar o valor
                 */
                scope.verificaSelecao = function () {
                    $timeout(function () {
                        if (!scope.valor || !scope.valor.id) {
                            scope.valor = null;
                            change();
                        }
                    }, 100);
                };

                /**
                 * Funcao executada quando um item é selecionado na lista
                 */
                scope.selecionar = function ($item, $model, $label, $event) {
                    scope.valor = $item;
                };


                /**
                 * Transforma de vazio para null
                 */
                scope.$watch('valor', function name() {
                    if (!scope.valor || scope.valor === null || scope.valor === '') {
                        scope.valor = null;
                        change();
                    }
                });

                /**
                 * Metodo chamado ao alterar uma localidade
                 */
                var change = function () {
                    if (scope.onChangeFn) {
                        scope.onChangeFn(scope.valor);
                    }
                };



            }
            return {
                restrict: 'E',
                replace: true,
                link: link,
                template: function (e, a) {
                    var id = Math.random().toString(36).substring(10);

                    var template =
                        '<div class="form-group has-feedback "> ' +
                        '	<input id="' + id + '" type="text" ' +
                        '		ng-model="valor" maxlength="250"' +
                        '		placeholder="{{placeHolder}}"  ' +
                        '		typeahead-wait-ms="200"  ' +
                        '		typeahead-min-length="3"  ' +
                        '		typeahead-editable="true"  ' +
                        '		typeahead-on-select="verificaSelecao()" ' +
                        '		uib-typeahead="obj as (obj.texto) for obj in completar($viewValue)"  ' +
                        '		typeahead-on-select="selecionar($item)"  ' +
                        '		class="form-control"> ' +
                        '</div> ';

                    return template;
                },
                scope: {
                    valor: "=",
                    placeHolder:"=",
                    onChangeFn: "=",
                    consultaFn: "=",
                    obrigatorio: "="
                }
            };
        }])
    return app;
});