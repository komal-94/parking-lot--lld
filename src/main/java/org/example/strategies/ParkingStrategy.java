package org.example.strategies;

import org.example.models.ParkingSpot;
import org.example.models.Vehicle;
import org.example.models.VehicleSize;

import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(VehicleSize vehicleSize, String entrance);
    void releaseSpot(ParkingSpot parkingSpot, String entrance);
}
