package com.mpk.dao;

import com.mpk.entity.RouteBusStop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bartosz Piśkiewicz on 30.01.2017.
 */
public interface RouteBusStopRepository extends JpaRepository<RouteBusStop, Long> {
}
