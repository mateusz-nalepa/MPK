angular.module('com.mpk.adminpanel', [])
.directive('adminpanel', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/adminPanel/adminPanel.html',
  };
});