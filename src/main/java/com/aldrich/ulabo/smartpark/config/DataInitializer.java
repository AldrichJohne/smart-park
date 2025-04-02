package com.aldrich.ulabo.smartpark.config;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.entity.Vehicle;
import com.aldrich.ulabo.smartpark.enums.VehicleType;
import com.aldrich.ulabo.smartpark.repository.ParkingLotRepo;
import com.aldrich.ulabo.smartpark.repository.VehicleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeDatabase(ParkingLotRepo parkingLotRepo, VehicleRepo vehicleRepo) {
        return args -> {

            ParkingLot lot1 = new ParkingLot("LOT01", "Mall Of Asia", 30, 0);
            ParkingLot lot2 = new ParkingLot("LOT02", "St Luke's Hospital", 20, 0);

            parkingLotRepo.saveAll(List.of(lot1, lot2));

            Vehicle car1 = new Vehicle("NNN-1111", VehicleType.CAR, "Coco Martin", null);
            Vehicle bike1 = new Vehicle("CCC-0000", VehicleType.MOTORCYCLE, "Juan Dela Cruz", null);

            vehicleRepo.saveAll(List.of(car1, bike1));
        };
    }
}
