package com.mpk.controllers;

import com.mpk.helpers.BusHelper;
import com.mpk.helpers.ChooseHoursHelper;
import com.mpk.helpers.DriverHelper;
import com.mpk.helpers.WorkScheduleHelper;
import com.mpk.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workschedule")
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @Autowired
    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @GetMapping
    public List<WorkScheduleHelper> findAll() {
        return workScheduleService.findAll();
    }


    @PostMapping
    public ResponseEntity<Void> add(@RequestBody WorkScheduleHelper workScheduleHelper) {
        return workScheduleService.add(workScheduleHelper);
    }

    @PostMapping("freebuses")
    public List<BusHelper> getFreeBuses(@RequestBody ChooseHoursHelper chooseHoursHelper) {
        return workScheduleService.freeBusesHelpers(chooseHoursHelper);
    }

    @PostMapping("freedrivers")
    public List<DriverHelper> getFreeDrivers(@RequestBody ChooseHoursHelper chooseHoursHelper) {
        return workScheduleService.freeDriversHelpers(chooseHoursHelper);
    }


    @DeleteMapping("{wsId}")
    public ResponseEntity<Void> delete(@PathVariable Long wsId) {
        return workScheduleService.delete(wsId);
    }


}
