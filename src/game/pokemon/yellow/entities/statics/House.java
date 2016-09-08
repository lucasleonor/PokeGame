package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;

/**
 *
 * @author Lucas G R Leonor
 */
public class House extends StaticEntity {

    public House(int x, int y) {
        super(1, x, y, EntityManager.house.getWidth(), EntityManager.house.getHeight());
        texture = EntityManager.house.getImage();
        bounds = EntityManager.house.getBounds();
    }

    public House() {
        super(1, 0, 0, TileManager.TILEWIDHT * 7, TileManager.TILEHEIGHT * 8);
        texture = Assets.getFromTiles(0, 11168, width, height);
        bounds = new Rectangle(TileManager.TILEWIDHT, TileManager.TILEHEIGHT * 2, TileManager.TILEWIDHT * 5 - 1, TileManager.TILEHEIGHT * 5 - 1);
    }

}
