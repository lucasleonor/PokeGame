package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.entities.Entity;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public abstract class StaticEntity extends Entity {

    protected final int ID;
    protected BufferedImage texture;
    protected int width, height;

    public StaticEntity(int ID, int x, int y, int width, int height) {
        super(x, y);
        this.ID = ID;
        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {
    }

    @Override
    public int getX() {
        return x * TileManager.TILEWIDHT;
    }

    @Override
    public int getY() {
        return y * TileManager.TILEHEIGHT;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public BufferedImage getImage() {
        return texture;
    }

}
