angular.module('com.mpk.timetableAtBusStop', [])
.directive('timetableatbusstop', function() {
  return {
    restrict: 'E',
    templateUrl: "views/timetableAtBusStop/timetableAtBusStop.html",
    controller: 'TimetableAtBusStopController',
    controllerAs: 'Ctrl',
  };
});