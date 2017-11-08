package wire.logic;

public enum PhysicsConstants {
    COPPER {
        @Override
        public double getMaxTemperature() {
            return 1085;
        }

        @Override
        public double getMinTemperature() {
            return -273.15;
        }

        @Override
        public double getSpecificHeatOfCopper() {
            return 381;
        }

        @Override
        public double getDensityOfCopper() {
            return 8.92;
        }

        @Override
        public double getResistanceIn20Degrees() {
            return 1.72E-08;
        }

        @Override
        public double getTemperatureCoefficientOfResistance() {
            return 0.00393;
        }
    };

    public abstract double getMaxTemperature();

    public abstract double getMinTemperature();

    public abstract double getSpecificHeatOfCopper();

    public abstract double getDensityOfCopper();

    public abstract double getResistanceIn20Degrees();

    public abstract double getTemperatureCoefficientOfResistance();
}
