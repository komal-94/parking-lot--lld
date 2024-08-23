package org.example.services;

import org.example.models.ParkingSpot;
import org.example.models.Vehicle;
import org.example.models.VehicleSize;
import org.example.strategies.ParkingStrategy;

import java.util.Optional;

public class ParkingServiceImpl implements ParkingService {
    private static ParkingServiceImpl instance;
    private ParkingStrategy parkingStrategy;

    public ParkingServiceImpl(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public static ParkingServiceImpl getInstance(ParkingStrategy parkingStrategy) {
        if (instance == null) {
            // ask:
            synchronized (ParkingServiceImpl.class) {
                instance = new ParkingServiceImpl(parkingStrategy);
            }
        }
        return instance;
    }

    @Override
    public ParkingSpot parkVehicle(Vehicle vehicle, String entrance) {
        VehicleSize vehicleSize = vehicle.getVehicleSize();
        Optional<ParkingSpot> spotOptional = parkingStrategy.findSpot(vehicleSize, entrance);
        if (spotOptional.isPresent()) {
            ParkingSpot spot = spotOptional.get();
            System.out.println("Spot details:"+ spot.getId() + " " + spot.getDistancesFromEntrance());
            spot.setOccupied(true);
            return spot;
        }
        throw new RuntimeException("Parking spot not available");
    }

    @Override
    public void exitVehicle(ParkingSpot parkingSpot, String entrance) {
        parkingSpot.setOccupied(false);
        System.out.println("Exit vehicle parking:"+ parkingSpot.getId());
        parkingStrategy.releaseSpot(parkingSpot, entrance);
    }
}
