var appConfig = {
    appContextRoot:"colaboradores-web"
};


requirejs.config({
    paths: {
        'mainJs': ['../vendor/main'],
        'msAppJs': ['../vendor/app'],

    },
    shim: {
        'msAppJs': {
            deps: ['mainJs']
        }
    },
    deps: ['mainJs', 'msAppJs'],
});

require(['msAppJs','../app/mainController'
], function (app) {
    'use strict';


    return app;
});
