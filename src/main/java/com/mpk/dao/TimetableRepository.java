package com.mpk.dao;

import com.mpk.entity.BusLine;
import com.mpk.entity.Timetable;
import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rower on 06.11.2016.
 */
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findByBusLineAndDirectionAndDayKind(BusLine busLine, Direction direction, DayKind dayKind);
}
