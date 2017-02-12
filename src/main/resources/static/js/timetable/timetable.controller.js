angular.module('com.mpk.timetable').controller(
		'TimetableController',
		function($window,timetableService, $scope, $stateParams) {
            $scope.busLine = $stateParams.busLine;
            $scope.timetables = {
            	straight: [],
				reversed: [],
			};

            $scope.pageLoad = function () {
				timetableService.getTimetableByBusLineId($scope.busLine).then(function (response) {
					if(response) {
                        console.log(response);
						$scope.timetables = response;
                    }
                }, function (errResponse) {
                    console.log(errResponse);
                });
                console.log("data fetched");
            };
			$scope.pageLoad();

			$scope.addTimetable = function () {
				var time = angular.copy($scope.newTime);
				if(time.direction == "Straight"){
                    $scope.timetables.straight.push(time);
				}else{
                    $scope.timetables.reversed.push(time);
				}
            };
			
			$scope.save = function () {
				timetableService.saveTimetable($scope.timetables, $scope.busLine).then(function (response) {
                    alert("ok");
                }, function (errResponse) {
                    console.log(errResponse);
                })
            };

			$scope.delete = function () {
				timetableService.deleteTimetable($scope.busLine).then(function (response) {
                    alert("ok");
                }, function (errResponse) {
                    console.log(errResponse);
                })
            };
			
		});