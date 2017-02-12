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
public class BusLine implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    @OneToOne(mappedBy = "busLine", optional = true)
    private Route route;
    @OneToMany(mappedBy = "busLine", cascade = CascadeType.ALL)
    private List<Timetable> timetables;


}
