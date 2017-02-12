angular.module('com.mpk.busstoptable').controller(
		'BusStopTableController',
		function($window,busstoptableService) {
			var self = this;
			self.busstop={};
			self.busstops={};
			self.getBusstops = function(){
				busstoptableService.getBusstops().then(function(response){
					self.busstops=response;
				})
			}
			self.getBusstops();
	
			self.setBusstopToEdit = function(busstop){
				busstop.edit = true;
			}
			self.editBusstop = function(busstop){
				busstoptableService.editBusstop(busstop).then(function(response){
					busstop.edit=false;
				})
			}
			self.showBusstopAdd = function(){
				busstoptableService.showBusstopAdd();
			}
			self.closeBusstopAdd = function(){
				busstoptableService.closeBusstopAdd();
			}
			self.deleteBusstop = function(busstop){
				busstoptableService.deleteBusstop(busstop).then(function(response){
					self.getBusstops();
				})
			}
			self.addBusstop = function(){			
				busstoptableService.addBusstop(self.busstop).then(function(response){
					busstoptableService.closeBusstopAdd();
					$window.location.reload();
				});
			}

		});