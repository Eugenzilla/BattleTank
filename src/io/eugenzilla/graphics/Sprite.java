package io.eugenzilla.graphics;

import io.eugenzilla.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private SpriteSheet spriteSheet;
    private float scale;
    private BufferedImage image;


    public Sprite (SpriteSheet spriteSheet, float scale) {
        this.spriteSheet = spriteSheet;
        this.scale = scale;
        image = spriteSheet.getSprite(0);
        image = Utils.resize(image, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
    }

    public void render(Graphics2D graphics2D, float x, float y) {

        graphics2D.drawImage(image, (int)x, (int)y, null);
    }
}
