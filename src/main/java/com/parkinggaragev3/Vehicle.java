package com.parkinggaragev3;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Vehicle {
    private String licencePlate;
    private String vehicleType;
    private int size;
    private static final double FEE_PER_MINUTE = 3.75;
    private double feeCharged;
    LinkedList<String> spotsTaken = new LinkedList<>();
    private LocalDateTime timeIN;
    private LocalDateTime timeOUT;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Vehicle(int size){
        this.size = size;
        timeIN = LocalDateTime.now();
    }

    //Sets the license plate with a length of 3-7 characters no special characters
    public void setLicensePlate(String licencePlate){
        this.licencePlate = licencePlate;
    }

    //Sets vehicle type to those allowed = Motorcycle, Compact, Truck, Bus
    public void setVehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }
    //Adds the whole linked list to the vehicle's list of spots taken
    public void setSpotsTaken(LinkedList<String> spotsTaken){
        this.spotsTaken.addAll(spotsTaken);
    }
    //When leaving, set the leave time and charge the fee per minute, per slot taken
    public void leave(){
        timeOUT = LocalDateTime.now();
        Duration totalTime = Duration.between(timeIN,timeOUT);

        this.feeCharged = (totalTime.toMinutes() * FEE_PER_MINUTE) * size;
        //this doesn't need to be cleared since the garage clears the vehicle anyways
        spotsTaken.clear();
    }

    //creates a recipt string that shows all the information needed
    public String receiptToString(){
        String tempTimeIN = formatter.format(timeIN);
        String tempTimeOUT = formatter.format(timeOUT);

        return "License: "+licencePlate+"\nSpots Taken: "+size+"\nFee per minute/spot: " +FEE_PER_MINUTE+ "\nTime In: " +tempTimeIN+ "\nTime Out: " +tempTimeOUT+ "\nTotal: " +feeCharged;
    }

    //Returns just a string of the spots taken
    public String getSpotsToString(){
        String spots = "";
        for (String spot : spotsTaken){
            spots = spots + spot + ",";
        }
        return spots;
    }

    public LinkedList<String> getSpotsTaken(){return spotsTaken;}
    public int getSize(){return size;}
    public String getLicencePlate(){return licencePlate;}
    public String getVehicleType(){return vehicleType;}
}
