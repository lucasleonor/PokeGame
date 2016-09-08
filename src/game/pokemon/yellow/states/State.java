package game.pokemon.yellow.states;

import game.pokemon.yellow.Handler;
import java.awt.Graphics;

/**
 *
 * @author Lucas G R Leonor
 */
public abstract class State {

    protected static final int WAITING_TIME;
    protected int timer, windowW, windowH;
    protected Handler handler;

    static {
        WAITING_TIME = 12;
    }

    public State() {
        handler = Handler.getINSTANCE();
        timer = 1;
        windowW = handler.getWindowWidth();
        windowH = handler.getWindowHeight();
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

}
