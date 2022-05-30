package com.parkinggaragev3;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class Level {

    private final static int SPOTS_PER_ROW = 10;
    private int numOfSpots;
    private int lvlNum;
    private HashMap<String, Spot> level = new HashMap<>();

    public Level(int lvlNum, int numOfSpots){
        this.lvlNum = lvlNum;
        this.numOfSpots = numOfSpots;
        createSpots();
    }

    //Creates the spot by making the key the spot number and level EX: 1L14
    public void createSpots(){
        String spotNum = "";
        for(int i = 1; i <= numOfSpots; ++i){
            spotNum = lvlNum + "L" + i;
            level.put(spotNum, new Spot(spotNum));
        }
    }

    //Linear search for spots, returns the list of spots found and true or false
    public Pair<LinkedList<String>,Boolean> findSpots(Vehicle vehicle){
        String tempLicense = vehicle.getLicencePlate();
        int spotsNeeded = vehicle.getSize();
        LinkedList<String> listOfSpots = new LinkedList<>();
        int spotsFound = 0;
        int j = SPOTS_PER_ROW;
        //Searches through the level keeping track of the rows so the spots are in the same row
        for (int i = 1; i <= level.size(); ++i){
            String spotNum = lvlNum + "L" + i;
            //If the next spot isn't empty and the spots found isn't the amt needed they aren't consecutive
            if (!level.get(spotNum).isEmpty() && spotsFound != spotsNeeded){
                spotsFound = 0;
                listOfSpots.clear();
            }
            //Once we get to the end of the row, reset the numbers and clear the spots found
            if (j == 0){
                j = SPOTS_PER_ROW;
                spotsFound = 0;
                listOfSpots.clear();
            }
            //Once we find a spot we add it to the list of spots
            if (level.get(spotNum).isEmpty()){
                spotsFound++;
                listOfSpots.add(spotNum);
            }
            //Once spots found equal spots needed, park the vehicle in the spot with the license plate
            if (spotsFound == spotsNeeded){
                for (String spots : listOfSpots){
                    level.get(spots).parkVehicle(tempLicense);
                    System.out.println(tempLicense + " Parked in: " + spots);
                }
                return new Pair<>(listOfSpots,true);
            }
            --j;
        }
        System.out.println("No Spots Found");
        return new Pair<>(listOfSpots,false);
    }

    //Goes through the hashmap of every spot to find the matching license plate and will unpark
    public boolean unParkVehicle(Vehicle vehicle){
        String licenseNo = vehicle.getLicencePlate();
        String tempSpot = "";
        for(int i = 1; i <= level.size(); ++i){
            tempSpot = lvlNum + "L" + i;
            if (level.get(tempSpot).getParkedLicense().equals(licenseNo)) {
                level.get(tempSpot).unPark();
            }
        }
        return true;

    }

}
