package io.eugenzilla.game;

import io.eugenzilla.IO.Input;
import io.eugenzilla.display.Display;
import io.eugenzilla.game.level.Level;
import io.eugenzilla.graphics.Sprite;
import io.eugenzilla.graphics.SpriteSheet;
import io.eugenzilla.graphics.TextureAtlas;
import io.eugenzilla.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Battle Tank";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f; // 60 обновлений в секунду
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1; // в милисекундах

    public static final String ATLAS_FILENAME = "texture_atlas.png";

    private boolean isRunning;
    private Thread gameThread;
    private Graphics2D graphics2D;
    private Input inputListener;
    private TextureAtlas textureAtlas;
    private Player player;
    private Level level;


    public Game() {
        isRunning = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics2D = Display.getGraphics();
        inputListener = new Input();
        Display.addInputListener(inputListener);
        textureAtlas = new TextureAtlas(ATLAS_FILENAME);
        player = new Player(300,300, 2, 3, textureAtlas);
        level = new Level(textureAtlas);
    }

    public synchronized void start() { // функция может вызываться только из одного треда
        if (isRunning) { return; }

        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start(); // запускает метод run
    }

    public synchronized void stop() {
        if (!isRunning) {return; }

        isRunning = false;

        try {
            gameThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        cleanup();
    }

    private void update() { // рассчеты всей физики игры
        player.update(inputListener);
        level.update();
    }

    private void render() { // отрисовка сцен
        Display.clear();
        level.render(graphics2D);
        player.render(graphics2D);
        level.renderGrass(graphics2D);
        Display.swapBuffers();
    }

    @Override
    public void run() { // ядро игры - бесконечный луп
        int fps = 0;
        int updateCounter = 0;
        int updateLoopsCounter = 0; //сколько раз пришлось догонять апдейт

        long totalElapsedTime = 0;

        float delta = 0;
        long lastTime = Time.get();

        while (isRunning) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            totalElapsedTime += elapsedTime;

            boolean isRenderNeeded = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                updateCounter++;
                delta--;

                if (isRenderNeeded) {
                    updateLoopsCounter++;
                } else {
                    isRenderNeeded = true;
                }
            }

            if(isRenderNeeded) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME); // дать подышать другим тредам
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            if (totalElapsedTime >= Time.SECOND) { // отображение статистики
                Display.setTitle(TITLE + " || fps: " + fps + " | upd: " + updateCounter + " | updLoops: " + updateLoopsCounter );
                fps = 0;
                updateCounter = 0;
                updateLoopsCounter = 0;
                totalElapsedTime = 0;
            }
        }

    }

    private void cleanup() { // очистка ресурсов
        Display.destroy();
    }
}
