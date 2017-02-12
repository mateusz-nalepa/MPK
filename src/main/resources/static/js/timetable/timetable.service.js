angular.module('com.mpk.timetable').service('timetableService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance={};
			return{

				getTimetableByBusLineId: function (busLine) {
                    return Restangular.one('timetable/line', busLine).get();
                },

				saveTimetable: function(timetables, busLine) {
                    return Restangular.one("timetable/busLine", busLine).customPOST(timetables);
                },

				deleteTimetable: function (busLine) {
                    return Restangular.one("timetable/busLine", busLine).customDELETE();
                }


			};

		
		});