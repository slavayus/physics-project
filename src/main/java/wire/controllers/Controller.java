package wire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    public TextField resistanceTextField;

    @FXML
    public TextField amperageTextField;

    @FXML
    public TextField lengthOfWireTextField;

    @FXML
    public TextField temperatureOfWireTextField;

    @FXML
    public TextField temperatureOfTheAirTextField;

    @FXML
    public TextField diameterOfWireTextField;

    public void calculateButton() {
        System.out.println("YEE");

    }
}
