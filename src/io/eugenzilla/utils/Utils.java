package io.eugenzilla.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static BufferedImage resize(BufferedImage image, int width, int height) {

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, height, null);

        return newImage;
    }

    public static int[][] levelParser(String filePath) {

        int [][] result = null;

        try (BufferedReader reader= new BufferedReader(new FileReader(new File(filePath)))) {

            String line = null;
            List<int[]> lvlLines = new ArrayList<int[]>();
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split("");
                int[] tokenInts = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
                lvlLines.add(tokenInts);
            }

            result = new int[lvlLines.size()][lvlLines.get(0).length];

            for (int i = 0; i < lvlLines.size(); i++) {
                result[i] = lvlLines.get(i);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
