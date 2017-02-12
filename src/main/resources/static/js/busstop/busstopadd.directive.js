angular.module('com.mpk.busstopadd', [])
.directive('busstopadd', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busstop/busstopadd.html',
    controller: 'BusStopTableController',
    controllerAs: 'BusStopTableCtrl',
  };
});