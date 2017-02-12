package com.mpk.dao;

import com.mpk.entity.BusLine;
import com.mpk.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByBusLine(BusLine busLine);
}
