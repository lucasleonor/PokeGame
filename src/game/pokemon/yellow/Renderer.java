package game.pokemon.yellow;

import game.pokemon.yellow.display.Display;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.gfx.GameCamera;
import game.pokemon.yellow.inputs.KeyManager;
import game.pokemon.yellow.inputs.MouseManager;
import game.pokemon.yellow.states.StateManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Lucas G R Leonor
 */
public class Renderer implements Runnable {

    public String title;
    private int width, height, fps;
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private GameCamera gameCamera;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private boolean running = false, rendering = false;
    private Handler handler;

    public Renderer(String title, int width, int height, KeyManager keyManager, MouseManager mouseManager) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.keyManager = keyManager;
        this.mouseManager = mouseManager;
        handler = Handler.getINSTANCE();
    }

    private void init() {
        Assets.init();
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        gameCamera = new GameCamera(0, 0);
    }

    private void render() {
        rendering = true;
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }
        g.setFont(new Font("Dialog", 0, 12));
        g.setColor(Color.yellow);
        g.drawString("FPS: " + fps, 10, 15);
        g.drawString("TPS: " + handler.getGame().getTicksPerSecond(), 10, 30);
        //End Drawing
        bs.show();
        g.dispose();
        rendering = false;
    }

    @Override
    public void run() {
        init();
        int maxFps = 500;
        double timePerTick = 1000000000 / maxFps, delta = 0;
        long now, lastTime = System.nanoTime(), timer = 0;
        int frames = 0;
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                while (handler.getGame().isTicking()) {
                    System.out.print("");
                }
                render();
                frames++;
                delta = 0;
            }
            if (timer >= 1000000000) {
                fps = frames;
                frames = 0;
                timer = 0;
            }
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public void setMouseManager(MouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public void setGameCamera(GameCamera gameCamera) {
        this.gameCamera = gameCamera;
    }

    public boolean isRendering() {
        return rendering;
    }

}
