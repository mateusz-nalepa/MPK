package com.mpk.services.timetable;

import com.mpk.enums.Direction;

import java.sql.Time;
import java.util.List;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
public class TimetableAtBusStop {

    private long busStopId;
    private long busLineId;
    private List<Time> timetableAtWorkday;
    private List<Time> timetableAtSaturday;
    private List<Time> timetableAtSunday;
    private Direction direction;


    TimetableAtBusStop() {
    }

    public long getBusStopId() {
        return busStopId;
    }

    public void setBusStopId(long busStopId) {
        this.busStopId = busStopId;
    }

    public long getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(long busLineId) {
        this.busLineId = busLineId;
    }

    public List<Time> getTimetableAtWorkday() {
        return timetableAtWorkday;
    }

    public void setTimetableAtWorkday(List<Time> timetableAtWorkday) {
        this.timetableAtWorkday = timetableAtWorkday;
    }

    public List<Time> getTimetableAtSaturday() {
        return timetableAtSaturday;
    }

    public void setTimetableAtSaturday(List<Time> timetableAtSaturday) {
        this.timetableAtSaturday = timetableAtSaturday;
    }

    public List<Time> getTimetableAtSunday() {
        return timetableAtSunday;
    }

    public void setTimetableAtSunday(List<Time> timetableAtSunday) {
        this.timetableAtSunday = timetableAtSunday;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
//    @Override
//    public boolean equals(Object obj) {
//        if(obj == null || !(obj instanceof TimetableAtBusStop)){
//            return false;
//        }
//        TimetableAtBusStop t = (TimetableAtBusStop)obj;
//        return (t.busLineId == this.busLineId) && (t.busStopId == this.busStopId);
//    }
}
