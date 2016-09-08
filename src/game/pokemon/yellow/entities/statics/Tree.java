package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;

/**
 *
 * @author Lucas G R Leonor
 */
public class Tree extends StaticEntity {

    public Tree() {
        super(0, 0, 0, TileManager.TILEWIDHT * 4, TileManager.TILEHEIGHT * 3);
        texture = Assets.getFromTiles(0, 3904, TileManager.TILEWIDHT * 4, TileManager.TILEHEIGHT * 3);
        bounds = new Rectangle(TileManager.TILEWIDHT, TileManager.TILEHEIGHT, TileManager.TILEWIDHT * 2 - 1, TileManager.TILEHEIGHT * 2 - 1);
    }

    public Tree(int x, int y) {
        super(0, x, y, EntityManager.tree.getWidth(), EntityManager.tree.getHeight());
        texture = EntityManager.tree.getImage();
        bounds = EntityManager.tree.getBounds();
    }

}
