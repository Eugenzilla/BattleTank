package io.eugenzilla.display;

import javax.swing.*;
import java.awt.*;

public abstract class Display {

    private static boolean isCreated = false;
    private static JFrame window;
    private static Canvas content;

    public static void create(int width, int height, String title) {
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


    }

}
