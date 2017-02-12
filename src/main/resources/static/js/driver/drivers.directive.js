angular.module('com.mpk.drivers', [])
.directive('drivers', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/driver/drivers.html',
    controller: 'DriversController',
    controllerAs: 'DriversCtrl',
  };
});