package wire.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

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

    @FXML
    public Rectangle wire;

    @FXML
    public Label messageCheckInputData;

    private double resistance;
    private double amperage;
    private double lengthOfWire;
    private double temperatureOfWire;
    private double temperatureOfTheAir;
    private double diameterOfWire;


    public void calculateButton() {
        ErrorInputData errorInputData = checkInputData();

        if (errorInputData == ErrorInputData.ALL_IS_WELL) {
            messageCheckInputData.setStyle("-fx-text-fill: #4E7546");
            messageCheckInputData.setText(errorInputData.getMessage());
//            drawChart();
        } else {
            messageCheckInputData.setStyle("-fx-text-fill: red");
            messageCheckInputData.setText(errorInputData.getMessage());
        }
    }

    private ErrorInputData checkInputData() {
        ErrorInputData errorInputData = ErrorInputData.ALL_IS_WELL;
        try {
            resistance = Double.parseDouble(resistanceTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_RESISTANCE;
        }

        try {
            amperage = Double.parseDouble(amperageTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_AMPERAGE;
        }

        try {
            lengthOfWire = Double.parseDouble(lengthOfWireTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_LENGTH_OF_WIRE;
        }

        try {
            temperatureOfWire= Double.parseDouble(temperatureOfWireTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_TEMPERATURE_OF_WIRE;
        }

        try {
            temperatureOfTheAir = Double.parseDouble(temperatureOfTheAirTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_TEMPERATURE_OF_THE_AIR;
        }

        try {
            diameterOfWire = Double.parseDouble(diameterOfWireTextField.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_DIAMETER_OF_WIRE;
        }

        return errorInputData;

    }
}
