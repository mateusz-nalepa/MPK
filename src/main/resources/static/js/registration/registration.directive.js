angular.module('com.mpk.registration', [])
.directive('registration', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/registration/registrationForm.html',
    controller: 'RegistrationController',
    controllerAs: 'RegistrationCtrl',
  };
});