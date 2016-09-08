package game.pokemon.yellow.entities.statics;

import game.pokemon.yellow.gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public class Item {

    private BufferedImage icon;

    public Item() {
        icon = Assets.getItemIcon();
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }
}
