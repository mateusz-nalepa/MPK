package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.BusLine;
import com.mpk.excepctions.BusLineNotFoundException;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusLineHelper;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Service
public class BusLineService  extends Observable{
    private BusLineRepository busLineRepository;

    @Autowired
    public BusLineService(BusLineRepository busLineRepository, TimetableAtBusStopFlyweight timetableAtBusStopFlyweight) {
        this.busLineRepository = busLineRepository;
        this.addObserver(timetableAtBusStopFlyweight);
    }

    public List<BusLineHelper> getAll(){
        List<BusLine> busLines = busLineRepository.findAll();
        List<BusLineHelper> lineHelpers = new ArrayList<>();
        for(BusLine b: busLines){
            lineHelpers.add(new BusLineHelper(b));
        }
        return lineHelpers;
    }

    public BusLineHelper findById(Long id) {
        BusLine busLine = busLineRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        return new BusLineHelper(busLine);
    }

    public void add(BusLineHelper busLine){
        BusLine b = new BusLine();
        b.setName(busLine.getName());
        b.setType(busLine.getType());
        busLineRepository.save(b);
        this.notifyObservers();
    }

    public void update(BusLineHelper busLineHelper) {
        BusLine busLine = busLineRepository.findOne(busLineHelper.getId());
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        busLine.setName(busLineHelper.getName());
        busLine.setType(busLineHelper.getType());
        busLineRepository.save(busLine);
        this.notifyObservers();
    }

    public void delete(Long id) {
        BusLine busLine = busLineRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        busLineRepository.delete(busLine);
        this.notifyObservers();
    }

}
