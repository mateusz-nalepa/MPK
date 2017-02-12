package com.mpk.dao;

import com.mpk.entity.Bus;
import com.mpk.entity.Driver;
import com.mpk.enums.BusStatus;
import com.mpk.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByStatus(DriverStatus driverStatus);
}
