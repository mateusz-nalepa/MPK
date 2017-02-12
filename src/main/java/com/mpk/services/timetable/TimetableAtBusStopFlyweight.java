package com.mpk.services.timetable;

import com.mpk.enums.Direction;

import java.util.Observer;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
public interface TimetableAtBusStopFlyweight extends Observer {

    TimetableAtBusStop getTimetableAtBusStop(long lineId, long busStopId, Direction direction);

}
