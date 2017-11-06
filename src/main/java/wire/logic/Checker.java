package wire.logic;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Checker {
    private final static int LONG_TERM_PERMISSIBLE_TEMPERATURE = 70;
    private final static int MAX_DIAMETER = 1;
    private final static double MIN_TEMPERATURE = -273.15;

    private double resistance;
    private double diameterOfWire;
    private double amperage;
    private double startTemperatureOfWire;

    public Checker(double startTemperatureOfWire, double amperage, double diameterOfWire, double resistance) {
        this.startTemperatureOfWire = startTemperatureOfWire;
        this.amperage = amperage;
        this.diameterOfWire = diameterOfWire;
        this.resistance = resistance;
    }

    public ErrorCheckInputData checkInputData() {
        ErrorCheckInputData errorCheckInputData = ErrorCheckInputData.ALL_IS_WELL;
        int thermalConductivity = getThermalConductivity();
        double continuousAdmissibleAmperage = sqrt((thermalConductivity * ((PI * pow(diameterOfWire, 2)) / 4) * LONG_TERM_PERMISSIBLE_TEMPERATURE) / (resistance));


        if (continuousAdmissibleAmperage < amperage) {
            errorCheckInputData = ErrorCheckInputData.ERROR_AMPERAGE_IS_TO_ABOVE;
        }


        if (LONG_TERM_PERMISSIBLE_TEMPERATURE < startTemperatureOfWire) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE;
        }


        if (startTemperatureOfWire < MIN_TEMPERATURE) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW;
        }


        if (diameterOfWire > MAX_DIAMETER) {
            errorCheckInputData = ErrorCheckInputData.ERROR_LENGTH_OF_WIRE;
        }

        return errorCheckInputData;
    }

    private int getThermalConductivity() {
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
