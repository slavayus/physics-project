package wire.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;
import wire.logic.*;
import wire.logic.checker.PhysicsConstantsChecker;
import wire.logic.constants.ErrorCheckPhysicsConstants;
import wire.logic.constants.ErrorTransferInputData;
import wire.logic.constants.ErrorTransferPhysicsConstants;
import wire.logic.constants.PhysicsConstants;

public class Controller {

    @FXML
    public TextField amperageTextField;

    @FXML
    public TextField lengthOfWireTextField;

    @FXML
    public TextField temperatureOfWireTextField;

    @FXML
    public TextField diameterOfWireTextField;

    @FXML
    public Rectangle wireRectangle;

    @FXML
    public Label messageInputData;

    @FXML
    public DropShadow dropShadowForWire;

    @FXML
    public Label currentTemperatureLabel;

    @FXML
    public TextField maxTemperatureOfWireTextField;

    @FXML
    public TextField minTemperatureOfWireTextField;

    @FXML
    public TextField specificHeat;

    @FXML
    public TextField resistanceIn20Degrees;

    @FXML
    public TextField temperatureCoefficientOfResistance;

    @FXML
    public TextField density;

    private double amperage;
    private double lengthOfWire;
    private double startTemperatureOfWire;
    private double diameterOfWire;

    public void initialize() {
        calculateButton();
    }

    public void calculateButton() {
        ErrorTransferInputData errorTransferInputData = transferInputData();
        ErrorTransferPhysicsConstants errorTransferPhysicsConstants = initializePhysicsConstants();

        if (errorTransferInputData == ErrorTransferInputData.ALL_IS_WELL) {
            if (errorTransferPhysicsConstants == ErrorTransferPhysicsConstants.ALL_IS_WELL) {
                ErrorCheckPhysicsConstants errorCheckPhysicsConstants = PhysicsConstantsChecker.checkData(PhysicsConstants.COPPER);
                if (ErrorCheckPhysicsConstants.ALL_IS_WELL == errorCheckPhysicsConstants) {
                    setGoodMessage(errorTransferPhysicsConstants.getMessage());
                    drawWire();
                } else {
                    setErrorMessage(errorCheckPhysicsConstants.getMessage());
                }
            } else {
                setErrorMessage(errorTransferPhysicsConstants.getMessage());
            }
        } else {
            setErrorMessage(errorTransferInputData.getMessage());
        }
    }

    private Task<Void> task;

    private void drawWire() {
        if (task != null) {
            task.cancel();
        }

        task = new Wire(dropShadowForWire, amperage, lengthOfWire, startTemperatureOfWire, diameterOfWire, PhysicsConstants.COPPER, wireRectangle, messageInputData);
        currentTemperatureLabel.textProperty().bind(task.messageProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private ErrorTransferPhysicsConstants initializePhysicsConstants() {
        ErrorTransferPhysicsConstants errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ALL_IS_WELL;

        try {
            PhysicsConstants.COPPER.setMaxTemperature(Double.parseDouble(maxTemperatureOfWireTextField.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_MAX_TEMPERATURE;
        }

        try {
            PhysicsConstants.COPPER.setMinTemperature(Double.parseDouble(minTemperatureOfWireTextField.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_MIN_TEMPERATURE;
        }

        try {
            PhysicsConstants.COPPER.setSpecificHeat(Double.parseDouble(specificHeat.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_SPECIFIC_HEAT;
        }

        try {
            PhysicsConstants.COPPER.setResistanceIn20Degrees(Double.parseDouble(resistanceIn20Degrees.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_RESISTANCE_IN_20_DEGREES;
        }

        try {
            PhysicsConstants.COPPER.setTemperatureCoefficientOfResistance(Double.parseDouble(temperatureCoefficientOfResistance.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE;
        }

        try {
            PhysicsConstants.COPPER.setDensity(Double.parseDouble(density.getText()));
        } catch (Exception ex) {
            errorTransferPhysicsConstants = ErrorTransferPhysicsConstants.ERROR_DENSITY;
        }

        return errorTransferPhysicsConstants;
    }

    private ErrorTransferInputData transferInputData() {
        ErrorTransferInputData errorTransferInputData = ErrorTransferInputData.ALL_IS_WELL;

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
