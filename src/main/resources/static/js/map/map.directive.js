angular.module('com.mpk.map', [])
.directive('map', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/map.html',
  };
});
