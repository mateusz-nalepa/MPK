angular.module('com.mpk.buslinetable').service('busLineTableService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance = {};
			return{
				addBusLine : function(busLine){
					return Restangular.all('busline').post(busLine);
					
				},
				getBusLines : function(){
					console.log('getBusLine()');
					return Restangular.all('busline').getList();
				},
				editBusLine : function(busLine){
					console.log('editBusLine()');
					return Restangular.one('busline',busLine.id).customPUT(busLine);
				},
				showBusLineAdd : function(){
					console.log('showBusLineAdd()');
					modalInstance = $uibModal.open({
						animation : true,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						template: '<buslineadd></buslineadd>'
					})
				},
				deleteBusLine : function(busLine){
					console.log('deleteBusline()');
					return Restangular.one('busline',busLine.id).customDELETE();
				},
				closeBusLineAdd : function(){
					modalInstance.close();
					console.log('closeBusLineAdd()');
				}
			};
		
		});