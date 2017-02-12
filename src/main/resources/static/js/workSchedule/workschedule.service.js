angular.module('com.mpk.workschedule').service('workScheduleService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance={};
		return {
			getFreeBuses : function(rangeHours) {
				return Restangular.one('workschedule').post('freebuses',JSON.stringify(rangeHours));
			},
			getFreeDrivers : function(rangeHours) {
				return Restangular.one('workschedule').post('freedrivers',JSON.stringify(rangeHours));
			},
			addDriverAndBus : function(wsHelper) {
				return Restangular.all('workschedule').post(wsHelper);
			},
			getWorkSchedules : function() {
				return Restangular.all('workschedule').getList();
			},
			deleteWorkSchedule : function(ws){
				return Restangular.one("workschedule", ws.id).customDELETE();
			},
			};
		});