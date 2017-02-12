package com.mpk.excepctions;

import com.mpk.entity.*;
import lombok.SneakyThrows;

public class ExceptionFactory {
    @SneakyThrows
    public static void throwNotFoundExceptionIfNull(Object o, Class c) {
        if (o == null) {
            if (c.equals(BusLine.class)) {
                throw new BusLineNotFoundException();
            }
            if (c.equals(Route.class)) {
                throw new RouteNotFoundException();
            }
            if (c.equals(Bus.class)) {
                throw new BusNotFoundException();
            }
            if (c.equals(BusStop.class)) {
                throw new BusStopNotFoundException();
            }
            if (c.equals(WorkSchedule.class)) {
                throw new WorkScheduleNotFoundException();
            }
        }
    }
}
