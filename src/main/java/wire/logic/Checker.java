package wire.logic;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Checker {
    private final static int LONG_TERM_PERMISSIBLE_TEMPERATURE = 1085;
    private final static double MIN_TEMPERATURE = -273.15;

    private final double lengthOfWire;
    private final double diameterOfWire;
    private final double amperage;
    private final double startTemperatureOfWire;

    public Checker(double startTemperatureOfWire, double amperage, double diameterOfWire, double lengthOfWire) {
        this.startTemperatureOfWire = startTemperatureOfWire;
        this.amperage = amperage;
        this.diameterOfWire = diameterOfWire;
        this.lengthOfWire = lengthOfWire;
    }

    public ErrorCheckInputData checkInputData() {
        ErrorCheckInputData errorCheckInputData = ErrorCheckInputData.ALL_IS_WELL;
        int thermalConductivity = getThermalConductivity();
        double continuousAdmissibleAmperage = sqrt((thermalConductivity * ((PI * pow(diameterOfWire, 2)) / 4) * LONG_TERM_PERMISSIBLE_TEMPERATURE) / (5));


//        if (continuousAdmissibleAmperage < amperage) {
//            errorCheckInputData = ErrorCheckInputData.ERROR_AMPERAGE_IS_TO_ABOVE;
//        }


        if (LONG_TERM_PERMISSIBLE_TEMPERATURE < startTemperatureOfWire) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE;
        }

        if(amperage<0){
            errorCheckInputData = ErrorCheckInputData.ERROR_AMPERAGE_IS_TOO_LOW;
        }

        if (startTemperatureOfWire < MIN_TEMPERATURE) {
            errorCheckInputData = ErrorCheckInputData.ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW;
        }


        if(lengthOfWire<=0){
            errorCheckInputData = ErrorCheckInputData.ERROR_LENGTH_OF_WIRE;
        }


        if (diameterOfWire <= 0) {
            errorCheckInputData = ErrorCheckInputData.ERROR_DIAMETER_OF_WIRE_IS_TO_LOW;
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
