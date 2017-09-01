
if(!appConfig) {
	console.log('Não foi definido um configurador da aplicação.');
	//return;
}
var sufixo = '.min';
var bust = (new Date()).getTime();
var angularVersion="1.2.8";

requirejs.config({
	urlArgs: "bust=" + bust,
	paths: {
		'jQuery': ['../vendor/jquery/jquery'+ sufixo],
		'angular': ['../vendor/angularjs/angular'+ sufixo],
        'angularUiBootstrap': ['../vendor/angular-ui-bootstrap/ui-bootstrap-tpls' + sufixo],
        'restangular':['../vendor/restangular/restangular' ],
        'underscore':['../vendor/underscore/underscore' + sufixo],
        'lodash':['../vendor/lodash/lodash.core'],


    },
	shim: {
		'angular': {
			deps: ['jQuery'],
			exports: 'angular'
		},
		'angularUiBootstrap': {
			deps: ['angular'],
			exports: 'angularUiBootstrap'
		},'restangular': {
            deps: ['angular','lodash'],
            exports: 'restangular'
        },
        'underscore': {
            exports: 'underscore'
        }, 'lodash': {
            exports: 'lodash'
        },

	}
	,priority: ["angular"]
	// ,deps: ['bootstrap']
});


requirejs.onError = function (err) {
    /*
        err has the same info as the errback callback:
        err.requireType & err.requireModules
    */
    console.log(err);
    console.log(err.requireType);
    // Be sure to rethrow if you don't want to
    // blindly swallow exceptions here!!!
};

