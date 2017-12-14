package wire.logic.checker;

import wire.logic.constants.ErrorCheckPhysicsConstants;
import wire.logic.constants.PhysicsConstants;

public class PhysicsConstantsChecker {
    private PhysicsConstantsChecker() {
    }

    public static ErrorCheckPhysicsConstants checkData(PhysicsConstants material) {
        ErrorCheckPhysicsConstants errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ALL_IS_WELL;

        if (material.getMinTemperature() < -273.15) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_TEMPERATURE_IS_TO_LOW;
        }

        if (material.getSpecificHeat() < 0.129) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_SPECIFIC_HEAT_IS_TO_LOW;
        }

        if (material.getSpecificHeat() > 14.304) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_SPECIFIC_HEAT_IS_TO_ABOVE;
        }

        if (material.getDensity() > 22.61) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_DENSITY_IS_TO_ABOVE;
        }

        if (material.getDensity() < 0.53) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_DENSITY_IS_TO_LOW;
        }

        if (material.getResistanceIn20Degrees() > 150E-08) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_RESISTANCE_IN_20_DEGREES_IS_TO_ABOVE;
        }

        if (material.getResistanceIn20Degrees() < 1.5E-08) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_RESISTANCE_IN_20_DEGREES_IS_TO_LOW;
        }

        if (material.getTemperatureCoefficientOfResistance() > 0.0065) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE_IS_TO_ABOVE;
        }

        if (material.getTemperatureCoefficientOfResistance() < 0.00002) {
            errorCheckPhysicsConstants = ErrorCheckPhysicsConstants.ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE_IS_TO_LOW;
        }

        return errorCheckPhysicsConstants;
    }

}