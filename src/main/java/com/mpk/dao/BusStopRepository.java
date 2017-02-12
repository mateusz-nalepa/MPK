package com.mpk.dao;

import com.mpk.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
}
