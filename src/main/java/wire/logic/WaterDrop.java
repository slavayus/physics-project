package wire.logic;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

class WaterDrop {
    private final ImageView imageView;
    private final short windowHeight;
    private short positionY;

    WaterDrop(double windowWidth, short windowHeight) {
        this.windowHeight = (short) (windowHeight - 240);

        short positionX = (short) (Math.random() * (windowWidth - 15));

        this.imageView = new ImageView(WaterDrop.class.getResource("../water.png").toString());
        imageView.setX(positionX);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
    }

    private boolean move() {
        if (this.windowHeight < positionY) {
            return false;
        } else {
            imageView.setTranslateY(++positionY);
            return true;
        }
    }

    ImageView getImageView() {
        return imageView;
    }

    void animate() {
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!move()) {
                    this.stop();
                }
            }
        }.start();
    }

    void moveToStart() {
        positionY = (short) -(Math.random() * 800);
        imageView.setTranslateY(positionY);
    }
}
