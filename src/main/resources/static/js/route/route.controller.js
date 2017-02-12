angular.module('com.mpk.route').controller(
		'RouteController',
		function($window, routeService, $stateParams, busstoptableService, $scope) {
			var self = this;
            $scope.route = {};

            $scope.models = {
                selected: null,
                busStops: [],
				busStopsInRoute: []
            };


			self.getRoute = function(){
				routeService.getRoute($stateParams.busLine).then(function(response){
                    console.log(response);
                    $scope.route = response;
                    $scope.models.busStopsInRoute = response.busStops;
				}, function (errResponse) {
					console.log("not found");
                })
			};
			
			self.getBusStops = function () {
                busstoptableService.getBusstops().then(function (response) {
                    $scope.models.busStops = response;
                }, function (errResponse) {
					console.log("not found");
                })
            };

			self.pageLoad = function () {
				self.getRoute();
				self.getBusStops();
            };
			self.pageLoad();

            $scope.deleteRoute = function () {
				routeService.deleteRoute($stateParams.busLine).then(function (response) {
					alert("ok");
                })
            };

			$scope.save = function () {
				var route = {
					id: '',
                    busStops: []
				};
				var n = $scope.models.busStopsInRoute.length;
				var busStops = $scope.models.busStopsInRoute;
				for(i=0; i<n; i++) {
					route.busStops.push({
					 	id:				busStops[i].id,
                    	name:			busStops[i].name,
						address:		busStops[i].address,
			   			location:		busStops[i].location,
				 		numberInRoute:	i+1,
					});
                }
				if($scope.route.id) {
					route.id = $scope.route.id;
					routeService.update($stateParams.busLine, route.id, route).then(function (response) {
                        alert("ok");
                    }, function (errResponse) {
						
                    });
                }else{
                    routeService.save($stateParams.busLine, route).then(function (response) {
                        alert("ok");
                    }, function (errResponse) {

                    });
				}
            };

            $scope.$watch('models', function(model) {
                $scope.modelAsJson = angular.toJson(model, true);
            }, true);
			

		});