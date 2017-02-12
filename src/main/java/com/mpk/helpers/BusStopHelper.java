package com.mpk.helpers;

import com.mpk.entity.BusStop;
import com.mpk.entity.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusStopHelper {

    private Long id;
    private String name;
    private String address;
    private Coordinates location;

    public BusStopHelper() {
    }

    public BusStopHelper(BusStop busStop) {
        id = busStop.getId();
        name = busStop.getName();
        address = busStop.getAddress();
        location = busStop.getLocation();
    }
}
