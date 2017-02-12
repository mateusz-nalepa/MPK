package com.mpk.entity;

import lombok.*;

import javax.persistence.Embeddable;


@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private double north;
    private double east;
}
