package com.mpk.helpers;

import com.mpk.entity.BusLine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusLineHelper {
    private Long id;
    private String name;
    private String type;

    public BusLineHelper(){

    }
    public BusLineHelper(BusLine busLine){
        id = busLine.getId();
        name = busLine.getName();
        type = busLine.getType();
    }
    

}
