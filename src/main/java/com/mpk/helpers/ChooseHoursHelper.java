package com.mpk.helpers;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChooseHoursHelper {
    private LocalDateTime workBegin;
    private LocalDateTime workEnd;
}
