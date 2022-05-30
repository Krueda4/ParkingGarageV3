package com.parkinggaragev3;

public class Truck extends Vehicle{
    private final String vehicleType = "Truck";

    public Truck(String licensePlate){
        super(2);
        super.setVehicleType(vehicleType);
        super.setLicensePlate(licensePlate);
    }
}
