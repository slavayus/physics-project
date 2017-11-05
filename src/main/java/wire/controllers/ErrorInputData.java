package wire.controllers;

public enum ErrorInputData {
    ERROR_RESISTANCE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: сопротивление";
        }
    },
    ERROR_AMPERAGE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: сила тока";
        }
    },
    ERROR_LENGTH_OF_WIRE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: длина проводника";
        }
    },
    ERROR_TEMPERATURE_OF_WIRE {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: температура проводника";
        }
    },
    ERROR_TEMPERATURE_OF_THE_AIR {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: температура окружающей среды";
        }
    },
    ALL_IS_WELL{
        @Override
        public String getMessage() {
            return "Данные введены корректно";
        }
    },
    ERROR_DIAMETER_OF_WIRE{
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: диаметр проводника";
        }
    };

    public abstract String getMessage();
}
