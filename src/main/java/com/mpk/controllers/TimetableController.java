package com.mpk.controllers;

import com.mpk.entity.Timetable;
import com.mpk.enums.Direction;
import com.mpk.helpers.ABitBetterTimetableHelper;
import com.mpk.helpers.TimetableHelper;
import com.mpk.services.TimetableService;
import com.mpk.services.timetable.TimetableAtBusStop;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Piśkiewicz on 24.01.2017.
 */
@RestController
@RequestMapping("timetable")
public class TimetableController {
    private TimetableService timetableService;
    private TimetableAtBusStopFlyweight timetableAtBusStopFlyweight;

    @Autowired
    public TimetableController(TimetableService timetableService, TimetableAtBusStopFlyweight timetableAtBusStopFlyweight) {
        this.timetableService = timetableService;
        this.timetableAtBusStopFlyweight = timetableAtBusStopFlyweight;
    }


    @RequestMapping(method = RequestMethod.POST, value = "busLine/{busLineId}")
    public void create(@RequestBody ABitBetterTimetableHelper timetables, @PathVariable long busLineId) {
        timetableService.create(timetables, busLineId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "busLine/{busLineId}")
    public void delete(@PathVariable long busLineId) {
        timetableService.deleteByBusLine(busLineId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public void update(@PathVariable long id, @RequestBody Timetable timetable) {
        this.timetableService.edit(timetable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/line/{busLineId}")
    public ABitBetterTimetableHelper getTimetableByBusLineId(@PathVariable long busLineId){
        List<Timetable> timetables = timetableService.findByBusLineId(busLineId);
        List<TimetableHelper> straight = new ArrayList<>();
        List<TimetableHelper> reversed = new ArrayList<>();
        for (Timetable t: timetables) {
            if (t.getDirection() == Direction.Straight) {
                straight.add(new TimetableHelper(t));
            }else{
                reversed.add(new TimetableHelper(t));
            }
        }
        return new ABitBetterTimetableHelper(straight, reversed);
    }

    @RequestMapping(value = "line/{lineId}/busStop/{busStopId}")
    public ArrayList<TimetableAtBusStop> getTimetableAtBusStop(@PathVariable long lineId, @PathVariable long busStopId) {
        ArrayList<TimetableAtBusStop> timetableAtBusStops = new ArrayList<>();
        TimetableAtBusStop straight = this.timetableAtBusStopFlyweight.getTimetableAtBusStop(lineId, busStopId, Direction.Straight);
        TimetableAtBusStop reversed = this.timetableAtBusStopFlyweight.getTimetableAtBusStop(lineId, busStopId, Direction.Reversed);
        timetableAtBusStops.add(straight);
        timetableAtBusStops.add(reversed);
        return timetableAtBusStops;
    }

}



