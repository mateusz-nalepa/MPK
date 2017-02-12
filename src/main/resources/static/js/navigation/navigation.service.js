angular
		.module('com.mpk.navigation')
		.service(
				'navigationService',
				function($uibModal, $rootScope, $http, $window, $location, $q, Restangular) {
					var modalInstance={};
					return {
						login : function(userName, password) {
							var config = {
								params : {
									login : userName,
									password : password
								}
							};
							$http
									.post(
											'authenticate',
											$.param(config.params),
											{
												headers : {
													"content-type" : "application/x-www-form-urlencoded"
												}
											})
									.then(
											function(data) {
												$rootScope.authenticationFailed = false;
												$rootScope.authenticated = true;
												$location.path('homepage');
												$window.location.reload();											},
											function(data) {
												$rootScope.authenticationFailed = true;
												$location.path('homepage');
											});
						},
						authenticate : function() {
							$http.get('principal').then(function(response) {
								if (response.data.authenticated) {
									$rootScope.authenticated = true;
									$rootScope.principal = response.data.principal.username;
								}
							});
						},
						logout : function() {
							$http.post('logout', {}).then(function() {
								$rootScope.authenticated = false;
								$location.path('homepage');
								$window.location.reload();	
							}, function() {
								$rootScope.authenticated = false;
								$location.path('homepage');
								$window.location.reload();	
							});
						},
						showRegistrationModal : function() {
							modalInstance = $uibModal.open({
								animation : true,
								ariaLabelledBy : 'modal-title',
								ariaDescribedBy : 'modal-body',
								template : '<registration></registration>'
							});
						},
						closeRegistrationModal : function() {
							modalInstance.close();
						},
						getRole : function(){
							return Restangular.one("principal").get();
						}
					};
				});