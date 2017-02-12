angular.module('com.mpk.workschedule').controller(
    'WorkScheduleController',
    function ($window, workScheduleService, $state, $timeout) {//}, $scope) {
        var self = this;
        self.workBegin = {};
        self.workEnd = {};
        self.rangeHours = {};
        self.freeBuses = {};
        self.freeDrivers = {};
        self.wsHelper = {};
        self.status = "When you're done adding work schedule, you'll get here status.";
        self.chooseHours = function () {
            self.rangeHours.workBegin = [
                self.workBegin.getFullYear(),
                self.workBegin.getMonth() + 1,
                self.workBegin.getDate(),
                self.workBegin.getHours(),
                self.workBegin.getMinutes()
            ];
            self.rangeHours.workEnd = [
                self.workEnd.getFullYear(),
                self.workEnd.getMonth() + 1,
                self.workEnd.getDate(),
                self.workEnd.getHours(),
                self.workEnd.getMinutes()
            ];

            workScheduleService.getFreeBuses(self.rangeHours).then(
                function (data) {
                    self.freeBuses = data.plain();
                },
                function (data) {
                    //error handling
                }
            );
            workScheduleService.getFreeDrivers(self.rangeHours).then(
                function (data) {
                    self.freeDrivers = data.plain();
                },
                function (data) {
                    //error handling
                }
            );
            $state.go('workscheduleadd.driverandbus')
        };

        self.addDriverAndBus = function () {
            self.wsHelper.driverId = self.driverId;
            self.wsHelper.busId = self.busId;
            self.wsHelper.workBegin = self.rangeHours.workBegin;
            self.wsHelper.workEnd = self.rangeHours.workEnd;
            console.log("jestem kurwa spierdolonym rozkladem");
            workScheduleService.addDriverAndBus(self.wsHelper).then(function (response) {
                    self.status = "Successfully added a new entry to work schedule!";
                },
                function (response) {
                    if (response.status == 409) {
                        console.log("jakis spierdolony error wystapil");
                        self.status = "An error has occured during adding work schedule!";
                    }
                    if (response.status == 500) {
                        console.log("nie wszystkie pola sa uzupelnione");
                        self.status = "Not all fields are completed! Please Try again";
                    }
                });
            $state.go('workscheduleadd.confirmation');
            $timeout(function () {
                $state.go('workschedule');
            }, 3000);
        };

        self.workSchedules = {};
        self.getWorkSchedules = function () {
            workScheduleService.getWorkSchedules().then(function (response) {
                self.workSchedules = response;
            })
        };
        self.getWorkSchedules();

        self.deleteWorkSchedule = function (ws) {
            workScheduleService.deleteWorkSchedule(ws).then(function (response) {
            	self.getWorkSchedules();
            })
        };
    });