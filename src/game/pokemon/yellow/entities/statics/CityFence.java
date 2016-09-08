package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;

/**
 *
 * @author Lucas G R Leonor
 */
public class CityFence extends StaticEntity {

    public CityFence(int x, int y) {
        super(3, x, y, EntityManager.cityFence.getWidth(), EntityManager.cityFence.getHeight());
        texture = EntityManager.cityFence.getImage();
        bounds = EntityManager.cityFence.getBounds();
    }

    public CityFence() {
        super(3, 0, 0, TileManager.TILEWIDHT, TileManager.TILEHEIGHT);
        texture = Assets.getFromTiles(128, 16480, 32, 32);
        bounds = new Rectangle(0, 0, TileManager.TILEWIDHT - 1, TileManager.TILEHEIGHT - 1);
    }

}
