package com.mpk.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WorkSchedule implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Bus bus;
    @Column(name = "work_begin", columnDefinition = "DATETIME")
    private LocalDateTime workBegin;
    @Column(name = "work_end", columnDefinition = "DATETIME")
    private LocalDateTime workEnd;
    private long hours;
    private Boolean active;
    @OneToMany(mappedBy = "workSchedule")
    private List<Course> courses;
    //@OneToMany(mappedBy = "workSchedule")
    //private  List<BusIntersecWorkSchedule> busIntersecWorkSchedules;
}
