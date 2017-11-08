package wire.logic;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;

public class Wire {
    private Task<Void> task;

    public Wire(Task<Void> task) {
        this.task = task;
    }

    public Task<Void> drawWire(DropShadow dropShadowForWire, double startTemperatureOfWire, double amperage, double diameterOfWire, double lengthOfWire, Label currentTemperatureLabel, Rectangle wire, Label messageInputData, PhysicsConstants material) {
        setWireHeight(diameterOfWire, wire);

        if (task != null) {
            task.cancel();
        }

        task = new CopperWire(dropShadowForWire, amperage, lengthOfWire, startTemperatureOfWire, diameterOfWire, material);
        currentTemperatureLabel.textProperty().bind(task.messageProperty());

        task.setOnFailed(workerStateEvent -> {
            setErrorMessage(task.getException().getMessage(), messageInputData);
            dropWire();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        return task;
    }

    private void dropWire() {
        System.out.println("YEE");

    }


    private void setErrorMessage(String errorMessage, Label messageInputData) {
        messageInputData.setStyle("-fx-text-fill: red");
        messageInputData.setText(errorMessage);
    }


    private void setWireHeight(double diameterOfWire, Rectangle wire) {
        if (diameterOfWire <= 0.01) {
            wire.setHeight(1);
        } else {
            if (diameterOfWire < 1) {
                wire.setHeight(diameterOfWire * 50);
            } else {
                wire.setHeight(50);
            }
        }
    }

}
