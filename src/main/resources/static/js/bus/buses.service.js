angular.module('com.mpk.buses').service('busesService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance={};
		return {
				addBus : function(bus) {
					return Restangular.all('bus').post(bus);
				},
				getBuses : function() {
					return Restangular.all('bus').getList();
				},
				editBus : function(bus) {
					return Restangular.one("bus", bus.id).customPUT(bus);
				},
				deleteBus : function(bus){
					return Restangular.one("bus", bus.id).customDELETE();
				},
				setStatus : function(bus,status){
					bus.status=status;
					return Restangular.one("bus", bus.id).customPUT(bus);
				},
				showBusAddModal : function() {
					modalInstance = $uibModal.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						template : '<busadd></busadd>'
					});
				},
				closeBusAddModal : function() {
					modalInstance.close();
				}
			};
		});