(function() {
	angular.module('com.mpk.test').controller('TestController',
			[ '$http', function($http) {
				var vm = this;
				this.getPrincipal = function() {
					return $http.get('principal').then(function(response) {
						vm.principal=response.data.name;
						$("#panel").fadeToggle("slow");
						return response.data.name;
					});
				}
				this.hallo = "Hello world";
			} ])
}());