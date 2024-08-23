package org.example.services;

import org.example.models.ParkingSpot;
import org.example.models.Vehicle;

public interface ParkingService {
    ParkingSpot parkVehicle(Vehicle vehicle, String entrance);
    void exitVehicle(ParkingSpot parkingSpot, String entrance);
}
