package wire.logic.constants;

public enum ErrorCheckPhysicsConstants {
    ERROR_TEMPERATURE_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Минимальная температура проводника не может быть меньше -273.15";
        }
    },
    ERROR_SPECIFIC_HEAT_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Удельная теплоемкость не может быть ниже 0.129(золото)";
        }
    },
    ERROR_SPECIFIC_HEAT_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Удельная теплоемкость не может быть выше 14.304(водород)";
        }
    },
    ERROR_DENSITY_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Плотность проводника не может быть выше 22.61(осмий)";
        }
    },
    ERROR_DENSITY_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Плотность проводника не может быть ниже 0.53(литий)";
        }
    },
    ALL_IS_WELL {
        @Override
        public String getMessage() {
            return "Данные введены корректно";
        }
    },
    ERROR_RESISTANCE_IN_20_DEGREES_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Сопротивление проводника при 20 градусах не может быть выше 150(хромаль)";
        }
    },
    ERROR_RESISTANCE_IN_20_DEGREES_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Сопротивление проводника при 20 градусах не может быть выше 1.5(серебро)";
        }
    },
    ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Температурный коэффициент сопротивления не может быть выше 0.0065(никель)";
        }
    },
    ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Температурный коэффициент сопротивления не может быть ниже 0.00002(марганец)";
        }
    };

    public abstract String getMessage();
}
