package wire.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import wire.logic.Checker;
import wire.logic.ErrorCheckInputData;
import wire.logic.ErrorTransferInputData;

import static java.lang.StrictMath.*;

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
    public Label messageInputData;

    @FXML
    public DropShadow dropShadowForWire;

    private double resistance;
    private double amperage;
    private double lengthOfWire;
    private double startTemperatureOfWire;
    private double temperatureOfTheAir;
    private double diameterOfWire;


    public void initialize() {
        calculateButton();
    }

    public void calculateButton() {
        ErrorTransferInputData errorTransferInputData = transferInputData();

        if (errorTransferInputData == ErrorTransferInputData.ALL_IS_WELL) {
            setGoodMessage(errorTransferInputData.getMessage());
            doRefactorOfWire();
        } else {
            setErrorMessage(errorTransferInputData.getMessage());
        }
    }

    private void doRefactorOfWire() {
        Checker checker = new Checker(startTemperatureOfWire, amperage, diameterOfWire, resistance);
        ErrorCheckInputData errorCheckInputData = checker.checkInputData();
        if (errorCheckInputData == ErrorCheckInputData.ALL_IS_WELL) {
            drawWire();
        } else {
            setErrorMessage(errorCheckInputData.getMessage());
            dropWire();
        }
    }


    private void drawWire() {
        wire.setHeight(55.55*diameterOfWire);
        if (startTemperatureOfWire >= 20) {
            dropShadowForWire.setRadius(startTemperatureOfWire - 18);
            int colorValue = (int) (50 + (startTemperatureOfWire - 20) * 4.1);
            dropShadowForWire.setColor(Color.rgb(colorValue, 0, 0, 1));
        } else {
            dropShadowForWire.setRadius(2);
            dropShadowForWire.setColor(Color.rgb(10, 0, 0, 1));
        }
    }

    private void dropWire() {
        System.out.println("YEE");
    }

    private ErrorTransferInputData transferInputData() {
        ErrorTransferInputData errorTransferInputData = ErrorTransferInputData.ALL_IS_WELL;
        try {
            resistance = Double.parseDouble(resistanceTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_RESISTANCE;
        }

        try {
            amperage = Double.parseDouble(amperageTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_AMPERAGE;
        }

        try {
            lengthOfWire = Double.parseDouble(lengthOfWireTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_LENGTH_OF_WIRE;
        }

        try {
            startTemperatureOfWire = Double.parseDouble(temperatureOfWireTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_TEMPERATURE_OF_WIRE;
        }

        try {
            temperatureOfTheAir = Double.parseDouble(temperatureOfTheAirTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_TEMPERATURE_OF_THE_AIR;
        }

        try {
            diameterOfWire = Double.parseDouble(diameterOfWireTextField.getText());
        } catch (Exception ex) {
            errorTransferInputData = ErrorTransferInputData.ERROR_DIAMETER_OF_WIRE;
        }

        return errorTransferInputData;

    }

    private void setErrorMessage(String errorMessage) {
        messageInputData.setStyle("-fx-text-fill: red");
        messageInputData.setText(errorMessage);
    }

    private void setGoodMessage(String goodMessage) {
        messageInputData.setStyle("-fx-text-fill: #4E7546");
        messageInputData.setText(goodMessage);
    }
}
