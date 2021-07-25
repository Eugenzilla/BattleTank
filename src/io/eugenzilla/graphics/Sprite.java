package io.eugenzilla.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private SpriteSheet spriteSheet;
    private float scale;

    public Sprite (SpriteSheet spriteSheet, float scale) {
        this.spriteSheet = spriteSheet;
        this.scale = scale;
    }

    public void render(Graphics2D graphics2D, float x, float y) {
        BufferedImage image = spriteSheet.getSprite(0);
        graphics2D.drawImage(image, (int)x, (int)y, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
    }
}
