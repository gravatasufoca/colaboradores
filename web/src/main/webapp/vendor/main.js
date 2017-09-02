if (!appConfig) {
    console.log('Não foi definido um configurador da aplicação.');
    //return;
}
var sufixo = '.min';
var bust = (new Date()).getTime();
var angularVersion = "1.2.8";

requirejs.config({
    urlArgs: "bust=" + bust,
    paths: {
        'jQuery': ['../vendor/jquery/jquery' + sufixo],
        'angular': ['../vendor/angularjs/angular' + sufixo],
        'angularUiBootstrap': ['../vendor/angular-ui-bootstrap/ui-bootstrap-tpls' + sufixo],
        'restangular': ['../vendor/restangular/restangular'],
        'underscore': ['../vendor/underscore/underscore' + sufixo],
        'lodash': ['../vendor/lodash/lodash.core'],
        'angularUiRouter': ['../vendor/angular-ui-router/angular-ui-router' + sufixo],
        'componentes': ['../vendor/arquitetura/componentes'],
        'bootstrap': ['../vendor/arquitetura/bootstrap'],
        'domReady': ['../vendor/requirejs/domReady' + sufixo],
        'jQueryNoty': ['..//vendor/jquery-noty/jquery.noty.packaged' + sufixo],
        'jQueryNotyLayoutsTopCenter': ['..//vendor/jquery-noty/layouts/topCenter'],
        'jQueryNotyLayouts': ['..//vendor/jquery-noty/layouts'],
        'jQueryNotyThemesDefault': ['../vendor/jquery-noty/themes/default'],
        'controllers' : ['../vendor/arquitetura/controladores'],
        'utils' : ['../vendor/arquitetura/utils'],
        'pages':['../app/pages']


    },
    shim: {
        'angular': {
            deps: ['jQuery'],
            exports: 'angular'
        },
        'angularUiBootstrap': {
            deps: ['angular'],
            exports: 'angularUiBootstrap'
        }, 'restangular': {
            deps: ['angular', 'lodash'],
            exports: 'restangular'
        },
        'underscore': {
            exports: 'underscore'
        }, 'lodash': {
            exports: 'lodash'
        },
        'angularUiRouter': {
            deps: ['angular'],
            exports: 'angularUiRouter'
        },
        'jQueryNoty': {
            deps: ['jQuery'],
            exports: 'jQueryNoty'
        },
        'jQueryNotyLayoutsTopCenter': {
            deps: ['jQueryNoty'],
            exports: 'jQueryNotyLayoutsTopCenter'
        },
        'jQueryNotyThemesDefault': {
            deps: ['jQueryNoty'],
            exports: 'jQueryNotyThemesDefault'
        },

    }
    , priority: ["angular"]
    , deps: ['bootstrap']
});


requirejs.onError = function (err) {

    console.log(err);
    console.log(err.requireType);

};

