package wire.logic.constants;

public enum ErrorTransferPhysicsConstants {
    ERROR_MAX_TEMPERATURE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: максимальная температура проводника";
        }
    },
    ERROR_MIN_TEMPERATURE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: минимальная температура проводника";
        }
    },
    ERROR_SPECIFIC_HEAT {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: удельная теплоемкость";
        }
    },
    ERROR_RESISTANCE_IN_20_DEGREES {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: сопротивление при 20 градусах";
        }
    },
    ALL_IS_WELL {
        @Override
        public String getMessage() {
            return "Данные введены корректно";
        }
    },
    ERROR_TEMPERATURE_COEFFICIENT_OF_RESISTANCE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: температурный коэффициент сопротивления";
        }
    },
    ERROR_DENSITY {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: плотность";
        }
    };

    public abstract String getMessage();
}
