package wire.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;
import wire.logic.Temperature;
import wire.logic.Checker;
import wire.logic.ErrorCheckInputData;
import wire.logic.ErrorTransferInputData;

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
    public Rectangle wire;

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
    private Task<Void> task;

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
        Checker checker = new Checker(startTemperatureOfWire, amperage, diameterOfWire, lengthOfWire);
        ErrorCheckInputData errorCheckInputData = checker.checkInputData();
        if (errorCheckInputData == ErrorCheckInputData.ALL_IS_WELL) {
            drawWire();
        } else {
            setErrorMessage(errorCheckInputData.getMessage());
            dropWire();
        }
    }

    private void drawWire() {
        setWireHeight();

        if (task != null) {
            task.cancel();
        }

        task = new Temperature(dropShadowForWire, amperage, lengthOfWire, startTemperatureOfWire, diameterOfWire);
        currentTemperatureLabel.textProperty().bind(task.messageProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void setWireHeight() {
        if (diameterOfWire <= 0.01) {
            wire.setHeight(1);
        }else{
            if(diameterOfWire<1){
                wire.setHeight(50*diameterOfWire);
            }else {
                wire.setHeight(50);
            }
        }

    }

    private void dropWire() {
        System.out.println("YEE");
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
