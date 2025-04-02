package com.aldrich.ulabo.smartpark.service.impl;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.entity.Vehicle;
import com.aldrich.ulabo.smartpark.enums.VehicleType;
import com.aldrich.ulabo.smartpark.repository.ParkingLotRepo;
import com.aldrich.ulabo.smartpark.repository.VehicleRepo;
import com.aldrich.ulabo.smartpark.service.VehicleService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepo vehicleRepo;

    @Mock
    private ParkingLotRepo parkingLotRepo;

    @Test
    public void registerVehicle() {
        Vehicle vehicle = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", null);
        Mockito.when(vehicleRepo.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);

        Vehicle actual = vehicleService.registerVehicle(new Vehicle());

        Assertions.assertNotNull(actual);
    }

    @Test
    public void checkin_success() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 0);
        Vehicle vehicle = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", null);

        Mockito.when(parkingLotRepo.findById(Mockito.any())).thenReturn(Optional.of(parkingLot));
        Mockito.when(vehicleRepo.findById(Mockito.anyString())).thenReturn(Optional.of(vehicle));
        Mockito.when(vehicleRepo.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);

        Vehicle actual = vehicleService.checkIn("aaa-111", "LOT001");

        Assertions.assertNotNull(actual);
    }

    @Test
    public void checkin_parking_lot_full() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 10);
        Mockito.when(parkingLotRepo.findById(Mockito.any())).thenReturn(Optional.of(parkingLot));

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () ->
                vehicleService.checkIn("aaa-111", "LOT001"));

        Assertions.assertEquals("Parking Lot with ID LOT001 is full", exception.getMessage());
    }

    @Test
    public void checkin_vehicle_already_parked() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 0);
        Vehicle vehicle = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", parkingLot);

        Mockito.when(parkingLotRepo.findById(Mockito.any())).thenReturn(Optional.of(parkingLot));
        Mockito.when(vehicleRepo.findById(Mockito.anyString())).thenReturn(Optional.of(vehicle));

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () ->
                vehicleService.checkIn("aaa-111", "LOT001"));

        Assertions.assertEquals("Vehicle with license plate aaa-111 is already parked", exception.getMessage());
    }

    @Test
    public void checkin_parking_lot_not_found() {
        Vehicle vehicle = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", null);

        Mockito.when(parkingLotRepo.findById(Mockito.any())).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                vehicleService.checkIn("aaa-111", "LOT001"));

        Assertions.assertEquals("Parking Lot with ID LOT001 not found", exception.getMessage());

    }

    @Test
    public void checkin_vehicle_not_found() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 0);

        Mockito.when(parkingLotRepo.findById(Mockito.any())).thenReturn(Optional.of(parkingLot));
        Mockito.when(vehicleRepo.findById(Mockito.any())).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                vehicleService.checkIn("aaa-111", "LOT001"));

        Assertions.assertEquals("Vehicle with license plate aaa-111 not found", exception.getMessage());

    }

    @Test
    public void checkout_success() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 1);
        ParkingLot parkingLotUpdated = new ParkingLot("LOT001", "MOA", 10, 0);
        Vehicle vehicle = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", parkingLot);
        Vehicle vehicleUpdated = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", null);

        Mockito.when(vehicleRepo.findById(Mockito.anyString())).thenReturn(Optional.of(vehicle));
        Mockito.when(parkingLotRepo.save(Mockito.any(ParkingLot.class))).thenReturn(parkingLotUpdated);
        Mockito.when(vehicleRepo.save(Mockito.any(Vehicle.class))).thenReturn(vehicleUpdated);

        Vehicle actual = vehicleService.checkout("aaa-111");

        Assertions.assertNotNull(actual);
        Assertions.assertNull(actual.getParkingLot());
    }

    @Test
    public void checkout_vehicle_not_found() {
        Mockito.when(vehicleRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.checkout("aaa-111");
        });


        Assertions.assertEquals("Vehicle with license plate aaa-111 not found", exception.getMessage());
    }

    @Test
    public void checkout_vehicle_not_parked() {
        Vehicle vehicleUpdated = new Vehicle("aaa-111", VehicleType.CAR ,"Aj Ramos", null);
        Mockito.when(vehicleRepo.findById(Mockito.anyString())).thenReturn(Optional.of(vehicleUpdated));

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            vehicleService.checkout("aaa-111");
        });


        Assertions.assertEquals("Vehicle with license plate aaa-111 is not parked in any lot", exception.getMessage());
    }

    @Test
    public void getParkVehicles() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 2);
        List<Vehicle> vehicles = List.of(
                new Vehicle("aaa-111", VehicleType.CAR, "", parkingLot),
                new Vehicle("bbb-111", VehicleType.TRUCK, "", parkingLot),
                new Vehicle("ccc-111", VehicleType.MOTORCYCLE, "",
                        new ParkingLot("LOT002", "Ayala", 20, 1)));

        Mockito.when(vehicleRepo.findAll()).thenReturn(vehicles);

        List<Vehicle> actual = vehicleService.getParkVehicles("LOT001");

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());


    }
}
