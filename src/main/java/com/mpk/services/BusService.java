package com.mpk.services;

import com.mpk.dao.BusRepository;
import com.mpk.entity.Bus;
import com.mpk.enums.BusStatus;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<BusHelper> findAll() {
        List<Bus> buses = busRepository.findAll();
        List<BusHelper> busHelpers = new ArrayList<>();
        buses.forEach((bus -> busHelpers.add(new BusHelper(bus))));
        return busHelpers;
    }

    public BusHelper findOne(Long id) {
        Bus bus = busRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(bus, Bus.class);
        return new BusHelper(bus);
    }

    public ResponseEntity<Void> add(BusHelper busHelper) {
        Bus bus = Bus.builder()
                .location(busHelper.getLocation())
                .status(busHelper.getStatus())
                .registrationNumber(busHelper.getRegistrationNumber())
                .yearOfProduction(busHelper.getYearOfProduction())
                .active(true)
                .status(BusStatus.Active)
                .build();

        if ((busRepository.findByRegistrationNumber(bus.getRegistrationNumber())) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        busRepository.save(bus);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    public ResponseEntity<Void> update(BusHelper busHelper) {
        if (!isPossibleChangeRegistrationNumber(busHelper)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        Bus bus = busRepository.findOne(busHelper.getId());
        ExceptionFactory.throwNotFoundExceptionIfNull(bus, Bus.class);
        bus.setLocation(busHelper.getLocation());
        bus.setStatus(busHelper.getStatus());
        bus.setRegistrationNumber(busHelper.getRegistrationNumber());
        bus.setYearOfProduction(busHelper.getYearOfProduction());
        busRepository.save(bus);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> delete(Long id) {
        Bus bus = busRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(bus, Bus.class);

        if (bus.getWorkSchedules().isEmpty()) {
            bus.setActive(false);
            busRepository.save(bus);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private boolean isPossibleChangeRegistrationNumber(BusHelper busHelper) {
        Bus bus = busRepository.findByRegistrationNumber(busHelper.getRegistrationNumber());
        if (((bus) != null) && (bus.getId() != busHelper.getId())) {
            return false;
        }
        return true;
    }

}
