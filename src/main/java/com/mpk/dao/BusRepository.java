package com.mpk.dao;

import com.mpk.entity.Bus;
import com.mpk.enums.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByStatusAndActive(BusStatus busStatus, Boolean active);

    Bus findByRegistrationNumber(String registrationNumber);
}
