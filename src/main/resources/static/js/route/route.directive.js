angular.module('com.mpk.route', [])
.directive('route', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/route/route.html',
    controller: 'RouteController',
    controllerAs: 'ctrl',
  };
});