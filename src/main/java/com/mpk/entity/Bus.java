package com.mpk.entity;

import com.mpk.enums.BusStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bus implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private BusStatus status;
    @Embedded
    private Coordinates location;
    @Column(unique = true)
    private String registrationNumber;
    private int yearOfProduction;
    private Boolean active;
    @OneToMany(mappedBy = "bus")
    private List<WorkSchedule> workSchedules;
    // @OneToMany(mappedBy = "bus")
    // private List<BusIntersecWorkSchedule> busIntersecWorkSchedules;
}
