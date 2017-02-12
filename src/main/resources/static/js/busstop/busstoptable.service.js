angular.module('com.mpk.busstoptable').service('busstoptableService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance={};
			return{
				
				addBusstop : function(bus){
					return Restangular.all('busstop').post(bus);
				},
				getBusstops : function(){
					console.log('getBusstops()');
					return Restangular.all('busstop').getList();
				},
				editBusstop : function(busstop){
					console.log('editBusstop()');
					return Restangular.one('busstop',busstop.id).customPUT(busstop);
				},
				showBusstopAdd : function(){
					modalInstance = $uibModal.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						template : '<busstopadd></busstopadd>'
					});
				},
				deleteBusstop : function(busstop){
					console.log('deleteBusstop()');
					return Restangular.one("busstop",busstop.id).customDELETE();				
				},
				closeBusstopAdd : function() {
					modalInstance.close();
					console.log('closeBusstopAdd()');
				}
			};

		
		});