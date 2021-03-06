var appConfig = {
    appContextRoot:"colaboradores",
    login:{
        url:"api/oauth/login",
        url_usuario:"api/oauth/token"
    },
    logout: {
        url: 'api/oauth/logout'
    },
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
