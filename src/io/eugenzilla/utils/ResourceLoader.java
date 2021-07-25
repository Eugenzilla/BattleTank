package io.eugenzilla.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {

    public static final String RES_PATH = "res/";

    public static BufferedImage loadImage (String fileName) {
        BufferedImage bufferedImage = null;

        try{
            bufferedImage = ImageIO.read(new File(RES_PATH + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bufferedImage;
    }
}
