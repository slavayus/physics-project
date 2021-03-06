package wire.logic.constants;

public enum ErrorCheckInputData {
    ERROR_AMPERAGE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Сила тока выше допустимой";
        }
    },
    ERROR_LENGTH_OF_WIRE {
        @Override
        public String getMessage() {
            return "Длина проводника должна быть положительным числом";
        }
    },
    ERROR_TEMPERATURE_OF_WIRE_IS_TO_ABOVE {
        @Override
        public String getMessage() {
            return "Температура проводника выше допустимой";
        }
    },
    ALL_IS_WELL {
        @Override
        public String getMessage() {
            return "Данные введены корректно";
        }
    },
    ERROR_DIAMETER_OF_WIRE_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Диаметр проводника должен быть больше нуля";
        }
    },
    ERROR_TEMPERATURE_OF_WIRE_IS_TO_LOW {
        @Override
        public String getMessage() {
            return "Температура проводника слишком низкая";
        }
    },
    ERROR_AMPERAGE_IS_TOO_LOW {
        @Override
        public String getMessage() {
            return "Сила тока должна быть выше ноля";
        }
    };

    public abstract String getMessage();
}
