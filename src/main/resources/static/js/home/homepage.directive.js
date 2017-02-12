angular.module('com.mpk.homepage', [])
.directive('homepage', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/homepage.html',
  };
});
