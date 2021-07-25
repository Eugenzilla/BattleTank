package io.eugenzilla.graphics;

import io.eugenzilla.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    private BufferedImage bufferedImage;

    public TextureAtlas(String imageName) {
        bufferedImage = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cutImage(int x, int y, int width, int height) {
        return bufferedImage.getSubimage(x, y, width, height);
    }
}
