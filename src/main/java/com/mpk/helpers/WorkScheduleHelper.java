package com.mpk.helpers;

import com.mpk.entity.WorkSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkScheduleHelper {

    private Long id;
    private DriverHelper driverHelper;
    private BusHelper busHelper;
    private LocalDateTime workBegin;
    private LocalDateTime workEnd;
    private Long busId;
    private Long driverId;
    private long hours;

    public WorkScheduleHelper(WorkSchedule ws) {
        this.id = ws.getId();
        this.driverHelper = new DriverHelper(ws.getDriver());
        this.busHelper = new BusHelper(ws.getBus());
        this.workBegin = ws.getWorkBegin();
        this.workEnd = ws.getWorkEnd();
        this.hours = ws.getHours();
    }

}