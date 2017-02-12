angular.module('com.mpk.registration').controller(
		'RegistrationController',
		function($window,registrationService,navigationService) {
			var self = this;
			self.user={};
			self.sameLoginInUse=false;
			self.sameEmailInUse=false;
			self.register = function(){
				self.sameLoginInUse=false;
				self.sameEmailInUse=false;
				registrationService.register(self.user).then(function(response){
					navigationService.closeRegistrationModal();
					$window.location.reload();
				},
				function(response){
					if(response.status == 405){
						self.sameEmailInUse=true;
					}
					if(response.status == 406){
						self.sameLoginInUse=true;
					}
				});
			}
			self.closeRegistrationModal = function(){
				navigationService.closeRegistrationModal();
			}
			self.initMap = function(){
				delayMe();
				console.log("init works");
			}
		});