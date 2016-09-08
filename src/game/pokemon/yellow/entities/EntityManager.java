package game.pokemon.yellow.entities;

import game.pokemon.yellow.Handler;
import game.pokemon.yellow.entities.persons.Player;
import game.pokemon.yellow.entities.pokemons.Pokemon;
import game.pokemon.yellow.entities.statics.CityFence;
import game.pokemon.yellow.entities.statics.House;
import game.pokemon.yellow.entities.statics.Lab;
import game.pokemon.yellow.entities.statics.Tree;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Lucas G R Leonor
 */
public class EntityManager {

    private Handler handler;
    private Player player;
    private Pokemon pokemon;
    private ArrayList<Entity> entities;
    private Comparator<Entity> comparator;
    public static final Entity tree, house, cityFence, lab;
    
    static {
        tree = new Tree();
        house = new House();
        cityFence = new CityFence();
        lab = new Lab();

    }

    public EntityManager() {
        handler = Handler.getINSTANCE();
        entities = new ArrayList<>();
        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity a, Entity b) {
                if (a == player || a == pokemon) {
                    return (a.getY() + a.getHeight()) - (b.getY() + b.getHeight() - 1);
                } else if (b == player || b == pokemon) {
                    return (a.getY() + a.getHeight() - 1) - (b.getY() + b.getHeight());
                }
                return (a.getY() + a.getHeight()) - (b.getY() + b.getHeight());
            }
        };
    }

    public EntityManager(Player player) {
        handler = Handler.getINSTANCE();
        this.player = player;
        entities = new ArrayList<>();
        entities.add(player);
        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity a, Entity b) {
                if (a == player || a == pokemon) {
                    return (a.getY() + a.getHeight()) - (b.getY() + b.getHeight() - 1);
                } else if (b == player || b == pokemon) {
                    return (a.getY() + a.getHeight() - 1) - (b.getY() + b.getHeight());
                }
                return (a.getY() + a.getHeight()) - (b.getY() + b.getHeight());
            }
        };
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
        entities.sort(comparator);
    }

    public void render(Graphics g) {
        int xStart = handler.getWorld().getxStart() * 32, xEnd = handler.getWorld().getxEnd() * 32,
                yStart = handler.getWorld().getyStart() * 32, yEnd = handler.getWorld().getyEnd() * 32;
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if ((entity.getX() > xStart || entity.getX() + entity.getWidth() > xStart) && (entity.getX() < xEnd)
                    && (entity.getY() > yStart || entity.getY() + entity.getHeight() > yStart) && (entity.getY() < yEnd)) {
                entity.render(g);
            }
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (this.player != null) {
            entities.remove(this.player);
        }
        this.player = player;
        entities.add(this.player);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        entities.remove(this.pokemon);
        entities.add(pokemon);
        this.pokemon = pokemon;
        entities.sort(comparator);
    }

}
