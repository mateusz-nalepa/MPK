angular.module('com.mpk.buses', [])
.directive('buses', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/bus/buses.html',
    controller: 'BusesController',
    controllerAs: 'BusesCtrl',
  };
});