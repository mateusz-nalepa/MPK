package com.mpk.helpers;

import com.mpk.entity.Driver;
import com.mpk.enums.DriverStatus;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class DriverHelper {
    private Long id;
    private double salary;
    //private List<WorkSchedule> workSchedules;
    private DriverStatus status;
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String phone;
    private Boolean active;


    public DriverHelper(Driver driver) {
        id = driver.getId();
        salary = driver.getSalary();
        //workSchedules = driver.getWorkSchedules();
        status = driver.getStatus();
        userId = driver.getUser().getId();
        firstName = driver.getUser().getFirstName();
        lastName = driver.getUser().getLastName();
        username = driver.getUser().getUsername();
        mail = driver.getUser().getMail();
        phone = driver.getUser().getPhone();
        active = driver.getUser().getActive();
    }

}