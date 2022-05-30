package com.parkinggaragev3;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkVehiclesController implements Initializable {

    Garage garage;

    @FXML
    private Button view_parked_button;

    @FXML
    private Label already_parked_label;

    @FXML
    private Label parked_success_label;

    @FXML
    private TextField license_field;

    @FXML
    private Label license_min_label;

    @FXML
    private Label not_parked_label;

    @FXML
    private Label null_vehicle_label;

    @FXML
    private ImageView recipt_image;
    //can't spell recipt
    @FXML
    private Label recipt_label;

    @FXML
    private ComboBox<?> vehicle_type_combo;

    //Retrieves the garage from another scene
    public void passGarage(Garage garage){
        this.garage = garage;
    }

    @FXML
    void back_out_button(ActionEvent event) {

        //gets information from type field and license plate
        String vehicleType = (String) vehicle_type_combo.getSelectionModel().getSelectedItem();
        String licensePlate = license_field.getText();

        //checks if the license plate is valid
        if (!checkLicense(licensePlate)){
            license_min_label.setVisible(true);
            not_parked_label.setVisible(false);
            null_vehicle_label.setVisible(false);
            already_parked_label.setVisible(false);
            parked_success_label.setVisible(false);
        }
        //Check if there was a vehicle chosen
        else if (vehicleType == null){
            null_vehicle_label.setVisible(true);
            license_min_label.setVisible(false);
            not_parked_label.setVisible(false);
            already_parked_label.setVisible(false);
            parked_success_label.setVisible(false);
        }
        else{
            //Depending on the vehicle type add it to the garage
            if (vehicleType.equals("Motorcycle")){
                Motorcycle motorcycle = new Motorcycle(licensePlate);
                notParkedCheck(motorcycle);
            }
            else if (vehicleType.equals("Compact")){
                Compact compact = new Compact(licensePlate);
                notParkedCheck(compact);
            }
            else if (vehicleType.equals("Truck")){
                Truck truck = new Truck(licensePlate);
                notParkedCheck(truck);
            }
            else if (vehicleType.equals("Bus")){
                Bus bus = new Bus(licensePlate);
                notParkedCheck(bus);
            }

        }

    }

    //When backing out, checks if the car is even parked but also if there is any space available
    public void notParkedCheck(Vehicle vehicle){
        Pair<String,Boolean> values = garage.unparkVehicle(vehicle);
        //Shows label if fail
        if (!values.getValue()){
            not_parked_label.setVisible(true);
            parked_success_label.setVisible(false);
        }
        //If it wasn't failed show the recipt of the vehicle that was backed out
        else {
            recipt_label.textProperty().bind(Bindings.format(values.getKey()));
            recipt_image.setVisible(true);
            recipt_label.setVisible(true);
            parked_success_label.setVisible(false);
        }
    }

    @FXML
    void park_vehicle(ActionEvent event) {
        recipt_label.setVisible(false);
        recipt_image.setVisible(false);

        //Retrieve the vehicle choice and license field
        String vehicleType = (String) vehicle_type_combo.getSelectionModel().getSelectedItem();
        String licensePlate = license_field.getText();

        //First check if the license is correct
        if (!checkLicense(licensePlate)){
            license_min_label.setVisible(true);
            not_parked_label.setVisible(false);
            null_vehicle_label.setVisible(false);
            already_parked_label.setVisible(false);
            parked_success_label.setVisible(false);
        }
        //Second check if the vehicle type has been chosen
        else if (vehicleType == null){
            null_vehicle_label.setVisible(true);
            license_min_label.setVisible(false);
            not_parked_label.setVisible(false);
            already_parked_label.setVisible(false);
            parked_success_label.setVisible(false);
        }
        //Parameters checked to create a vehicle
        else {
            license_min_label.setVisible(false);
            null_vehicle_label.setVisible(false);
            not_parked_label.setVisible(false);
            already_parked_label.setVisible(false);
            parked_success_label.setVisible(false);

            //Depending on the vehicle type chosen, unpark it in the garage
            if (vehicleType.equals("Motorcycle")){
                Motorcycle motorcycle = new Motorcycle(licensePlate);
                alreadyParkedVisible(motorcycle);
            }
            else if (vehicleType.equals("Compact")){
                Compact compact = new Compact(licensePlate);
                alreadyParkedVisible(compact);
            }
            else if (vehicleType.equals("Truck")){
                Truck truck = new Truck(licensePlate);
                alreadyParkedVisible(truck);
            }
            else if (vehicleType.equals("Bus")){
                Bus bus = new Bus(licensePlate);
                alreadyParkedVisible(bus);
            }

        }
    }

    //If vehicle is already parked show the proper label, if not it will be parked
    public void alreadyParkedVisible(Vehicle vehicle){
        if (!this.garage.parkVehicle(vehicle)){
            already_parked_label.setVisible(true);
            parked_success_label.setVisible(false);
        }
        parked_success_label.setVisible(true);
    }

    @FXML
    void view_parked_vehicles(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        stage = (Stage) view_parked_button.getScene().getWindow();
        stage.close();
        //Loads the new window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InGarageScreen.fxml"));
        Parent root = loader.load();
        InGarageController nextScene = loader.getController();
        //Sends current garage to the next scene
        nextScene.setGarage(this.garage);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    //Sets the license plate with a length of 3-7 characters no special characters
    public boolean checkLicense(String licencePlate){
        String regex = "^[A-Z0-9]{3,8}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(licencePlate);

        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
