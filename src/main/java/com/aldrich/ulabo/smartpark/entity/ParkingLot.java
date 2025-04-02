package com.aldrich.ulabo.smartpark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingLot {
    public ParkingLot(String lotId, String location, int capacity, int occupiedSpace) {
        this.lotId = lotId;
        this.location = location;
        this.capacity = capacity;
        this.occupiedSpace = occupiedSpace;
    }
    public ParkingLot(){}

    @Id
    @Column(length = 50, unique = true, nullable = false)
    private String lotId;

    private String location;
    private int capacity;
    private int occupiedSpace = 0;

    public boolean isFull() {
        return occupiedSpace >= capacity;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupiedSpace() {
        return occupiedSpace;
    }

    public void setOccupiedSpace(int occupiedSpace) {
        this.occupiedSpace = occupiedSpace;
    }
}
