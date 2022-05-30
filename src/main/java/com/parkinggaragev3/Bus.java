package com.parkinggaragev3;

public class Bus extends Vehicle{
    private final String vehicleType = "Bus";

    public Bus(String licensePlate){
        super(5);
        super.setVehicleType("Bus");
        super.setLicensePlate(licensePlate);
    }
}
