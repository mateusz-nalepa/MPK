package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
public class Route implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @OrderBy("numberInRoute")
    private List<RouteBusStop> routeBusStops;
    @OneToOne(cascade = CascadeType.PERSIST)
    private BusLine busLine;
}
