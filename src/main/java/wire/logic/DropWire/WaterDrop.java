package wire.logic.DropWire;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import wire.model.Main;

import java.util.ArrayList;
import java.util.List;

public class WaterDrop implements Destroy {
    private short windowHeight;
    private static final List<ImageView> waterDrops = new ArrayList<>();
    private static final Image image = new Image(WaterDrop.class.getResource("../../water.png").toString());
    private static final int maxWaterDrops = 400;


    private boolean move(ImageView imageView) {
        if (this.windowHeight < imageView.getTranslateY()) {
            return false;
        } else {

            double y = imageView.getTranslateY()+1;
            imageView.setTranslateY(y);
            return true;
        }
    }

    private void animate(ImageView imageView) {
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!move(imageView)) {
                    this.stop();
                }
            }
        }.start();
    }

    private void moveToStart(ImageView imageView) {
        int positionY = (short) -(Math.random() * 800);
        imageView.setTranslateY(positionY);
    }

    @Override
    public void destroyWire() {
        Pane rootNode = (Pane) Main.rootStage.getScene().getRoot();

        short windowWidth = (short) Main.rootStage.getScene().getWindow().getWidth();

        this.windowHeight = (short) (Main.rootStage.getScene().getWindow().getHeight() - 240);

        if (waterDrops.size() != maxWaterDrops) {
            waterDrops.clear();
            for (int i = 0; i < maxWaterDrops; i++) {
                waterDrops.add(initializeDrops(windowWidth));
                rootNode.getChildren().add(waterDrops.get(i));
            }
        }

        waterDrops.forEach(this::moveToStart);
        waterDrops.forEach(this::animate);
        waterDrops.forEach(this::animate);
        waterDrops.forEach(this::animate);
    }

    private ImageView initializeDrops(short windowWidth) {

        short positionX = (short) (Math.random() * (windowWidth - 15));

        ImageView imageView = new ImageView(image);
        imageView.setX(positionX);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        return imageView;
    }
}
