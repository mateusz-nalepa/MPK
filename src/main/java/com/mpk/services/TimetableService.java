package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.dao.TimetableRepository;
import com.mpk.entity.BusLine;
import com.mpk.entity.Timetable;
import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import com.mpk.helpers.ABitBetterTimetableHelper;
import com.mpk.helpers.TimetableHelper;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
@Service
public class TimetableService extends Observable {

    private TimetableRepository timetableRepository;
    private BusLineRepository busLineRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository, BusLineRepository busLineRepository) {
        this.timetableRepository = timetableRepository;
        this.busLineRepository = busLineRepository;
    }

    public void create(Timetable timetable){
        timetableRepository.save(timetable);
        this.notifyObservers();
    }

    public void create(TimetableHelper timetableHelper) {

        this.notifyObservers();
    }

    public void edit(Timetable timetable){
        timetableRepository.save(timetable);
        this.notifyObservers();
    }

    public void delete(Timetable timetable){
        timetableRepository.delete(timetable);
        this.notifyObservers();
    }

    public List<Timetable> findByBusLineId(long busLineId) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        return busLine.getTimetables();
    }

    public List<Timetable> findAll(){
        return timetableRepository.findAll();
    }

    public Timetable findById(long id){
        return timetableRepository.findOne(id);
    }

    public List<Timetable> findByBusLineIdDirectionAndDayKind(long busLineId, Direction direction, DayKind dayKind){
        BusLine busLine = busLineRepository.findOne(busLineId);
//        Sort sort = new Sort(Sort.Direction.ASC, "departureTime");
        List<Timetable> byBusLineAndDirectionAndDayKind = this.timetableRepository.findByBusLineAndDirectionAndDayKind(busLine, direction, dayKind);
        return byBusLineAndDirectionAndDayKind;
    }

    public void create(ABitBetterTimetableHelper timetableHelpers, long busLineId) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ArrayList<Timetable> timetables = new ArrayList<>();
        for (TimetableHelper t: timetableHelpers.getStraight()) {
            Timetable timetable = new Timetable(t);
            timetable.setBusLine(busLine);
            timetable.setDayKind((t.getDayKind() == null ? DayKind.Workday : t.getDayKind() ));
            timetables.add(timetable);
        }
        for (TimetableHelper t: timetableHelpers.getReversed()) {
            Timetable timetable = new Timetable(t);
            timetable.setBusLine(busLine);
            timetable.setDayKind((t.getDayKind() == null ? DayKind.Workday : t.getDayKind() ));
            timetables.add(timetable);
        }
        busLine.setTimetables(timetables);
        busLineRepository.save(busLine);

    }

    public void deleteByBusLine(long busLineId) {
        BusLine busLine = this.busLineRepository.findOne(busLineId);
        Iterator<Timetable> iterator = busLine.getTimetables().iterator();
        while (iterator.hasNext()) {
            Timetable timetable = iterator.next();
            iterator.remove();
            timetable.setBusLine(null);
            timetableRepository.delete(timetable);
        }

    }
}
