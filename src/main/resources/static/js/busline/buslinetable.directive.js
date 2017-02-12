angular.module('com.mpk.buslinetable', [])
.directive('buslinetable', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busline/buslinetable.html',
    controller: 'BusLineTableController',
    controllerAs: 'BusLineTableCtrl',
  };
});