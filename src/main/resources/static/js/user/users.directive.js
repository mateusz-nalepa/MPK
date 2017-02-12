angular.module('com.mpk.users', [])
.directive('users', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/user/users.html',
    controller: 'UsersController',
    controllerAs: 'UsersCtrl',
  };
});