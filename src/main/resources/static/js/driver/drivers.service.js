angular.module('com.mpk.drivers').service('driversService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance = {};
			return {
				getNotDriverUsers : function() {
					return Restangular.all('user/notDriver').getList();
				},
				getDrivers : function() {
					return Restangular.all('driver').getList();
				},
				addDriver : function(driver) {
					return Restangular.all('driver').post(driver);
				},
				editDriver : function(driver) {
					return Restangular.one("driver", driver.id).customPUT(driver);
				},
				deleteDriver : function(driver) {
					return Restangular.one("driver", driver.id).customDELETE();
				},
				setStatus : function(driver, status) {
					driver.status = status;
					return Restangular.one("driver", driver.id).customPUT(driver);
				},
				showAddDriverModal : function() {
					modalInstance = $uibModal.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						template : '<driverAddForm></driverAddForm>'
					});
				},
				closeAddDriverModal : function() {
					modalInstance.close();
				},
			};
		});