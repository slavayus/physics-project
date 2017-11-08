package wire.logic;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

class CopperWire extends Temperature {

    private final DropShadow dropShadowForWire;

    CopperWire(DropShadow dropShadowForWire, double amperage, double lengthOfWire, double startTemperatureOfWire, double diameterOfWire, PhysicsConstants material) {
        super(amperage, lengthOfWire, startTemperatureOfWire, diameterOfWire, material);
        this.dropShadowForWire = dropShadowForWire;
    }

    @Override
    protected Void call() throws WireException {
        double currentTemperature = startTemperatureOfWire;
        ErrorCheckInputData errorCheckInputData;
        calculateThermalConductivity();

        while (true) {
            errorCheckInputData = Checker.checkData(currentTemperature, amperage, diameterOfWire, lengthOfWire, material);

            if (errorCheckInputData == ErrorCheckInputData.ALL_IS_WELL) {
                setDropShadowForWire(currentTemperature);
            } else {
                updateMessage(String.valueOf(currentTemperature));
                throw new WireException(errorCheckInputData.getMessage());
            }

            if (this.isCancelled()) {
                break;
            }

            currentTemperature = calculateTemperature();

            updateMessage(String.valueOf(currentTemperature));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("YEE");
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

    private void calculateThermalConductivity() {
        thermalConductivity = 401;

        if (startTemperatureOfWire >= 125 && startTemperatureOfWire < 225) {
            thermalConductivity = 400;
        }

        if (startTemperatureOfWire >= 225) {
            thermalConductivity = 398;
        }

    }

}
