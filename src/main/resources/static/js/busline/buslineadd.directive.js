angular.module('com.mpk.buslineadd', [])
.directive('buslineadd', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busline/buslineadd.html',
    controller: 'BusLineTableController',
    controllerAs: 'BusLineTableCtrl',
  };
});