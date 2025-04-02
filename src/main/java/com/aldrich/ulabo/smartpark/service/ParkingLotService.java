package com.aldrich.ulabo.smartpark.service;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;

import java.util.List;
import java.util.Optional;

public interface ParkingLotService {
    ParkingLot registerParkingLot(ParkingLot parkingLot);
    List<ParkingLot> getParkingLots();
    Optional<ParkingLot> getParkingLotById(String lotId);
}
