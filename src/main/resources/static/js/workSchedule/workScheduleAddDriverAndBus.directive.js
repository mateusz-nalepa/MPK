angular.module('com.mpk.workschedule')
    .directive('workscheduleadddriverandbus', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/driverAndBus.html'
            // controller: 'WorkScheduleController',
            // controllerAs: 'WSCtrl'
        };
    });