angular.module('com.mpk.workschedule')
    .directive('workscheduleaddchoosehours', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/rangeHours.html'
            // controller: 'WorkScheduleController',
            // controllerAs: 'WSCtrl'
        };
    });