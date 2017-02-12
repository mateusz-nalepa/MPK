angular.module('com.mpk.busadd', [])
.directive('busadd', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/bus/busadd.html',
    controller: 'BusesController',
    controllerAs: 'BusesCtrl',
  };
});