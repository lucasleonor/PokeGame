package game.pokemon.yellow.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public class Animation {

    private int speed, index;
    private long timer;
    private BufferedImage[] frames;

    public Animation() {
        speed = 7;
        index = 0;
        timer = 0;
    }

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
    }

    public void tick() {
        timer++;
        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame(boolean walking) {
        if (!walking) {
            timer = 0;
        }
        return frames[index];
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage[] getFrames() {
        return frames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }

    public int getWidth() {
        return frames[index].getWidth();
    }

    public int getHeight() {
        return frames[index].getHeight();
    }
}
