package com.mpk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BusStopTimes {
    @Id
    @GeneratedValue
    private Long id;
    private long busOneId;
    private long busTwoId;
    private int time;

    public BusStopTimes(){}

    public BusStopTimes(long busOneId, long busTwoId, int time) {
        this.busOneId = busOneId;
        this.busTwoId = busTwoId;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBusOneId() {
        return busOneId;
    }

    public void setBusOneId(long busOneId) {
        this.busOneId = busOneId;
    }

    public long getBusTwoId() {
        return busTwoId;
    }

    public void setBusTwoId(long busTwoId) {
        this.busTwoId = busTwoId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof BusStopTimes)){
            return false;
        }
        return (((BusStopTimes) obj).getBusTwoId() == this.getBusTwoId()) && (((BusStopTimes) obj).getBusOneId() == this.getBusOneId());
    }
}
