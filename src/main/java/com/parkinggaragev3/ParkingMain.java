package com.parkinggaragev3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
Parking Garage Project
Author : Kevin Rueda
Date Completed : 4/7/2022

Garage Mahal Parking Garage
-Has 4 Classes of Vehicles : Motorcycle, Compact, Truck, Bus
-Each class needs a certain num of spaces : 1, 1, 2, 5

-Parking garage has rows of 10 and the spots per level must be a multiple of 10 up to 90
-Can make up to 99 Levels

-Parking Vehicles must be in consecutive spots
-License plate format is 3-8 Chars of Alpha or Numeric Integers for consistency, no hyphen or spaces
-Unparking vehicles removes the vehicles from the spots and takes it out of the parked vehicles array
-Must be same license and type of vehicle when unparking
-When unparking it will also return the ticket of that vehicle with the fee included

-UI Shows the currently parked car's license, type and spots taken in a table view
 */

public class ParkingMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ParkingMain.class.getResource("EntranceScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Garage Mahal!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
