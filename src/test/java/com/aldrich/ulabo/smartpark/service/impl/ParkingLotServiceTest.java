package com.aldrich.ulabo.smartpark.service.impl;

import com.aldrich.ulabo.smartpark.entity.ParkingLot;
import com.aldrich.ulabo.smartpark.repository.ParkingLotRepo;
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
public class ParkingLotServiceTest {

    @InjectMocks
    private ParkingLotServiceImpl parkingLotService;

    @Mock
    private ParkingLotRepo parkingLotRepo;

    @Test
    void registerParkingLot() {
        ParkingLot mockParkingLot = new ParkingLot("LOT001", "MOA", 10, 0);
        Mockito.when(parkingLotRepo.save(Mockito.any(ParkingLot.class))).thenReturn(mockParkingLot);

        ParkingLot parkingLot = parkingLotService.registerParkingLot(new ParkingLot());

        Assertions.assertNotNull(parkingLot);
        Assertions.assertEquals("LOT001", parkingLot.getLotId());
        Assertions.assertEquals("MOA", parkingLot.getLocation());
        Assertions.assertEquals(10, parkingLot.getCapacity());
        Assertions.assertEquals(0, parkingLot.getOccupiedSpace());
    }

    @Test
    void getParkingLots() {
        List<ParkingLot> parkingLots = List.of(
                new ParkingLot("LOT001", "Megamall", 20, 0),
                new ParkingLot("LOT002", "Moa", 10, 0)
        );
        Mockito.when(parkingLotRepo.findAll()).thenReturn(parkingLots);

        List<ParkingLot> actual = parkingLotService.getParkingLots();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void getParkingLotById() {
        ParkingLot parkingLot = new ParkingLot("LOT001", "MOA", 10, 0);
        Mockito.when(parkingLotRepo.findById("LOT001")).thenReturn(Optional.of(parkingLot));

        Optional<ParkingLot> actual = parkingLotService.getParkingLotById("LOT001");

        Assertions.assertNotNull(actual);
    }
}
