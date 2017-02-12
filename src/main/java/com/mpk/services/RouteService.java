package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.dao.BusStopRepository;
import com.mpk.dao.RouteBusStopRepository;
import com.mpk.dao.RouteRepository;
import com.mpk.entity.BusLine;
import com.mpk.entity.BusStop;
import com.mpk.entity.Route;
import com.mpk.entity.RouteBusStop;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusStopInRouteHelper;
import com.mpk.helpers.RouteHelper;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Service
public class RouteService extends Observable {
    private RouteRepository routeRepository;
    private BusLineRepository busLineRepository;
    private BusStopRepository busStopRepository;
    private RouteBusStopRepository routeBusStopRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, BusLineRepository busLineRepository, BusStopRepository busStopRepository, RouteBusStopRepository routeBusStopRepository) {
        this.routeRepository = routeRepository;
        this.busLineRepository = busLineRepository;
        this.busStopRepository = busStopRepository;
        this.routeBusStopRepository = routeBusStopRepository;
    }

    public RouteHelper findByBusLine(Long busLineId) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        Route route = routeRepository.findByBusLine(busLine);
        RouteHelper routeHelper = new RouteHelper();
        routeHelper.setId(route.getId());
        routeHelper.setBusStops(fetchBusStopsInHelpers(route.getRouteBusStops()));
        return routeHelper;
    }
//
//    public RouteHelper findById(Long routeId) {
//        Route route = routeRepository.findOne(routeId);
//        ExceptionFactory.throwNotFoundExceptionIfNull(route);
//        RouteHelper routeHelper = new RouteHelper();
//        routeHelper.setId(route.getId());
//        routeHelper.setBusStops(fetchBusStopsInHelpers(route.getRouteBusStops()));
//        return routeHelper;
//    }

    public void add(Long busLineId, RouteHelper routeHelper) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        Route route = new Route();
        route.setBusLine(busLine);
        route.setRouteBusStops(getRouteBusStops(routeHelper.getBusStops(), route));
        routeRepository.save(route);
        busLine.setRoute(route);
        busLineRepository.save(busLine);
        this.notifyObservers();
    }

    public void update(Long busLineId, RouteHelper routeHelper) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        this.delete(busLine.getRoute());
        this.add(busLineId, routeHelper);
    }

    public void delete(Long routeId) {
        Route route = routeRepository.findOne(routeId);
        this.delete(route);
    }

    @Transactional
    public void delete(Route route) {
        ExceptionFactory.throwNotFoundExceptionIfNull(route, Route.class);
        BusLine busLine = route.getBusLine();
        busLine.setRoute(null);
        route.setBusLine(null);
        busLineRepository.save(busLine);
        route.setBusLine(null);
//        for (RouteBusStop b: route.getRouteBusStops()) {
//            b.setBusStop(null);
////            b.setRoute(null);
//            routeBusStopRepository.save(b);
////            routeBusStopRepository.delete(b);
//        }
        routeRepository.delete(route);
        this.notifyObservers();
    }

    private List<RouteBusStop> getRouteBusStops(List<BusStopInRouteHelper> busStops, Route route) {
        List<RouteBusStop> routeBusStops = new ArrayList<>();
        for (BusStopInRouteHelper b : busStops) {
            routeBusStops.add(getRouteBusStop(b, route));
        }
        return routeBusStops;
    }

    private RouteBusStop getRouteBusStop(BusStopInRouteHelper busStopInRouteHelper, Route route) {
        RouteBusStop routeBusStop = new RouteBusStop();
        routeBusStop.setRoute(route);
        routeBusStop.setNumberInRoute(busStopInRouteHelper.getNumberInRoute());
        BusStop busStop = busStopRepository.findOne(busStopInRouteHelper.getId());
        routeBusStop.setBusStop(busStop);
        return routeBusStop;
    }

    private List<BusStopInRouteHelper> fetchBusStopsInHelpers(List<RouteBusStop> routeBusStops) {
        List<BusStopInRouteHelper> busStopInRouteHelpers = new ArrayList<>();
        for (RouteBusStop b : routeBusStops) {
            BusStopInRouteHelper busStopInRouteHelper = new BusStopInRouteHelper();
            busStopInRouteHelper.setId(b.getBusStop().getId());
            busStopInRouteHelper.setName(b.getBusStop().getName());
            busStopInRouteHelper.setLocation(b.getBusStop().getLocation());
            busStopInRouteHelper.setAddress(b.getBusStop().getAddress());
            busStopInRouteHelper.setNumberInRoute(b.getNumberInRoute());
            busStopInRouteHelpers.add(busStopInRouteHelper);
        }
        return busStopInRouteHelpers;
    }

}
