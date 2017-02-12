(function() {
    "use strict";
    var module = angular.module('com.mpk', [
        'ngAnimate',
        'ui.router',
        'ui.bootstrap',
        'com.mpk.navigation',
        'com.mpk.registration',
        'com.mpk.homepage',
        'com.mpk.busadd',
        'restangular',
        'com.mpk.buslineadd',
        'com.mpk.busstopadd',
        'com.mpk.adminpanel',
        'com.mpk.users',
        'com.mpk.buses',
        'com.mpk.workschedule',
        'com.mpk.busstoptable',
        'com.mpk.drivers',
        'com.mpk.buslinetable',
        'com.mpk.map',
        'com.mpk.route',
        'com.mpk.timetable',
        'com.mpk.timetableAtBusStop',
        'dndLists'
    ]);
 // enable http5mode (remove # from url)
    module.config([ '$locationProvider', function($locationProvider) {
        $locationProvider.html5Mode({ enabled: true});
    } ]);
}());

