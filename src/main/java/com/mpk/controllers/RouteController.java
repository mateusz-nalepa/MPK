package com.mpk.controllers;

import java.util.List;

import com.mpk.excepctions.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.helpers.RouteHelper;
import com.mpk.services.RouteService;

@RestController
@RequestMapping("busline")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/{busLineId}/routes")
    public RouteHelper getAllRoutes(@PathVariable Long busLineId) throws RouteNotFoundException {
        try {
            return routeService.findByBusLine(busLineId);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RouteNotFoundException();
        }
    }

//    @GetMapping("/{busLineId}/routes/{routeId}")
//    public RouteHelper getRoute(@PathVariable Long busLineId, @PathVariable Long routeId) {
//        return routeService.findById(routeId);
//    }

    @PostMapping("/{busLineId}/routes")
    public ResponseEntity<Void> add(@PathVariable Long busLineId, @RequestBody RouteHelper routeHelper) {
        routeService.add(busLineId, routeHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{busLineId}/routes/{routeId}")
    public ResponseEntity<Void> update(@PathVariable Long busLineId, @PathVariable Long routeId, @RequestBody RouteHelper routeHelper) {
        routeHelper.setId(routeId);
        routeService.update(busLineId, routeHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{busLineId}/routes")
    public ResponseEntity<Void> delete(@PathVariable Long busLineId) {
        RouteHelper route = routeService.findByBusLine(busLineId);
        routeService.delete(route.getId());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
