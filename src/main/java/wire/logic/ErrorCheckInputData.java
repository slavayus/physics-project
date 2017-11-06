package wire.logic;

public enum ErrorCheckInputData {
    ERROR_RESISTANCE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: сопротивление";
        }
    },
    ERROR_AMPERAGE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Сила тока выше допустимого";
        }
    },
    ERROR_LENGTH_OF_WIRE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: длина проводника";
        }
    },
    ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Температура проводника слишком высока(макс: 70)";
        }
    },
    ERROR_TEMPERATURE_OF_THE_AIR {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: температура окружающей среды";
        }
    },
    ALL_IS_WELL {
        @Override
        public String getMessage() {
            return "Данные введены корректно";
        }
    },
    ERROR_DIAMETER_OF_WIRE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Диаметр проводника слишком большая(макс: 1 метр)";
        }
    },
    ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Температура проводника слишком низкая(мин: -273.15)";
        }
    };

    public abstract String getMessage();
}
