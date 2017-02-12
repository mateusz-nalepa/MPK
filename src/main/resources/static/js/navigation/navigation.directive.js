angular.module('com.mpk.navigation', [])
.directive('navigation', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/navigation.html',
    controller: 'NavigationController',
    controllerAs: '$ctrl',
  };
});