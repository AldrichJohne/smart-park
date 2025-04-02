package com.aldrich.ulabo.smartpark.service;

import com.aldrich.ulabo.smartpark.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle registerVehicle(Vehicle vehicle);
    Vehicle checkIn(String licensePlate, String lotId);
    Vehicle checkout(String licensePlate);
    List<Vehicle> getParkVehicles(String lotId);
}
