package com.mpk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.helpers.BusStopHelper;
import com.mpk.services.BusStopService;

@RestController
@RequestMapping("map/busstop")
public class BusStopMapController {

    private BusStopService busStopService;
    @Autowired
    public BusStopMapController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @GetMapping
    public List<BusStopHelper> getAll(){
        return busStopService.findAll();
    }
}