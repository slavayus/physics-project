package wire.logic;

import java.util.Date;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;

class Temperature {
    private long date;

    Temperature() {
        this.date = new Date().getTime() / 1000;
    }

    double calculateTemperature(double thermalConductivity, double diameterOfWire, double lengthOfWire, PhysicsConstants material, double amperage, double startTemperatureOfWire) {
        double crossSectionalArea = (PI * pow(diameterOfWire, 2)) / 4;
        double weight = crossSectionalArea * lengthOfWire * material.getDensityOfCopper();
        double surfaceArea = PI * diameterOfWire * lengthOfWire;
        long duration = new Date().getTime() / 1000 - date;

        double resistance = calculateResistance(crossSectionalArea, material, lengthOfWire, startTemperatureOfWire);

        double first = pow(amperage, 2) * resistance * duration;
        double second = thermalConductivity * surfaceArea * duration;
        double third = weight * material.getSpecificHeatOfCopper();
        return startTemperatureOfWire + Math.abs(first / (third + second));
    }

    private double calculateResistance(double crossSectionalArea, PhysicsConstants material, double lengthOfWire, double startTemperatureOfWire) {
        return material.getResistanceIn20Degrees() * lengthOfWire / crossSectionalArea * (1 + material.getTemperatureCoefficientOfResistance() * (startTemperatureOfWire - 20));
    }
}