angular.module('com.mpk.workschedule')
    .directive('workschedule', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/workSchedules.html',
            controller: 'WorkScheduleController',
            controllerAs: 'WSCtrl'
        };
    });