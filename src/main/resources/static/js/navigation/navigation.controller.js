angular.module('com.mpk.navigation').controller(
		'NavigationController',
		function($rootScope,navigationService) {
			var self = this;
			$rootScope.authenticated = false;
			$rootScope.authenticationFailed = false;
			self.role = {};
			self.logInForm = {};
			
			self.openRegistrationModal = function() {
				navigationService.showRegistrationModal();
			}
			self.logIn = function() {
				navigationService.login(self.logInForm.username,
						self.logInForm.password);
			};
			self.logOut = function() {
				navigationService.logout();
			};
			self.openAdminPanel = function(){
				document.getElementById("mySidenav").style.width = "150px";
			};
			self.closeAdminPanel = function(){
				document.getElementById("mySidenav").style.width = "0px";
			};
			self.getRole = function(){
				navigationService.getRole().then(function(response){
					if(response!=null){
						self.role = response.authorities[0].authority;
					}
				})
			}
			navigationService.authenticate();
			self.getRole();
			self.initMap = function(){
				setTimeout(delayMe,1000);
			}
		});