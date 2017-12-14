package wire.logic.constants;

public enum PhysicsConstants {
    COPPER {
        @Override
        public double getMaxTemperature() {
            return maxTemperature;
        }

        @Override
        public double getMinTemperature() {
            return minTemperature;
        }

        @Override
        public double getSpecificHeat() {
            return specificHeat;
        }

        @Override
        public double getDensity() {
            return density;
        }

        @Override
        public double getResistanceIn20Degrees() {
            return resistanceIn20Degrees;
        }

        @Override
        public double getTemperatureCoefficientOfResistance() {
            return temperatureCoefficientOfResistance;
        }
    };

    protected double maxTemperature = 1085;
    protected double minTemperature = -273.15;
    //удельная теплоемкоесть
    protected double specificHeat = 0.381;
    //плотность
    protected double density = 8.92;
    //сопротивление при 20 градусах
    protected double resistanceIn20Degrees = 1.72E-08;
    //температурный коэффициент сопротивления
    protected double temperatureCoefficientOfResistance = 0.00393;

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setSpecificHeat(double specificHeat) {
        this.specificHeat = specificHeat;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setResistanceIn20Degrees(double resistanceIn20Degrees) {
        this.resistanceIn20Degrees = resistanceIn20Degrees;
    }

    public void setTemperatureCoefficientOfResistance(double temperatureCoefficientOfResistance) {
        this.temperatureCoefficientOfResistance = temperatureCoefficientOfResistance;
    }

    public abstract double getMaxTemperature();

    public abstract double getMinTemperature();

    public abstract double getSpecificHeat();

    public abstract double getDensity();

    public abstract double getResistanceIn20Degrees();

    public abstract double getTemperatureCoefficientOfResistance();
}
