package com.mpk.controllers;

import com.mpk.excepctions.BusStopNotFoundException;
import com.mpk.helpers.BusStopHelper;
import com.mpk.services.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("busstop")
public class BusStopController {

    private BusStopService busStopService;
    @Autowired
    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @GetMapping
    public List<BusStopHelper> getAll(){
        return busStopService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody BusStopHelper busStopHelper){
        busStopHelper.setId(null);
        busStopService.add(busStopHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("{busStopId}")
    public ResponseEntity<Void> update(@PathVariable Long busStopId, @RequestBody BusStopHelper busStopHelper) {
        busStopHelper.setId(busStopId);
        busStopService.edit(busStopHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("{busStopId}")
    public ResponseEntity<Void> delete(@PathVariable Long busStopId) {
        busStopService.delete(busStopId);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
