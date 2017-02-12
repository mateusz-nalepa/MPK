package com.mpk.services;

import com.mpk.dao.BusRepository;
import com.mpk.dao.DriverRepository;
import com.mpk.dao.WorkScheduleRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.Driver;
import com.mpk.entity.WorkSchedule;
import com.mpk.enums.BusStatus;
import com.mpk.enums.DriverStatus;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusHelper;
import com.mpk.helpers.ChooseHoursHelper;
import com.mpk.helpers.DriverHelper;
import com.mpk.helpers.WorkScheduleHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkScheduleService {
//    busesInRange.forEach(freeBuses::remove);

    //        for (Bus bus : busesInRange) {
//        freeBuses.remove(bus);
//    }
    private final WorkScheduleRepository workScheduleRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public WorkScheduleService(WorkScheduleRepository workScheduleRepository, BusRepository busRepository, DriverRepository driverRepository) {
        this.workScheduleRepository = workScheduleRepository;
        this.busRepository = busRepository;
        this.driverRepository = driverRepository;
    }

    @SneakyThrows
    public ResponseEntity<Void> add(WorkScheduleHelper wsh) {
        if (!isPossibleAddBus(wsh)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (!isPossibleAddDriver(wsh)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        long hours = calculateHours(wsh.getWorkBegin(), wsh.getWorkEnd());
        WorkSchedule workSchedule = WorkSchedule
                .builder()
                .workBegin(wsh.getWorkBegin())
                .workEnd(wsh.getWorkEnd())
                .hours(hours)
                .bus(busRepository.findOne(wsh.getBusId()))
                .driver(driverRepository.findOne(wsh.getDriverId()))
                .active(true)
                .build();
        workScheduleRepository.save(workSchedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private boolean isPossibleAddBus(WorkScheduleHelper wsh) {
        Bus bus = busRepository.findOne(wsh.getBusId());
        ExceptionFactory.throwNotFoundExceptionIfNull(bus, Bus.class);
        List<Bus> buses = freeBuses(wsh.getWorkBegin(), wsh.getWorkEnd());
        return buses.contains(bus);
    }

    private boolean isPossibleAddDriver(WorkScheduleHelper wsh) {
        Driver driver = driverRepository.findOne(wsh.getDriverId());
        ExceptionFactory.throwNotFoundExceptionIfNull(driver, Driver.class);
        List<Driver> drivers = freeDrivers(wsh.getWorkBegin(), wsh.getWorkEnd());
        return drivers.contains(driver);
    }

    private long calculateHours(LocalDateTime workBegin, LocalDateTime workEnd) {
        return ChronoUnit.HOURS.between(workBegin, workEnd);
    }

    private List<Driver> freeDrivers(LocalDateTime workBegin, LocalDateTime workEnd) {
        List<WorkSchedule> inRange = workScheduleRepository
                .findByWorkBeginGreaterThanEqualAndWorkEndLessThanEqualAndActive(workBegin, workEnd, true);
        List<Driver> driversInRange = new ArrayList<>();
        inRange.forEach(ws -> driversInRange.add(ws.getDriver()));
        List<Driver> freeDrivers = driverRepository.findByStatus(DriverStatus.Active);
        driversInRange.forEach(freeDrivers::remove);
        return freeDrivers;
    }

    private List<Bus> freeBuses(LocalDateTime workBegin, LocalDateTime workEnd) {
        List<WorkSchedule> inRange = workScheduleRepository
                .findByWorkBeginGreaterThanEqualAndWorkEndLessThanEqualAndActive(workBegin, workEnd, true);
        List<Bus> busesInRange = new ArrayList<>();
        inRange.forEach(workSchedule -> busesInRange.add(workSchedule.getBus()));
        List<Bus> freeBuses = busRepository.findByStatusAndActive(BusStatus.Active, true);
        busesInRange.forEach(freeBuses::remove);
        return freeBuses;
    }

    public List<DriverHelper> freeDriversHelpers(ChooseHoursHelper chooseHoursHelper) {
        LocalDateTime workBegin = chooseHoursHelper.getWorkBegin();
        LocalDateTime workEnd = chooseHoursHelper.getWorkEnd();
        return freeDrivers(workBegin, workEnd)
                .stream()
                .map(DriverHelper::new)
                .collect(Collectors.toList());

    }

    public List<BusHelper> freeBusesHelpers(ChooseHoursHelper chooseHoursHelper) {
        LocalDateTime workBegin = chooseHoursHelper.getWorkBegin();
        LocalDateTime workEnd = chooseHoursHelper.getWorkEnd();

        return freeBuses(workBegin, workEnd)
                .stream()
                .map(BusHelper::new)
                .collect(Collectors.toList());
    }

    public List<WorkScheduleHelper> findAll() {
        return workScheduleRepository.findByActive(true)
                .stream()
                .map(WorkScheduleHelper::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Void> delete(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(workSchedule, WorkSchedule.class);
        workSchedule.setActive(false);
        workScheduleRepository.save(workSchedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
