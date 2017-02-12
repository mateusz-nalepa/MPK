package com.mpk.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Bartosz Piśkiewicz on 30.01.2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class ABitBetterTimetableHelper {
    private List<TimetableHelper> straight;
    private List<TimetableHelper> reversed;

}
