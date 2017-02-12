package com.mpk.helpers;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteHelper {

    private Long id;
    private List<BusStopInRouteHelper> busStops;

    public RouteHelper() {
    }

}
