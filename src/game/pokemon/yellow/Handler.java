package game.pokemon.yellow;

import game.pokemon.yellow.entities.Entity;
import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.entities.persons.Player;
import game.pokemon.yellow.gfx.GameCamera;
import game.pokemon.yellow.inputs.KeyManager;
import game.pokemon.yellow.inputs.MouseManager;
import game.pokemon.yellow.states.StartState;
import game.pokemon.yellow.worlds.World;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas G R Leonor
 */
public class Handler {

    private Font font;
    public static final boolean debug = false;
    private Game game;
    private Renderer renderer;
    private World world;
    private static final Handler INSTANCE = new Handler();

    private Handler() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("PokemonGB.ttf"));
            font = font.deriveFont(12f);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(StartState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Handler getINSTANCE() {
        return INSTANCE;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getWindowWidth() {
        return renderer.getWidth();
    }

    public int getWindowHeight() {
        return renderer.getHeight();
    }

    public GameCamera getGameCamera() {
        return renderer.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getMouseX() {
        return getMouseManager().getMouseX();
    }

    public int getMouseY() {
        return getMouseManager().getMouseY();
    }

    public float getxOffset() {
        return getGameCamera().getxOffset();
    }

    public float getyOffset() {
        return getGameCamera().getyOffset();
    }

    public ArrayList<Entity> getEntities() {
        return world.getEm().getEntities();
    }

    public EntityManager getEntityManager() {
        return world.getEm();
    }

    public Player getPlayer() {
        return getEntityManager().getPlayer();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

}
