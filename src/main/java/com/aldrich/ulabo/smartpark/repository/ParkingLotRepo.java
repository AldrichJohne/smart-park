package com.aldrich.ulabo.smartpark.repository;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepo extends JpaRepository<ParkingLot, String> {
}
