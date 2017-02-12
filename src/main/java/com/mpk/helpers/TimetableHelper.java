package com.mpk.helpers;

import java.util.Date;

import com.mpk.entity.Timetable;
import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Bartosz Piśkiewicz on 24.01.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableHelper {
    private Long id;
    private Direction direction;
    private DayKind dayKind;
    private Date departureTime;

    public TimetableHelper(Timetable timetable) {
        this.id = timetable.getId();
        this.direction = timetable.getDirection();
        this.dayKind = timetable.getDayKind() == null ? DayKind.Workday : timetable.getDayKind();
        this.departureTime = timetable.getDepartureTime();
    }
}
