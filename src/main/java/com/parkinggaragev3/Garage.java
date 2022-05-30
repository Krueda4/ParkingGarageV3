package com.parkinggaragev3;

import javafx.util.Pair;

import java.util.LinkedList;

public class Garage {

    private int numOfLvls;
    private int totalSpots;
    private LinkedList<Level> levels = new LinkedList<>();
    private LinkedList<Vehicle> parkedVehicles = new LinkedList<>();


    public Garage(){}

    //Set num of levels
    public void setNumOfLvls(int numOfLvls){
        this.numOfLvls = numOfLvls;
    }

    //Set total spots with number per level and create them
    public void setSpots(int spotsPerLvl){
        this.totalSpots = spotsPerLvl * numOfLvls;
        createLevels(spotsPerLvl);
    }

    //Creates as many levels as user wants with specified spots per level
    public void createLevels(int spotsPerLvl){
        for (int i = 1; i <= numOfLvls; ++i){
            levels.add(new Level(i,spotsPerLvl));
        }
    }

    public boolean parkVehicle(Vehicle vehicle){
        //First check if the vehicle is already parked
        Pair<Integer,Boolean> values = checkParkedList(vehicle);
        if (values.getValue()){
            //Already parked so we can't park again
            System.out.println("Car already parked");
            return false;
        }
        //Go through the levels to find a spot. If a spot is found add it to parked vehicles list and return true;
        for (int i = 0; i < numOfLvls; ++i){
            Pair<LinkedList<String>,Boolean> valuesSpot = levels.get(i).findSpots(vehicle);
            if (valuesSpot.getValue()){
                System.out.println("Spots found");
                parkedVehicles.add(vehicle);
                parkedVehicles.get(parkedVehicles.size()-1).setSpotsTaken(valuesSpot.getKey());
                return true;
            }
        }
        System.out.println("No Spots found.");
        return false;
    }


    public Pair<String, Boolean> unparkVehicle(Vehicle vehicle){
        //First check if the vehicle is parked in the first place
        Pair<Integer, Boolean> values = checkParkedList(vehicle);
        if (!values.getValue()){
            System.out.println("Car isn't parked.");
            return new Pair<>("",false);
        }
        //Car is validated that it is parked
        //Check if the vehicle passed is the same class of vehicle to get the correct ticket
        if (!parkedVehicles.get(values.getKey()).getVehicleType().equals(vehicle.getVehicleType())){
            System.out.println("Vehicle type doesn't match.");
            return new Pair<>("",false);
        }
        //Hold ticket as string, and unpark the vehicle from level and parkedvehicles list
        String ticket = getVehicleTicket(vehicle);
        for (int i = 0; i < numOfLvls; ++i){
            if (levels.get(i).unParkVehicle(vehicle)){
                removeParkedCar(vehicle);
                System.out.println("Vehicle left.");
            }
        }
        return new Pair<>(ticket,true);
    }

    //For unParking Vehicle, gets the ticket by making the vehicle leave
    //.leave for the vehicle creates the fee
    public String getVehicleTicket(Vehicle vehicle){
        String ticket = "";
        for (Vehicle parkedObj : parkedVehicles){
            if (parkedObj.getLicencePlate().equals(vehicle.getLicencePlate())){
                parkedObj.leave();
                ticket = parkedObj.receiptToString();
                return ticket;
            }
        }
        return ticket;
    }

    //Returns the index of the vehicle parked in parkedVehicles list and if the vehicle is even in the list
    public Pair<Integer, Boolean> checkParkedList(Vehicle vehicle){
        String licenseNo = vehicle.getLicencePlate();
        for (int i = 0; i < parkedVehicles.size(); ++i){
            if (parkedVehicles.get(i).getLicencePlate().equals(licenseNo)){
                return new Pair<>(i,true);
            }
        }
        return new Pair<>(-1,false);
    }

    //If the car is parked it will be removed, if not it will return false
    public boolean removeParkedCar(Vehicle vehicle){
        String licenseNo = vehicle.getLicencePlate();

        for(int i = 0; i < parkedVehicles.size(); ++i){
            if (parkedVehicles.get(i).getLicencePlate().equals(licenseNo)){
                parkedVehicles.remove(i);
                System.out.println("Car successfully removed.");
                return true;
            }
        }
        System.out.println("Car isn't parked.");
        return false;
    }

    public LinkedList<Vehicle> getParkedVehicles(){return parkedVehicles;}

}
