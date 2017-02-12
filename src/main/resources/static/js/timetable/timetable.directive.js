angular.module('com.mpk.timetable', [])
.directive('timetable', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/timetable/timetable.html',
    controller: 'TimetableController',
    controllerAs: 'Ctrl',
  };
});