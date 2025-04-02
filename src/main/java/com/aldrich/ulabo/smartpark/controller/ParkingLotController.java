package com.aldrich.ulabo.smartpark.controller;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.entity.Vehicle;
import com.aldrich.ulabo.smartpark.service.ParkingLotService;
import com.aldrich.ulabo.smartpark.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lot")
public class ParkingLotController {
    private final ParkingLotService service;
    private final VehicleService vehicleService;

    public ParkingLotController(ParkingLotService service, VehicleService vehicleService) {
        this.service = service;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ParkingLot> registerParkingLot(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(service.registerParkingLot(parkingLot));
    }

    @GetMapping("/{lotId}")
    public ResponseEntity<ParkingLot> getParkingLot(@PathVariable String lotId) {
        return service.getParkingLotById(lotId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicles/{lotId}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByParkingLot(@PathVariable String lotId) {
        return ResponseEntity.ok(vehicleService.getParkVehicles(lotId));
    }
}
