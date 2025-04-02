package com.aldrich.ulabo.smartpark.service.impl;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.entity.Vehicle;
import com.aldrich.ulabo.smartpark.repository.ParkingLotRepo;
import com.aldrich.ulabo.smartpark.repository.VehicleRepo;
import com.aldrich.ulabo.smartpark.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepo vehicleRepo;
    private final ParkingLotRepo parkingLotRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, ParkingLotRepo parkingLotRepo) {
        this.vehicleRepo = vehicleRepo;
        this.parkingLotRepo = parkingLotRepo;
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Vehicle checkIn(String licensePlate, String lotId) {
        ParkingLot parkingLot = parkingLotRepo.findById(lotId)
                .orElseThrow(()-> new IllegalArgumentException("Parking Lot with ID " + lotId + " not found"));
        if (parkingLot.isFull()) {
            throw new IllegalStateException("Parking Lot with ID " + lotId + " is full");
        }

        Vehicle vehicle = vehicleRepo.findById(licensePlate)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with license plate " + licensePlate + " not found"));

        if (vehicle.getParkingLot() != null) {
            throw new IllegalStateException("Vehicle with license plate " + licensePlate + " is already parked");
        }

        vehicle.setParkingLot(parkingLot);
        parkingLot.setOccupiedSpace(parkingLot.getOccupiedSpace() + 1);

        parkingLotRepo.save(parkingLot);
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Vehicle checkout(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findById(licensePlate)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with license plate " + licensePlate + " not found"));

        ParkingLot lot = vehicle.getParkingLot();
        if (lot == null) {
            throw new IllegalStateException("Vehicle with license plate " + licensePlate + " is not parked in any lot");
        }

        vehicle.setParkingLot(null);
        lot.setOccupiedSpace(lot.getOccupiedSpace() - 1);

        parkingLotRepo.save(lot);
        return vehicleRepo.save(vehicle);
    }

    @Override
    public List<Vehicle> getParkVehicles(String lotId) {
        return vehicleRepo.findAll()
                .stream()
                .filter(v -> v.getParkingLot() != null && v.getParkingLot().getLotId().equals(lotId))
                .collect(Collectors.toList());
    }
}
