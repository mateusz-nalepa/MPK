angular.module('com.mpk.drivers')
.directive('driveraddform', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/driver/driverAddForm.html',
    controller: 'DriversController',
    controllerAs: 'DriversCtrl',
  };
});