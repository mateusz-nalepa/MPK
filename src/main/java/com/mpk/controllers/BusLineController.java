package com.mpk.controllers;

import com.mpk.excepctions.BusLineNotFoundException;
import com.mpk.helpers.BusLineHelper;
import com.mpk.services.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("busline")
public class BusLineController {
    private BusLineService busLineService;

    @Autowired
    public BusLineController(BusLineService busLineService) {
        this.busLineService = busLineService;
    }

    @GetMapping
    public List<BusLineHelper> getAll(){
        return busLineService.getAll();
    }

    @GetMapping("{busLineId}")
    public BusLineHelper getById(@PathVariable Long busLineId) {
        return busLineService.findById(busLineId);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody BusLineHelper busLineHelper){
        busLineService.add(busLineHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("{busLineId}")
    public ResponseEntity<Void> update(@PathVariable Long busLineId, @RequestBody BusLineHelper busLineHelper) {
        busLineHelper.setId(busLineId);
        busLineService.update(busLineHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("{busLineId}")
    public ResponseEntity<Void> delete(@PathVariable Long busLineId) {
        busLineService.delete(busLineId);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
