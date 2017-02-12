package com.mpk.helpers;

import com.mpk.entity.Bus;
import com.mpk.entity.Coordinates;
import com.mpk.enums.BusStatus;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class BusHelper {
    private Long id;
    private BusStatus status;
    private Coordinates location;
    private String registrationNumber;
    private int yearOfProduction;
    private Boolean active;

    public BusHelper(Bus bus) {
        this.id = bus.getId();
        this.status = bus.getStatus();
        this.location = bus.getLocation();
        this.registrationNumber = bus.getRegistrationNumber();
        this.yearOfProduction = bus.getYearOfProduction();
        this.active = bus.getActive();
    }
}
