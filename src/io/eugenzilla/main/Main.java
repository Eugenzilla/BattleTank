package io.eugenzilla.main;

import io.eugenzilla.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main(String[] args) {

        Display.create(800, 600, "Battle Tank", 0xff00ff00, 3); // argb цвет непрозрачный зеленый

        Timer timer = new Timer(1000 / 60, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { // функция будет вызываться каждый интервал времени
                Display.clear();
                Display.render();
                Display.swapBuffers();

            }
        });

        timer.setRepeats(true); // повторять Action
        timer.start();
    }
}
