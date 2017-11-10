package wire.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import wire.logic.*;

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

    private double amperage;
    private double lengthOfWire;
    private double startTemperatureOfWire;
    private double diameterOfWire;

    public void initialize() {
        calculateButton();
    }

    public void calculateButton() {
        ErrorTransferInputData errorTransferInputData = transferInputData();

        if (errorTransferInputData == ErrorTransferInputData.ALL_IS_WELL) {
            setGoodMessage(errorTransferInputData.getMessage());
            drawWire();
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
