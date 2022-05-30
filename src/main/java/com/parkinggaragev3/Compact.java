package com.parkinggaragev3;

public class Compact extends Vehicle{
    private final String vehicleType = "Compact";

    public Compact(String licensePlate){
        super(1);
        super.setVehicleType(vehicleType);
        super.setLicensePlate(licensePlate);
    }
}
