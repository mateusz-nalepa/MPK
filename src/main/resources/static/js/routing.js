(function() {
	"use strict";
	var module = angular.module('com.mpk');
	module
			.config(function($stateProvider, $urlRouterProvider,
					$locationProvider) {
				$locationProvider.html5Mode(true);
				$urlRouterProvider.otherwise('/homepage');
				$stateProvider
						.state('test2', {
							url : '/test2',
							template : '<registration></registration>'
						})
						.state('homepage', {
							url : '/homepage',
							template : '<homepage></homepage>'
						})
						.state('map',{
							url : '/map',
							template : '<map><map>'
						})
						.state('busadd', {
							url : '/admin/busadd',
							template : '<busadd></busadd>'
						})
						.state('buslineadd', {
							url : '/admin/buslineadd',
							template : '<buslineadd></buslineadd>'
						})
						.state('busstopadd', {
							url : '/admin/busstopadd',
							template : '<busstopadd></busstopadd>'
						})
						.state('users', {
							url : '/admin/users',
							template : '<users></users>'
						})
						.state('buses', {
							url : '/admin/buses',
							template : '<buses></buses>'
						})
						.state('workschedule', {
							url : '/admin/workschedule',
							template : '<workschedule></workschedule>'
						})
						.state('workscheduleadd', {
							url : '/admin/workscheduleadd',
							template : '<workscheduleadd></workscheduleadd>'
						})
						.state(
								'workscheduleadd.rangehours',
								{
									url : '/admin/rangehours',
									template : ' <workscheduleaddchoosehours></workscheduleaddchoosehours>'
								})
						.state(
								'workscheduleadd.driverandbus',
								{
									url : '/admin/driverandbus',
									template : ' <workscheduleadddriverandbus></workscheduleadddriverandbus>'
								})
						.state(
								'workscheduleadd.confirmation',
								{
									url : '/admin/confirmation',
									template : ' <workscheduleaddconfirmation></workscheduleaddconfirmation>'
								}).state('busstoptable', {
							url : '/admin/busstoptable',
							template : '<busstoptable></busstoptable>'
						}).state('drivers', {
							url : '/admin/drivers',
							template : '<drivers></drivers>'
						}).state('buslinetable', {
							url : '/admin/buslinetable',
							template : '<buslinetable></buslinetable>'
						}).state('timetable', {
							url : '/busLine/{busLine}/timetable',
							template : '<timetable></timetable>'
						}).state('timetableAtBusStop', {
							url : '/timetableAtBusStop',
							template : '<timetableatbusstop></timetableatbusstop>'
						}).state('route', {
							url : '/busLine/{busLine}/route',
							template : '<route></route>'
						}).state('forbidden', {
							url : '/403',
							templateUrl : 'views/403.html'
						})
			})
}());