package com.parkinggaragev3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntranceController {

    Garage garage = new Garage();

    @FXML
    private Button entrance_button;

    @FXML
    private Label error_label;

    @FXML
    private Label error_label2;

    @FXML
    private TextField floors_field;

    @FXML
    private TextField spots_field;


    @FXML
    void enter_button(ActionEvent event) throws IOException {
        //Checks if the fields are empty
        if (floors_field.getText().isEmpty() || spots_field.getText().isEmpty()){
            error_label.setVisible(false);
            error_label2.setVisible(true);
        }

        //Check if the String is only a number
        if (numCheck(floors_field.getText()) && numCheck(spots_field.getText())){
            //Parses string to an integer
            int floors = Integer.parseInt(floors_field.getText());
            int spots = Integer.parseInt(spots_field.getText());


            //Spots has to be a multiple of 10 & can't be 0
            if (spots%10 == 0 && floors != 0 && spots != 0){
                //Create the garage with the given parameters
                this.garage.setNumOfLvls(floors);
                this.garage.setSpots(spots);

                Stage stage = (Stage)  entrance_button.getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ParkVehiclesScreen.fxml"));
                Parent root = loader.load();
                ParkVehiclesController nextScene = loader.getController();
                //Pass the created garage to the next scene
                nextScene.passGarage(garage);
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            }
        }
        error_label.setVisible(true);
    }



    public boolean numCheck(String value){
        String regex = "\\d{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

}
