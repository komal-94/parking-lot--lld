package org.example.models;

import lombok.Getter;

import java.util.Map;

@Getter
public class ParkingSpot {
    private String id;
    private VehicleSize vehicleSize;
    private boolean isOccupied;
    private Map<String, Float> distancesFromEntrance;

    public ParkingSpot(String id, VehicleSize vehicleSize, Map<String, Float> distancesFromEntrance) {
        this.id = id;
        this.vehicleSize = vehicleSize;
        this.distancesFromEntrance = distancesFromEntrance;
        this.isOccupied = false;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Float getDistanceFromEntrance(String entrance) {
        return distancesFromEntrance.getOrDefault(entrance, Float.MAX_VALUE);
    }
}
