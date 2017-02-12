angular.module('com.mpk.timetableAtBusStop').controller(
		'TimetableAtBusStopController',
		function($window, busLineTableService, routeService,  timetableAtBusStopService, $scope) {
			$scope.busLineId;

			$scope.busLines = [];
			$scope.routes = [];
			$scope.timetables = {};

			$scope.pageLoad = function () {
				busLineTableService.getBusLines().then(function (response) {
					$scope.busLines = response;
                },function (errResponse) {
                    console.log(errResponse);
                })
            };
			$scope.pageLoad();
			
			$scope.selectLine = function (id) {
                $scope.busLineId = id;
				$scope.routes = [];
				$scope.timetables = {};
				routeService.getRoute(id).then(function (response) {
                    console.log(response);
                    $scope.routes = response;
                },function (errResponse) {
                    console.log(errResponse);
                })
            };

            $scope.selectRoute = function (id){
                console.log($scope.busLineId, id);
				timetableAtBusStopService.getTimetableAtBusStop($scope.busLineId, id).then(function (response) {
                    console.log(response);
                    $scope.timetables.straight = response[0];
                    $scope.timetables.reversed = response[1];
                },function (errResponse) {
                    console.log(errResponse);
                })
			};

		});