package wire.logic;

import javafx.concurrent.Task;

import java.util.Date;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;

abstract class Temperature extends Task<Void> {
    private long date;
    final PhysicsConstants material;
    double amperage;
    double lengthOfWire;
    double startTemperatureOfWire;
    double diameterOfWire;
    double thermalConductivity;


    Temperature(double amperage, double lengthOfWire, double startTemperatureOfWire, double diameterOfWire, PhysicsConstants material) {
        this.amperage = amperage;
        this.lengthOfWire = lengthOfWire;
        this.diameterOfWire = diameterOfWire;

        this.date = new Date().getTime() / 1000;
        this.startTemperatureOfWire = startTemperatureOfWire;
        this.material = material;
    }

    double calculateTemperature() {
        double crossSectionalArea = (PI * pow(diameterOfWire, 2)) / 4;
        double weight = crossSectionalArea * lengthOfWire * material.getDensityOfCopper();
        double surfaceArea = PI * diameterOfWire * lengthOfWire;
        long duration = new Date().getTime() / 1000 - date;

        double resistance = calculateResistance(crossSectionalArea);

        double first = pow(amperage, 2) * resistance * duration;
        double second = thermalConductivity * surfaceArea * duration;
        double third = weight * material.getSpecificHeatOfCopper();
        return startTemperatureOfWire + Math.abs(first / (third + second));
    }

    private double calculateResistance(double crossSectionalArea) {
        return material.getResistanceIn20Degrees() * lengthOfWire / crossSectionalArea * (1 + material.getTemperatureCoefficientOfResistance() * (startTemperatureOfWire - 20));
    }
}