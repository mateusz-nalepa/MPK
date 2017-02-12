package com.mpk.controllers;

import com.mpk.helpers.BusHelper;
import com.mpk.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bus")
public class BusController {

    private BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public List<BusHelper> findAll() {
        return busService.findAll();
    }

    @GetMapping("{busId}")
    public BusHelper findOne(@PathVariable Long busId) {
        return busService.findOne(busId);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody BusHelper busHelper) {
        return busService.add(busHelper);
        //return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("{busId}")
    public ResponseEntity<Void> update(@PathVariable Long busId, @RequestBody BusHelper busHelper) {
        busHelper.setId(busId);
        return busService.update(busHelper);
        //return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("{busId}")
    public ResponseEntity<Void> delete(@PathVariable Long busId) {
        return busService.delete(busId);
    }

}
