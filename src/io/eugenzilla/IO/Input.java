package io.eugenzilla.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Input extends JComponent {

    public static final int MAX_ASCII_CHARS = 256;
    private boolean[] keysMap;

    public Input() {
        keysMap = new boolean[MAX_ASCII_CHARS];

        for (int i = 0; i< keysMap.length; i++) {
            final int KEY_CODE = i; // для анонимного класса, чтобы сохранить копию значения

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false), i *2); // i*2 название для кнопки
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keysMap[KEY_CODE] = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true), i *2 +1); // i*2 +1 название для кнопки
            getActionMap().put(i * 2 +1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keysMap[KEY_CODE] = false;
                }
            });
        }
    }

    public boolean[] getKeysMap() {
        return Arrays.copyOf(keysMap, keysMap.length);
    }

    public boolean getKey(int keyCode) {
        return keysMap[keyCode];
    }
}
