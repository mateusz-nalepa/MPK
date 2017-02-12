angular.module('com.mpk.buslinetable').controller(
		'BusLineTableController',
		function($window,busLineTableService) {
			var self = this;
			self.busline = {};
			self.buslines = {};
			self.getBusLines = function(){
				busLineTableService.getBusLines().then(function(response){
					self.buslines = response;
				})
			}
			self.getBusLines();
			
			self.setBusLineToEdit = function(busline){
				busLineTableService.editBusLine(busline).then(function(response){
					busline.edit = true;
				})
			}
			self.editBusLine = function(busline){
				busLineTableService.editBusLine(busline).then(function(response){
					busline.edit = false;
				})
			}
			self.showBusLineAdd = function(){
					busLineTableService.showBusLineAdd();
				}
			self.closeBusLineAdd = function(){
				busLineTableService.closeBusLineAdd();
			}
			self.deleteBusLine = function(busline){
				busLineTableService.deleteBusLine(busline).then(function(response){
					self.getBusLines();
				})
			}
			self.addBusLine = function(){
					busLineTableService.addBusLine(self.busline).then(function(response){
						busLineTableService.closeBusLineAdd();
						$window.location.reload();
					});
			}
		});