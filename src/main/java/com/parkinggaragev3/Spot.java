package com.parkinggaragev3;

public class Spot {
    private String parkedLicense = "";
    private String spotNumber;
    private boolean isEmpty = true;

    //Spot constructor just needs the spot number
    public Spot(String spotNumber){
        this.spotNumber = spotNumber;
    }

    //Spot will hold the vehicle license and makes the spot not empty when parked in
    public void parkVehicle(String license){
        parkedLicense = license;
        isEmpty = false;
    }

    //unParking returns license to null and becomes empty
    public void unPark(){
        parkedLicense = "";
        isEmpty = true;
    }

    public String getParkedLicense(){return parkedLicense;}
    public String getSpotNumber(){return spotNumber;}
    public boolean isEmpty(){return isEmpty;}


}
