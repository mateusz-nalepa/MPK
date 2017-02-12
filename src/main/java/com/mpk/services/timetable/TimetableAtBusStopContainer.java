package com.mpk.services.timetable;

import com.mpk.enums.Direction;

import java.sql.Time;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
class TimetableAtBusStopContainer {

    private TimetableAtBusStop straight;
    private TimetableAtBusStop reversed;

    public TimetableAtBusStop getTimetable(Direction direction){
        if(direction == Direction.Straight){
            return straight;
        }else{
            return reversed;
        }
    }

    public void setTimetable(TimetableAtBusStop timetable, Direction direction){
        if(direction == Direction.Straight){
            straight = timetable;
        }else{
            reversed = timetable;
        }
    }
}
