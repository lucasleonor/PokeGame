package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;

/**
 *
 * @author Lucas G R Leonor
 */
public class Lab extends StaticEntity {

    public Lab(int x, int y) {
        super(2, x, y, EntityManager.lab.getWidth(), EntityManager.lab.getHeight());
        texture = EntityManager.lab.getImage();
        bounds = EntityManager.lab.getBounds();
    }

    public Lab() {
        super(2, 0, 0, TileManager.TILEWIDHT * 8, TileManager.TILEHEIGHT * 8);
        texture = Assets.getFromTiles(0, 11424, width, height);
        bounds = new Rectangle(0, TileManager.TILEHEIGHT * 2, TileManager.TILEWIDHT * 7 - 1, TileManager.TILEHEIGHT * 5 - 1);
    }

}
