angular.module('com.mpk.route').service('routeService',
		function(Restangular, $rootScope, $uibModal) {
			var modalInstance = {};
			return{
				getRoute : function(busLine){
					return Restangular.one('busline', busLine).one('routes').get();
				},
				
				deleteRoute : function (busLine) {
                    return Restangular.one('busline', busLine).one('routes').customDELETE();
                },

				save: function (busLineId, route) {
                    return Restangular.one('busline', busLineId).one('routes').customPOST(route);
                },
				
				update: function (busLineId, routeId, route) {
                    return Restangular.one('busline', busLineId).one('routes', routeId).customPUT(route);
                }

			};
		
		});