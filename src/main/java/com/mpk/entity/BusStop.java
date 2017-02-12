package com.mpk.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusStop implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    @Embedded
    private Coordinates location;
//    @OneToMany(mappedBy = "busStop")
//    private List<RouteBusStop> routeBusStops;

}
