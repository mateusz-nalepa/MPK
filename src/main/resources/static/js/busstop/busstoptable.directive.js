angular.module('com.mpk.busstoptable', [])
.directive('busstoptable', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busstop/busstoptable.html',
    controller: 'BusStopTableController',
    controllerAs: 'BusStopTableCtrl',
  };
});