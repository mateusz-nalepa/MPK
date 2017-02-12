package com.mpk.helpers;

import com.mpk.entity.BusStop;
import com.mpk.entity.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusStopInRouteHelper {
    private Long id;
    private String name;
    private String address;
    private Coordinates location;
    private Integer numberInRoute;

    public BusStopInRouteHelper() {
    }

    public BusStopInRouteHelper(BusStop busStop, int numberInRoute) {
        id = busStop.getId();
        name = busStop.getName();
        address = busStop.getAddress();
        this.numberInRoute = numberInRoute;
    }

}
