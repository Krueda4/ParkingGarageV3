package com.parkinggaragev3;

public class Motorcycle extends Vehicle{
    final String vehicleType = "Motorcycle";

    public Motorcycle(String licensePlate){
        super(1);
        super.setVehicleType(vehicleType);
        super.setLicensePlate(licensePlate);
    }
}
