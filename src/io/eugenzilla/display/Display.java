package io.eugenzilla.display;

import io.eugenzilla.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {

    private static boolean isCreated = false;
    private static JFrame window;
    private static Canvas content;

    public static BufferedImage bufferedImage;
    private static int[] bufferedImageData;
    private static Graphics bufferedImageGraphics;
    private static int clearColor; // цвет для очищения картинки

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {
        if (isCreated) {
            return;
        }

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // остановить программу по крестику

        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content); //внутренняя часть окна
        window.pack(); //изменить размер окна точно под размер контента
        window.setLocationRelativeTo(null); // окно по центру
        window.setVisible(true);

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedImageData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData(); // вытащить массив интегеров из изображения
        bufferedImageGraphics = bufferedImage.getGraphics();
        ((Graphics2D) bufferedImageGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        clearColor = _clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        isCreated = true;
    }

    public static void clear() {
        Arrays.fill(bufferedImageData, clearColor);
    }

    public static void swapBuffers() {
        Graphics graphics = bufferStrategy.getDrawGraphics(); // вытаскиваем текущую графику канваса
        graphics.drawImage(bufferedImage, 0, 0, null); // заменяем графику на буфер
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferedImageGraphics;
    }

    public static void destroy() {
        if (!isCreated) {
            return;
        }
        window.dispose();
    }

    public static void setTitle (String title) {
        window.setTitle(title);
    }

    public static void addInputListener(Input inputListener) {
        window.add(inputListener);
    }

}
