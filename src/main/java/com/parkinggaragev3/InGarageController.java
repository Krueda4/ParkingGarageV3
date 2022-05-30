package com.parkinggaragev3;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

    public class InGarageController {

        Garage garage;

        @FXML
        private Button back_button;

        @FXML
        private TableView<Vehicle> license_plates_table;

    @FXML
    void go_back_button(ActionEvent event) throws IOException {
        //Sends you back to the park vehicles screen
        Stage stage = (Stage)  back_button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ParkVehiclesScreen.fxml"));
        Parent root = loader.load();
        ParkVehiclesController nextScene = loader.getController();
        //Pass the garage to the next stage
        nextScene.passGarage(garage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void populateList(){

        //Sends the parked vehicles string list to an observable list that can be used by FX
        ObservableList<Vehicle> parkedVehicles = FXCollections.observableList(garage.getParkedVehicles());

        license_plates_table.setItems(parkedVehicles);
        //Create the columns that hold a Vehicle string and set the titles
        TableColumn<Vehicle, String> licenseCol = new TableColumn<>("License");
        TableColumn<Vehicle, String> spotsCol = new TableColumn<>("Spots taken");
        TableColumn<Vehicle, String> vehicleType = new TableColumn<>("Vehicle Type");

        //For each vehicle in parkedVehicles set the column value to each vehicle
        for (int i = 0; i < garage.getParkedVehicles().size(); ++i){
            licenseCol.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLicencePlate()));
            vehicleType.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getVehicleType()));
            spotsCol.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getSpotsToString()));
        }
        //Set the columns into the tableview
        license_plates_table.getColumns().setAll(licenseCol,vehicleType,spotsCol);

    }

    //Sets the garage
    public void setGarage(Garage garage){
        this.garage = garage;
        populateList();
    }

}
