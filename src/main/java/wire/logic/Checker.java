package wire.logic;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

class Checker {
    private Checker() {
    }

    static ErrorCheckInputData checkData(double temperatureOfWire, double amperage, double diameterOfWire, double lengthOfWire, PhysicsConstants material) {
        ErrorCheckInputData errorCheckInputData = ErrorCheckInputData.ALL_IS_WELL;
        int thermalConductivity = getThermalConductivity(temperatureOfWire);
        double continuousAdmissibleAmperage = sqrt((thermalConductivity * ((PI * pow(diameterOfWire, 2)) / 4) * material.getMaxTemperature()) / (5));


//        if (continuousAdmissibleAmperage < amperage) {
//            errorCheckInputData = ErrorCheckInputData.ERROR_AMPERAGE_IS_TO_ABOVE;
//        }


        if (material.getMaxTemperature() < temperatureOfWire) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE;
        }

        if (amperage < 0) {
            errorCheckInputData = ErrorCheckInputData.ERROR_AMPERAGE_IS_TOO_LOW;
        }

        if (temperatureOfWire < material.getMinTemperature()) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW;
        }


        if (lengthOfWire <= 0) {
            errorCheckInputData = ErrorCheckInputData.ERROR_LENGTH_OF_WIRE;
        }


        if (diameterOfWire <= 0) {
            errorCheckInputData = ErrorCheckInputData.ERROR_DIAMETER_OF_WIRE_IS_TO_LOW;
        }

        return errorCheckInputData;
    }

    private static int getThermalConductivity(double startTemperatureOfWire) {
        int thermalConductivity = 401;

        if (startTemperatureOfWire >= 125 && startTemperatureOfWire < 225) {
            thermalConductivity = 400;
        }

        if (startTemperatureOfWire >= 225) {
            thermalConductivity = 398;
        }

        return thermalConductivity;
    }

}
