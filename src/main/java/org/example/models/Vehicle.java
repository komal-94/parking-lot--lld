package org.example.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

//@Data
@AllArgsConstructor
@Getter
public class Vehicle {
    private String plateNo;
    private VehicleSize vehicleSize;
}
