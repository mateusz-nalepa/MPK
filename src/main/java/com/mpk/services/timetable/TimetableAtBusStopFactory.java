package com.mpk.services.timetable;

import com.mpk.entity.BusLine;
import com.mpk.entity.Timetable;
import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import com.mpk.helpers.BusStopInRouteHelper;
import com.mpk.helpers.RouteHelper;
import com.mpk.services.BusLineService;
import com.mpk.services.RouteService;
import com.mpk.services.TimeHolder;
import com.mpk.services.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
@Service
public class TimetableAtBusStopFactory {

    private RouteService routeService;
    private TimetableService timetableService;
    private TimeHolder timeHolder;

    @Autowired
    public TimetableAtBusStopFactory(RouteService routeService, TimetableService timetableService, TimeHolder timeHolder) {
        this.routeService = routeService;
        this.timetableService = timetableService;
        this.timeHolder = timeHolder;
    }

    public TimetableAtBusStop getTimetable(long busLineId, long busStopId, Direction direction){
        RouteHelper route = routeService.findByBusLine(busLineId);
        int interval = this.calculateInterval(route.getBusStops(), busStopId, direction);
        TimetableAtBusStop timetableAtBusStop = new TimetableAtBusStop();
        timetableAtBusStop.setBusStopId(busStopId);
        timetableAtBusStop.setBusLineId(busLineId);
        timetableAtBusStop.setDirection(direction);

        this.createTimetables(timetableAtBusStop, interval);

        return timetableAtBusStop;
    }

    private void createTimetables(TimetableAtBusStop timetableAtBusStop, int interval) {
        List<Time> workdayTimes = createTimetable(timetableAtBusStop.getBusLineId(), timetableAtBusStop.getDirection(), DayKind.Workday, interval);
        List<Time> saturdayTimes = createTimetable(timetableAtBusStop.getBusLineId(), timetableAtBusStop.getDirection(), DayKind.Saturday, interval);
        List<Time> sundayTimes = createTimetable(timetableAtBusStop.getBusLineId(), timetableAtBusStop.getDirection(), DayKind.Sunday, interval);

        timetableAtBusStop.setTimetableAtWorkday(workdayTimes);
        timetableAtBusStop.setTimetableAtSaturday(saturdayTimes);
        timetableAtBusStop.setTimetableAtSunday(sundayTimes);
    }

    private List<Time> createTimetable(long busLineId, Direction direction, DayKind dayKind, int interval){
        List<Timetable> timetables = timetableService.findByBusLineIdDirectionAndDayKind(busLineId, direction, dayKind);
        List<Time> times = new ArrayList<>();
        for (Timetable t: timetables) {
            times.add(new Time(t.getDepartureTime().getTime()+interval*60000 ));
        }
        return times;
    }

    private int calculateInterval(List<BusStopInRouteHelper> busStops, long endBusStopId, Direction direction){
        int interval = 0;
        if(direction == Direction.Reversed){
            Collections.reverse(busStops);
        }
        Iterator<BusStopInRouteHelper> iterator = busStops.iterator();
        BusStopInRouteHelper busStopOne = iterator.next();
        BusStopInRouteHelper busStopTwo;
        while(iterator.hasNext()){
            busStopTwo = iterator.next();
            interval += timeHolder.getTimeDiff(busStopOne.getId(), busStopTwo.getId());
            if(busStopTwo.getId().equals(endBusStopId)){
                break;
            }
            busStopOne = busStopTwo;
        }
        return interval;
    }
}
