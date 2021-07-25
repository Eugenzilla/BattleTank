package io.eugenzilla.game.level;

import io.eugenzilla.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type) {
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight()*scale);
    }

    protected TileType type() {
        return type;
    }

    protected void render(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(image, x, y, null);
    }

}
