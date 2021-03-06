define([
        'componentes/seguranca/seguranca'
        ], 
		function(seguranca) {
		
		'use strict';
	    
		msSeguranca.provider('autenticacaoProvider', ['$cookies', function($cookies) {
                        
	            var userIsAuthenticated = false;

                    this.setUserAuthenticated = function(value){
                        userIsAuthenticated = value;
                    };

                    this.getUserAuthenticated = function(){
                        return userIsAuthenticated;
                    };
                    
                    this.$get = function() {
                        return this;
                    };

                }]);
		
		return seguranca;
		
});