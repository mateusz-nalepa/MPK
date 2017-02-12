package com.mpk.services.timetable;

import com.mpk.entity.Timetable;
import com.mpk.enums.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.TreeSet;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
@Service
public class TimetableAtBusStopFlyweightImpl implements TimetableAtBusStopFlyweight {

    private HashMap<Long, HashMap<Long, TimetableAtBusStopContainer>> timetables;
    private TimetableAtBusStopFactory timetableAtBusStopFactory;

    @Autowired
    public TimetableAtBusStopFlyweightImpl(TimetableAtBusStopFactory timetableAtBusStopFactory) {
        this.timetables = new HashMap<>();
        this.timetableAtBusStopFactory = timetableAtBusStopFactory;
    }

    @Override
    public TimetableAtBusStop getTimetableAtBusStop(long lineId, long busStopId, Direction direction) {
        if(isCreated(lineId, busStopId, direction)){
            return timetables.get(lineId).get(busStopId).getTimetable(direction);
        }else{
            TimetableAtBusStop t = this.timetableAtBusStopFactory.getTimetable(lineId, busStopId, direction);
            addToFlyweight(t);
            return t;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.clearFlyweight();
    }

    private void addToFlyweight(TimetableAtBusStop t) {
        if(!timetables.containsKey(t.getBusLineId())){
            timetables.put(t.getBusLineId(), new HashMap<>());
        }
        if(!timetables.get(t.getBusLineId()).containsKey(t.getBusStopId())){
            timetables.get(t.getBusLineId()).put(t.getBusStopId(), new TimetableAtBusStopContainer());
        }
        timetables.get(t.getBusLineId()).get(t.getBusStopId()).setTimetable(t,t.getDirection());
    }

    private boolean isCreated(long lineId, long busStopId, Direction direction) {
        if(!timetables.containsKey(lineId)){
            return false;
        }
        HashMap<Long, TimetableAtBusStopContainer> containers = timetables.get(lineId);
        if(containers.containsKey(busStopId)){
            return containers.get(busStopId).getTimetable(direction) == null ? false : true;
        }
        return false;
    }

    private void clearFlyweight(){
        this.timetables = new HashMap<>();
    }
}
