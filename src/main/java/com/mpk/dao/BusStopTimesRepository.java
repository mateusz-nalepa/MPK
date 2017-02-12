package com.mpk.dao;

import com.mpk.entity.BusStopTimes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusStopTimesRepository extends JpaRepository<BusStopTimes, Long> {
}
