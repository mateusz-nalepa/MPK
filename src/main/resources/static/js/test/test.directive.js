angular.module('com.mpk.test', [])
.directive('test', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/test.html',
	controller: 'TestController',
	controllerAs: 'TestController',
  };
});