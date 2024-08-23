package org.example;

import org.example.models.ParkingSpot;
import org.example.models.Vehicle;
import org.example.models.VehicleSize;
import org.example.parkingLot.ParkingLot;
import org.example.services.ParkingService;
import org.example.services.ParkingServiceImpl;
import org.example.strategies.NearestSpotParkingStrategy;
import org.example.strategies.ParkingStrategy;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] entrances = {"A", "B"};

        ParkingLot parkingLot = new ParkingLot(entrances);

        Map<String, Float> distancesForSpot1 = new HashMap<>();
        distancesForSpot1.put("A", 10f);
        distancesForSpot1.put("B", 5f);

        ParkingSpot parkingSpot1 = new ParkingSpot("P1", VehicleSize.SMALL, distancesForSpot1);

        Map<String, Float> distancesForSpot2 = new HashMap<>();
        distancesForSpot2.put("A", 1f);
        distancesForSpot2.put("B", 3f);

        ParkingSpot parkingSpot2 = new ParkingSpot("P2", VehicleSize.MEDIUM, distancesForSpot2);

        parkingLot.addParkingSpot(parkingSpot1);
        parkingLot.addParkingSpot(parkingSpot2);

        ParkingStrategy parkingStrategy = parkingLot.getParkingStrategy();
        ParkingService parkingService = ParkingServiceImpl.getInstance(parkingStrategy);

        Vehicle vehicle = new Vehicle("MH12MX1247", VehicleSize.SMALL);
        ParkingSpot p1 = parkingService.parkVehicle(vehicle, "A");

        Vehicle vehicle2 = new Vehicle("MH12MX1247", VehicleSize.SMALL);
        ParkingSpot p2 = parkingService.parkVehicle(vehicle2, "B");
//        parkingService.exitVehicle(p1, "A");

    }
}