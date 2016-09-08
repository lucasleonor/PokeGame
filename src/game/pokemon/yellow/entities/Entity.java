package game.pokemon.yellow.entities;

import game.pokemon.yellow.Handler;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public abstract class Entity {

    public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;

    protected Handler handler;
    protected int x, y;
    protected Rectangle bounds;

    public Entity(int x, int y) {
        handler = Handler.getINSTANCE();
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public void render(Graphics g) {
        g.drawImage(getImage(), (int) (getX() - handler.getxOffset()), (int) (getY() - handler.getyOffset()), getWidth(), getHeight(), null);
//        g.setColor(new Color(255, 0, 0, 125));
//        g.fillRect((int) (getActualTileX() * TileManager.TILEWIDHT - handler.getxOffset()), (int) (getActualTileY() * TileManager.TILEHEIGHT - handler.getyOffset()), (int) bounds.getWindowWidth(), (int) bounds.getWindowHeight());
    }

    public int getWidth() {
        return DEFAULT_WIDTH;
    }

    public int getHeight() {
        return DEFAULT_HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public abstract BufferedImage getImage();

    public int getActualTileX() {
        return (int) ((getX() + bounds.x) / TileManager.TILEWIDHT);
    }

    public int getActualTileY() {
        return (int) ((getY() + bounds.y) / TileManager.TILEHEIGHT);
    }

}
