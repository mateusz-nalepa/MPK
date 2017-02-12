package com.mpk.entity;

import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import com.mpk.helpers.TimetableHelper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Timetable implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BusLine busLine;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    @Enumerated(EnumType.STRING)
    private DayKind dayKind;
    @Temporal(TemporalType.TIME)
    private Date departureTime;

    public Timetable() {
        this.dayKind = DayKind.Workday;
    }

    public Timetable(TimetableHelper t) {
        this.id = t.getId();
        this.direction = t.getDirection();
        this.dayKind = t.getDayKind();
        this.departureTime = t.getDepartureTime();
    }
}
