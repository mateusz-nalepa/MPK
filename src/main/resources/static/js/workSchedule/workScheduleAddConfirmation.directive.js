angular.module('com.mpk.workschedule')
    .directive('workscheduleaddconfirmation', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/confirmation.html'
            // controller: 'WorkScheduleController',
            // controllerAs: 'WSCtrl'
        };
    });