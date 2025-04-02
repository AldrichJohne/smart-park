package com.aldrich.ulabo.smartpark.controller;

import com.aldrich.ulabo.smartpark.entity.Vehicle;
import com.aldrich.ulabo.smartpark.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.registerVehicle(vehicle));
    }

    @PostMapping("/{licensePlate}/check-in/{lotId}")
    public ResponseEntity<Vehicle> checkIn(@PathVariable String licensePlate, @PathVariable String lotId) {
        return ResponseEntity.ok(service.checkIn(licensePlate, lotId));
    }

    @PostMapping("/{licensePlate}/check-out")
    public ResponseEntity<Vehicle> checkOut(@PathVariable String licensePlate) {
        return ResponseEntity.ok(service.checkout(licensePlate));
    }

}
