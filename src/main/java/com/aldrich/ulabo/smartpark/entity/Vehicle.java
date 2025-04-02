package com.aldrich.ulabo.smartpark.entity;

import com.aldrich.ulabo.smartpark.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.Pattern;

@Entity
public class Vehicle {
    public Vehicle(String licensePlate, VehicleType type, String ownerName, ParkingLot parkingLot) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
        this.parkingLot = parkingLot;
    }

    public Vehicle() {
    }

    @Id
    @Pattern(regexp = "^[A-Z]{3}-\\d{4}$", message = "Invalid license plate format. Expected format: AAA-1234")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Owner name must contain only letters and spaces")
    private String ownerName;

    @Getter
    @ManyToOne
    private ParkingLot parkingLot;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
