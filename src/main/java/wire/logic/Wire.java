package wire.logic;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import wire.logic.DropWire.Destroy;
import wire.logic.DropWire.WaterDrop;
import wire.logic.checker.InputDataChecker;
import wire.logic.constants.ErrorCheckInputData;
import wire.logic.constants.PhysicsConstants;

import java.io.IOException;

public class Wire extends Task<Void> {

    private final DropShadow dropShadowForWire;
    private final Rectangle wireRectangle;
    private final double amperage;
    private final double lengthOfWire;
    private final double diameterOfWire;
    private final double startTemperatureOfWire;
    private final PhysicsConstants material;


    public Wire(DropShadow dropShadowForWire, double amperage, double lengthOfWire, double startTemperatureOfWire, double diameterOfWire, PhysicsConstants material, Rectangle wireRectangle, Label messageInputData) {
        this.dropShadowForWire = dropShadowForWire;
        this.wireRectangle = wireRectangle;
        this.amperage = amperage;
        this.lengthOfWire = lengthOfWire;
        this.diameterOfWire = diameterOfWire;
        this.startTemperatureOfWire = startTemperatureOfWire;
        this.material = material;

        this.setOnFailed(workerStateEvent -> {
            setErrorMessage(this.getException().getMessage(), messageInputData);
            try {
                dropWire();
            } catch (IOException e) {
                System.out.println("YEE");
            }
        });

    }


    @Override
    protected Void call() throws WireException {
        setWireHeight(diameterOfWire, wireRectangle);

        double currentTemperature = startTemperatureOfWire;

        ErrorCheckInputData errorCheckInputData;

        double thermalConductivity = calculateThermalConductivity();

        Temperature temperature = new Temperature();

        while (true) {
            errorCheckInputData = InputDataChecker.checkData(currentTemperature, amperage, diameterOfWire, lengthOfWire, material);

            if (errorCheckInputData == ErrorCheckInputData.ALL_IS_WELL) {
                setDropShadowForWire(currentTemperature);
            } else {
                updateMessage(String.valueOf(currentTemperature));

                if (errorCheckInputData == ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE) {
                    throw new WireException(errorCheckInputData.getMessage() + "(макс: " + material.getMaxTemperature() + ")");
                } else {
                    if (errorCheckInputData == ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW) {
                        throw new WireException(errorCheckInputData.getMessage() + "(мин: " + material.getMinTemperature() + ")");
                    } else {
                        throw new WireException(errorCheckInputData.getMessage());
                    }
                }
            }

            if (this.isCancelled()) {
                break;
            }

            currentTemperature = temperature.calculateTemperature(thermalConductivity, diameterOfWire, lengthOfWire, material, amperage, startTemperatureOfWire);

            updateMessage(String.valueOf(currentTemperature));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                System.out.println("YEE");
            }
        }
        return null;
    }


    private void setDropShadowForWire(double currentTemperature) {
        double newRadius;
        Color color;

        if (currentTemperature >= 0) {
            newRadius = 50f / 1085f * currentTemperature;

            int redColorValue = (int) (255f / 1085f * currentTemperature);
            color = Color.rgb(redColorValue, 0, 0, 1);
        } else {
            if (currentTemperature < 0) {
                newRadius = Math.abs(50f / 273.15f * currentTemperature);
                int blueColorValue = (int) Math.abs(255f / 273.15f * currentTemperature);

                color = Color.rgb(0, 0, blueColorValue, 1);
            } else {
                newRadius = 2;
                color = Color.rgb(10, 0, 0, 1);
            }
        }

        dropShadowForWire.setRadius(newRadius);
        dropShadowForWire.setColor(color);
    }

    private double calculateThermalConductivity() {
        double thermalConductivity = 401;

        if (startTemperatureOfWire >= 125 && startTemperatureOfWire < 225) {
            thermalConductivity = 400;
        }

        if (startTemperatureOfWire >= 225) {
            thermalConductivity = 398;
        }
        return thermalConductivity;
    }


    private void dropWire() throws IOException {
        Destroy destroy = new WaterDrop();
        destroy.destroyWire();
    }

    private void setErrorMessage(String errorMessage, Label messageInputData) {
        messageInputData.setStyle("-fx-text-fill: red");
        messageInputData.setText(errorMessage);
    }


    private void setWireHeight(double diameterOfWire, Rectangle wire) {
        if (diameterOfWire <= 0.01) {
            wire.setHeight(1);
        } else {
            if (diameterOfWire < 1) {
                wire.setHeight(diameterOfWire * 50);
            } else {
                wire.setHeight(50);
            }
        }
    }

}
