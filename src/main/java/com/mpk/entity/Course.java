package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dayOfCourse;
    @ManyToOne
    private BusLine busLine;
    //@ManyToOne
    //private Bus bus;
    //@ManyToOne
    //private Driver driver;
    @ManyToOne
    private WorkSchedule workSchedule;
}
