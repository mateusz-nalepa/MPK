angular.module('com.mpk.users').service('usersService',
		function(Restangular, $rootScope, $uibModal) {
			return {
				getUsers : function() {
					return Restangular.all('user').getList();
				},
				editUser : function(user) {
					return Restangular.one("user", user.id).customPUT(user);
				},
				deleteUser : function(user){
					return Restangular.one("user", user.id).customDELETE();
				},
				setRole : function(user,role){
					user.role=role;
					return Restangular.one("user", user.id).customPUT(user);
				}
			};
		});