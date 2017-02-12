package com.mpk;

import com.mpk.dao.*;
import com.mpk.entity.*;
import com.mpk.enums.BusStatus;
import com.mpk.enums.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestNalepa {

    @Autowired
    UserRepository userRepo;

    @Autowired
    Test test;

    @Autowired
    WorkScheduleRepository workScheduleRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BusLineRepository busLineRepository;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        addBusesWithWorkSchedules();
        test.afterPropertiesSet();
    }

    @javax.transaction.Transactional
    private void addBusesWithWorkSchedules() {
        List<Bus> buses = new ArrayList<>();
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(50.0).north(50.0).build())
                .active(false).yearOfProduction(1990).registrationNumber("TBU1111").build());
        buses.add(Bus.builder().status(BusStatus.Broken).location(Coordinates.builder().east(60.0).north(60.0).build())
                .active(true).yearOfProduction(1991).registrationNumber("TBU1112").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(70.0).north(70.0).build())
                .active(true).yearOfProduction(1992).registrationNumber("TBU1113").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(80.0).north(80.0).build())
                .active(false).yearOfProduction(1993).registrationNumber("TBU1114").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(90.0).north(90.0).build())
                .active(true).yearOfProduction(1995).registrationNumber("TBU1115").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(100.0).north(100.0).build())
                .active(true).yearOfProduction(1997).registrationNumber("TBU1116").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(120.0).north(110.0).build())
                .active(false).yearOfProduction(1999).registrationNumber("TBU1117").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(120.0).north(120.0).build())
                .active(true).yearOfProduction(2015).registrationNumber("TBU1118").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(130.0).north(130.0).build())
                .active(false).yearOfProduction(2016).registrationNumber("TBU1119").build());
        busRepository.save(buses);

        addRandomUsers();
        List<User> users = getRandomUsers();

        addRandomDrivers(users);
        List<Driver> drivers = getRandomDrivers();

        List<User> randomUsers = getRandomUsers();

        int liczba = 0;
        for (User u : randomUsers) {
            u.setDriver(drivers.get(liczba));
            liczba++;
        }

        saveModifiedUsers(randomUsers);
        users = getModifiedUsers();


        List<LocalDateTime> localDateTimesBegin = new ArrayList<>();
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 23, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 24, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 25, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 26, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 26, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 27, 8, 0));
        List<LocalDateTime> localDateTimesEnd = new ArrayList<>();
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 23, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 24, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 25, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 26, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 26, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 27, 16, 0));

        addRandomWS(buses, drivers, localDateTimesBegin, localDateTimesEnd);
        addBusStop();
        addBusLine();

    }

    public List<User> getModifiedUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void saveModifiedUsers(List<User> randomUsers) {
        userRepo.save(randomUsers);
    }

    @Transactional
    private List<WorkSchedule> getRandomWS() {
        return workScheduleRepository.findAll();
    }

    @Transactional
    public void addRandomWS(List<Bus> buses, List<Driver> drivers, List<LocalDateTime> localDateTimesBegin, List<LocalDateTime> localDateTimesEnd) {
        List<WorkSchedule> workSchedules = new ArrayList<>();
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(0)).workEnd(localDateTimesEnd.get(0))
                .active(false).hours(8).bus(buses.get(0)).driver(drivers.get(0)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(1)).workEnd(localDateTimesEnd.get(1))
                .active(true).hours(8).bus(buses.get(1)).driver(drivers.get(1)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(2)).workEnd(localDateTimesEnd.get(2))
                .active(true).hours(8).bus(buses.get(2)).driver(drivers.get(2)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(3)).workEnd(localDateTimesEnd.get(3))
                .active(true).hours(8).bus(buses.get(3)).driver(drivers.get(3)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(4)).workEnd(localDateTimesEnd.get(4))
                .active(false).hours(8).bus(buses.get(4)).driver(drivers.get(4)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(5)).workEnd(localDateTimesEnd.get(5))
                .active(true).hours(8).bus(buses.get(5)).driver(drivers.get(5)).build());
        workScheduleRepository.save(workSchedules);
    }

    @Transactional
    private void addRandomDrivers(List<User> users) {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(Driver.builder().salary(1111).status(DriverStatus.Active).user(users.get(0)).build());
        drivers.add(Driver.builder().salary(2222).status(DriverStatus.Active).user(users.get(1)).build());
        drivers.add(Driver.builder().salary(3333).status(DriverStatus.Active).user(users.get(2)).build());
        drivers.add(Driver.builder().salary(4444).status(DriverStatus.Active).user(users.get(3)).build());
        drivers.add(Driver.builder().salary(5555).status(DriverStatus.Active).user(users.get(4)).build());
        drivers.add(Driver.builder().salary(6666).status(DriverStatus.Active).user(users.get(5)).build());
        driverRepository.save(drivers);
    }

    @Transactional
    private List<Driver> getRandomDrivers() {
        return driverRepository.findAll();
    }

    @Transactional
    private List<User> getRandomUsers() {
        return userRepo.findAll();

    }


    @Transactional
    public void addRandomUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().firstName("Imie Jeden").lastName("Nazw Jeden").username("jeden").password("jeden")
                .mail("jeden@gmail.com").phone("111111111").active(true).role("ROLE_USER").build());
        users.add(User.builder().firstName("Imie dwa").lastName("Nazw dwa").username("dwa").password("dwa")
                .mail("dwa@gmail.com").phone("222222222").active(true).role("ROLE_USER").build());
        users.add(User.builder().firstName("Imie trzy").lastName("Nazw trzy").username("trzy").password("trzy")
                .mail("trzy@gmail.com").phone("333333333").active(true).role("ROLE_USER").build());
        users.add(User.builder().firstName("Imie cztery").lastName("Nazw cztery").username("cztery").password("cztery")
                .mail("cztery@gmail.com").phone("444444444").active(true).role("ROLE_USER").build());
        users.add(User.builder().firstName("Imie piec").lastName("Nazw piec").username("piec").password("piec")
                .mail("piec@gmail.com").phone("555555555").active(true).role("ROLE_USER").build());
        users.add(User.builder().firstName("Imie szesc").lastName("Nazw szesc").username("szesc").password("szesc")
                .mail("szesc@gmail.com").phone("666666666").active(true).role("ROLE_USER").build());

        userRepo.save(users);
    }

    //Dawid test
    @Transactional
    private void addBusStop() {
        List<BusStop> busstops = new ArrayList<>();
        busstops.add(BusStop.builder().name("Warszawska/Politechnika").address("Warszawska").location(Coordinates.builder().east(50.880417).north(20.637372).build()).build());
        busstops.add(BusStop.builder().name("Romualda/Mielczarskiego").address("Mielczarskiego").location(Coordinates.builder().east(50.871853).north(20.613704).build()).build());
        busstops.add(BusStop.builder().name("Warszawska/Korona").address("Warszawska/Korona").location(Coordinates.builder().east(50.875857).north(20.634376).build()).build());
        busstops.add(BusStop.builder().name("Pocieszka").address("Pocieszka").location(Coordinates.builder().east(50.880139).north(20.63487).build()).build());
        busstops.add(BusStop.builder().name("IX Wiekow Kielc").address("IX Wiekow Kielc").location(Coordinates.builder().east(50.874026).north(20.632555).build()).build());

        busStopRepository.save(busstops);
    }

    @Transactional
    private void addBusLine() {

        List<BusLine> buslines = new ArrayList<>();
        buslines.add(BusLine.builder().name("CommonLine").type("normal").build());
        buslines.add(BusLine.builder().name("UltraLine").type("hasty").build());
        buslines.add(BusLine.builder().name("TripLine").type("comfortable").build());
        busLineRepository.save(buslines);
    }


}


