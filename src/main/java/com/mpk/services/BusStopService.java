package com.mpk.services;

import com.mpk.dao.BusStopRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.BusStop;
import com.mpk.excepctions.BusStopNotFoundException;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusStopHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusStopService {

    private BusStopRepository busStopRepository;

    @Autowired
    public BusStopService(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    public List<BusStopHelper> findAll(){
        return convertToHelper(busStopRepository.findAll());
    }

    public BusStopHelper findById(Long id) {
        BusStop b = busStopRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(b, BusStop.class);
        return new BusStopHelper(b);
    }

    public void add(BusStopHelper busStopHelper){
        BusStop busStop = new BusStop();
        busStop.setName(busStopHelper.getName());
        busStop.setAddress(busStopHelper.getAddress());
        busStop.setLocation(busStopHelper.getLocation());
        busStopRepository.save(busStop);
    }

    public void edit(BusStopHelper busStopHelper) {
        BusStop busStop = busStopRepository.findOne(busStopHelper.getId());
        ExceptionFactory.throwNotFoundExceptionIfNull(busStop, BusStop.class);
        busStop.setName(busStopHelper.getName());
        busStop.setAddress(busStopHelper.getAddress());
        busStop.setLocation(busStopHelper.getLocation());
        busStopRepository.save(busStop);
    }

    public void delete(Long id) {
        BusStop busStop = busStopRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(busStop, BusStop.class);
        busStopRepository.delete(busStop);
    }

    private List<BusStopHelper> convertToHelper(List<BusStop> busStops){
        List<BusStopHelper> busStopHelpers = new ArrayList<>();
        for (BusStop b: busStops) {
            busStopHelpers.add(new BusStopHelper(b));
        }
        return busStopHelpers;
    }

}
