package com.aldrich.ulabo.smartpark.repository;

import com.aldrich.ulabo.smartpark.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {
}
