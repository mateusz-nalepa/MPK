angular.module('com.mpk.workschedule', [])
    .directive('workscheduleadd', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/workScheduleAdd.html',
            controller: 'WorkScheduleController',
            controllerAs: 'WSCtrl'
        };
    });