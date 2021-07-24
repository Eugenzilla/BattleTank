package io.eugenzilla.game;

import io.eugenzilla.display.Display;
import io.eugenzilla.utils.Time;

public class Game implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Battle Tank";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f; // 60 обновлений в секунду
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1; // в милисекундах

    private boolean isRunning;
    private Thread gameThread;


    public Game() {
        isRunning = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);

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

    }

    private void render() { // отрисовка сцен

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
