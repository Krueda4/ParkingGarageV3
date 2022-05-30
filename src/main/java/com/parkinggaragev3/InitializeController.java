package com.parkinggaragev3;

import javafx.scene.control.ComboBox;

public class InitializeController {

    ComboBox<String> comboBox = new ComboBox<>();

    public InitializeController(ComboBox box){
        comboBox = box;
    }

//    public void initalize(){
//        vehicle_type_combo.getItems().clear();
//        vehicle_type_combo.getItems().addAll("Motorcycle","Compact","Truck","Bus");
//    }
}
