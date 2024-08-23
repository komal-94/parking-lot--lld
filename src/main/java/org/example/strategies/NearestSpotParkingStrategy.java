package org.example.strategies;

import org.example.models.ParkingSpot;
import org.example.models.Vehicle;
import org.example.models.VehicleSize;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class NearestSpotParkingStrategy implements ParkingStrategy{
    private Map<VehicleSize, Integer> vacantSpots;
    private Map<VehicleSize, Map<String, PriorityQueue<ParkingSpot>>> parkingQueues;

    public NearestSpotParkingStrategy(Map<VehicleSize, Map<String, PriorityQueue<ParkingSpot>>> parkingQueues) {
        this.parkingQueues = parkingQueues;
        this.vacantSpots = new HashMap<>();
        for (VehicleSize vehicleSize : VehicleSize.values()) {
            System.out.println("do do:" + parkingQueues.get(vehicleSize).values().stream());
//            ask:
            vacantSpots.put(vehicleSize, parkingQueues.get(vehicleSize).values().stream().mapToInt(PriorityQueue::size).sum());
        }
    }

    @Override
    public Optional<ParkingSpot> findSpot(VehicleSize vehicleSize, String entrance) {
        PriorityQueue parkingQueue = parkingQueues.get(vehicleSize).get(entrance);

        if (parkingQueue == null || parkingQueue.isEmpty()) {
            return Optional.empty();
        }
        ParkingSpot spot = (ParkingSpot) parkingQueue.poll();
        if (spot != null) {
            removeSpotFromAllEntrances(spot);
            vacantSpots.put(vehicleSize, vacantSpots.get(vehicleSize) - 1);
        }
//        ask:
        return Optional.ofNullable(spot);
    }

    @Override
    public void releaseSpot(ParkingSpot parkingSpot, String entrance) {
        VehicleSize vehicleSize = parkingSpot.getVehicleSize();
        PriorityQueue<ParkingSpot> parkingQueue = parkingQueues.get(vehicleSize).get(entrance);
        parkingQueue.add(parkingSpot);
        vacantSpots.put(vehicleSize, vacantSpots.get(vehicleSize) + 1);
    }

    private void removeSpotFromAllEntrances(ParkingSpot spot) {
        VehicleSize size = spot.getVehicleSize();
        parkingQueues.get(size).forEach((entrance, queue) -> queue.remove(spot));
    }

    public int getVacantSpots(VehicleSize vehicleSize) {
        return vacantSpots.getOrDefault(vehicleSize, 0);
    }
}
