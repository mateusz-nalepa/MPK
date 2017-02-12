angular.module('com.mpk.timetableAtBusStop').service('timetableAtBusStopService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance={};
			return{

				getTimetableAtBusStop: function (busLine, busStop) {
                    return Restangular.one('timetable/line', busLine).one('busStop', busStop).get();
                },


			};

		
		});