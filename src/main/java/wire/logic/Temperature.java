package wire.logic;

import javafx.concurrent.Task;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.Date;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.tan;

public class Temperature extends Task<Void> {
    private static final int SPECIFIC_HEAT_OF_COPPER = 381;
    private static final double DENSITY_OF_COPPER = 8.92;
    private static final double RESISTANCE_IN_20_DEGREES = 1.72E-08;
    private static final double TEMPERATURE_COEFFICIENT_OF_RESISTANCE = 0.00393;

    private final DropShadow dropShadowForWire;
    private double currentTemperature;
    private long date;
    private double amperage;
    private double lengthOfWire;
    private double startTemperatureOfWire;
    private double diameterOfWire;


    public Temperature(DropShadow dropShadowForWire, double amperage, double lengthOfWire, double startTemperatureOfWire, double diameterOfWire) {
        this.dropShadowForWire = dropShadowForWire;
        this.amperage = amperage;
        this.lengthOfWire = lengthOfWire;
        this.diameterOfWire = diameterOfWire;

        this.date = new Date().getTime() / 1000;
        this.startTemperatureOfWire = startTemperatureOfWire;
    }


    private void calculateTemperature() {
        double crossSectionalArea = (PI * pow(diameterOfWire, 2)) / 4;
        double weight = crossSectionalArea * lengthOfWire * DENSITY_OF_COPPER;
        double surfaceArea = PI * diameterOfWire * lengthOfWire;
        long duration = new Date().getTime() / 1000 - date;

        double resistance = calculateResistance(crossSectionalArea);

        double first = pow(amperage, 2) * resistance * duration;
        double second = getThermalConductivity() * surfaceArea * duration;
        double third = weight * SPECIFIC_HEAT_OF_COPPER;
        currentTemperature = startTemperatureOfWire + first / (third + second);
        updateMessage(String.valueOf(currentTemperature));
    }

    private double calculateResistance(double crossSectionalArea) {
        return RESISTANCE_IN_20_DEGREES * lengthOfWire / crossSectionalArea * (1 + TEMPERATURE_COEFFICIENT_OF_RESISTANCE * (startTemperatureOfWire - 20));
    }

    private synchronized int getThermalConductivity() {
        int thermalConductivity = 401;

        if (startTemperatureOfWire >= 125 && startTemperatureOfWire < 225) {
            thermalConductivity = 400;
        }

        if (startTemperatureOfWire >= 225) {
            thermalConductivity = 398;
        }

        return thermalConductivity;
    }


    @Override
    protected Void call() {
        try {
            while (true) {

                if (this.isCancelled()) {
                    break;
                }

                calculateTemperature();

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("YEE");
//                }
                if (currentTemperature >= 20) {
                    dropShadowForWire.setRadius(currentTemperature - 18);
                    int colorValue = (int) (50 + (currentTemperature - 20) * 4.1);
                    dropShadowForWire.setColor(Color.rgb(colorValue, 0, 0, 1));
                } else {
                    dropShadowForWire.setRadius(2);
                    dropShadowForWire.setColor(Color.rgb(10, 0, 0, 1));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}