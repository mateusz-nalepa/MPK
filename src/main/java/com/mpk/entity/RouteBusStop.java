package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RouteBusStop {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BusStop busStop;
    @ManyToOne(cascade = CascadeType.ALL)
    private Route route;
    private int numberInRoute;
}
