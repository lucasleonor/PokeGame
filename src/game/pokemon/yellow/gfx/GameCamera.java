package game.pokemon.yellow.gfx;

import game.pokemon.yellow.Handler;
import game.pokemon.yellow.entities.persons.Player;
import game.pokemon.yellow.tiles.TileManager;

/**
 *
 * @author Lucas G R Leonor
 */
public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        handler = Handler.getINSTANCE();
    }

    public void centerOnPlayer(Player e) {
        xOffset = e.getX() - (handler.getWindowWidth() - e.getWidth()) / 2;
        yOffset = e.getY() - (handler.getWindowHeight() - e.getHeight()) / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * TileManager.TILEWIDHT - handler.getWindowWidth()) {
            xOffset = handler.getWorld().getWidth() * TileManager.TILEWIDHT - handler.getWindowWidth();
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * TileManager.TILEHEIGHT - handler.getWindowHeight()) {
            yOffset = handler.getWorld().getHeight() * TileManager.TILEHEIGHT - handler.getWindowHeight();
        }
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
