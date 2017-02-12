package com.mpk.utils;

import com.mpk.services.RouteService;
import com.mpk.services.TimeHolder;
import com.mpk.services.TimeHolderImpl;
import com.mpk.services.TimetableService;
import com.mpk.services.timetable.TimetableAtBusStopFactory;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Bartosz Piśkiewicz on 30.01.2017.
 */
@Component
public class FlyweightInjection {

    private RouteService routeService;
    private TimetableService timetableService;
    private TimeHolderImpl timeHolder;
    private TimetableAtBusStopFlyweight timetableAtBusStopFlyweight;

    public FlyweightInjection(RouteService routeService, TimetableService timetableService, TimeHolderImpl timeHolder, TimetableAtBusStopFlyweight timetableAtBusStopFlyweight) {
        this.routeService = routeService;
        this.timetableService = timetableService;
        this.timeHolder = timeHolder;
        this.timetableAtBusStopFlyweight = timetableAtBusStopFlyweight;
    }

    @PostConstruct
    public void init(){
        this.routeService.addObserver(this.timetableAtBusStopFlyweight);
        this.timetableService.addObserver(this.timetableAtBusStopFlyweight);
        this.timeHolder.addObserver(this.timetableAtBusStopFlyweight);
    }

}
