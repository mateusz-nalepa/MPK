angular.module('com.mpk.drivers').controller(
		'DriversController',
		function($window, driversService, driversService) {
			var self = this;
			self.driver = {};
			self.userId = {};
			self.notDriverUsers = {};
			self.drivers = {};
			self.getNotDriverUsers = function() {
				driversService.getNotDriverUsers().then(function(response) {
					self.notDriverUsers = response;
				})
			}
			self.getDrivers = function() {
				driversService.getDrivers().then(function(response) {
					self.drivers = response;
				})
			}
			self.drivers = self.getDrivers();
			self.notDriverUsers = self.getNotDriverUsers();
			self.addDriver = function() {
				self.driver.userId = self.userId;
				driversService.addDriver(self.driver).then(function(response) {
					$window.location.reload();
				});
			}

			self.setDriverToEdit = function(driver) {
				driver.edit = true;
			}
			self.editDriver = function(driver) {
				driversService.editDriver(driver).then(function(response) {
					driver.edit = false;
				})
			}
			self.deleteDriver = function(driver) {
				driversService.deleteDriver(driver).then(function(response) {
					self.drivers=self.getDrivers();
					self.deleteCompleted = true;
				}, function(responseErr) {
					self.deleteCompleted = false;
				})
			}
			self.setStatus = function(driver, status) {
				driversService.setStatus(driver, status).then(
						function(respone) {
							self.getDrivers();
						})
			}
			self.showAddDriverModal = function() {
				driversService.showAddDriverModal();
			}
			self.closeAddDriverModal = function() {
				driversService.closeAddDriverModal();
			}
		});