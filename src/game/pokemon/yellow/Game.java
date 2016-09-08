package game.pokemon.yellow;

import game.pokemon.yellow.inputs.KeyManager;
import game.pokemon.yellow.inputs.MouseManager;
import game.pokemon.yellow.states.StateManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas G R Leonor
 */
public class Game {

    private boolean running = false, ticking = false;
    private Thread thread;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private Renderer renderer;
    private int ticksPerSecond;

    public Game(String title, int width, int height) {
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        renderer = new Renderer(title, width, height, keyManager, mouseManager);
    }

    private void init() {
        Handler.getINSTANCE().setGame(this);
        Handler.getINSTANCE().setRenderer(renderer);
        StateManager.setCurrentState("Game");
    }

    private void tick() {
        ticking = true;
        keyManager.tick();
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
        ticking = false;
    }

    public void run() {
        init();
        int tps = 60;//ticks per second
        int ticks = 0;
        double timePerTick = 1000000000 / tps, delta = 0;
        long now, lastTime = System.nanoTime(), timer = 0;
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                while(renderer.isRendering()){
                    System.out.print("");
                }
                tick();
                ticks++;
                delta=0;
            }
            if (timer >= 1000000000) {
                ticksPerSecond = ticks;
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        renderer.setRunning(running);
        thread = new Thread(renderer);
        thread.start();
        run();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        renderer.setRunning(running);
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getTicksPerSecond() {
        return ticksPerSecond;
    }

    public boolean isTicking() {
        return ticking;
    }

}
