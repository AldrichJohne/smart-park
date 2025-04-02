package com.aldrich.ulabo.smartpark.service.impl;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.repository.ParkingLotRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements com.aldrich.ulabo.smartpark.service.ParkingLotService {
    private final ParkingLotRepo repo;

    public ParkingLotServiceImpl(ParkingLotRepo repo) {
        this.repo = repo;
    }

    @Override
    public ParkingLot registerParkingLot(ParkingLot parkingLot) {
        return repo.save(parkingLot);
    }

    @Override
    public List<ParkingLot> getParkingLots() {
        return repo.findAll();
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(String lotId) {
        return repo.findById(lotId);
    }
}
