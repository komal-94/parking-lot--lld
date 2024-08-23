package org.example.parkingLot;

import org.example.models.ParkingSpot;
import org.example.models.VehicleSize;
import org.example.strategies.NearestSpotParkingStrategy;
import org.example.strategies.ParkingStrategy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingLot {
    private Map<VehicleSize, Map<String, PriorityQueue<ParkingSpot>>> parkingQueues;

    public ParkingLot(String[] entrances) {
        parkingQueues = new HashMap<>();

        for (VehicleSize vehicleSize: VehicleSize.values()) {
            parkingQueues.put(vehicleSize, new HashMap<>());
        }

        for (String entrance: entrances) {
            for (VehicleSize vehicleSize: VehicleSize.values()) {
                parkingQueues.get(vehicleSize).put(entrance, new PriorityQueue<>(
                        Comparator.comparingDouble(spot->spot.getDistanceFromEntrance(entrance))
                ));
            }
        }
    }

    public void addParkingSpot(ParkingSpot spot) {
        VehicleSize vehicleSize = spot.getVehicleSize();
        parkingQueues.get(vehicleSize).forEach((entrance, dist) -> {
            PriorityQueue<ParkingSpot> queue = parkingQueues.get(vehicleSize).get(entrance);
            if (queue != null) {
                queue.add(spot);
            }
        });
    }

    public ParkingStrategy getParkingStrategy() {
        return new NearestSpotParkingStrategy(parkingQueues);
    }
}
