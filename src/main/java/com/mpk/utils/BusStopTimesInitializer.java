package com.mpk.utils;

import com.mpk.services.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * Created by Bartosz Piśkiewicz on 24.01.2017.
 */
@Component
public class BusStopTimesInitializer {

    private TimeHolder timeHolder;

    @Autowired
    public BusStopTimesInitializer(TimeHolder timeHolder) {
        this.timeHolder = timeHolder;
    }

    @PostConstruct
    public void init() {
        Random random = new Random();
        for(int i = 1; i< 21; i++) {
            for(int j=i+1; j< 21; j++) {
                timeHolder.setTimeDiff(i, j, random.nextInt(5));
            }
        }


    }

}
