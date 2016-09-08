package game.pokemon.yellow.worlds;

import game.pokemon.yellow.Handler;
import game.pokemon.yellow.entities.EntityManager;
import game.pokemon.yellow.entities.persons.Player;
import java.awt.Color;
import java.awt.Graphics;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.Tile;
import tiled.core.TileLayer;
import tiled.io.TMXMapReader;

/**
 *
 * @author Lucas G R Leonor
 */
public class World {

    private Handler handler;
    private int spawnX, spawnY, xStart, xEnd, yStart, yEnd, timer;
    private Map map;
    private TileLayer land;
    private EntityManager em;
    private String actualPlace;
    private ObjectGroup objs;
    private Player player;
    private boolean placeChanged;

    public World(String path) {
        em = new EntityManager();
        loadWorld(path);
        player = new Player(true, spawnX, spawnY);
        em.setPlayer(player);
        handler = Handler.getINSTANCE();
        timer = 0;
        actualPlace = "";
    }

    public void tick() {
        em.tick();
        if (timer > 0) {
            timer++;
            if (timer == 120) {
                timer = 0;
                placeChanged = false;
            }
        }
        for (MapObject object : objs) {
            if (object.getType().equals("Place")) {
                if ((object.getX() < player.getX() && object.getWidth() + object.getX() >= player.getX())
                        && (object.getY() < player.getY() && object.getHeight() + object.getY() >= player.getY())) {
                    if (!object.getName().equals(actualPlace)) {
                        actualPlace = object.getName();
                        placeChanged = true;
                        timer=1;
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        xStart = (int) Math.max(0, handler.getxOffset() / 32);
        xEnd = (int) Math.min(map.getWidth(), (handler.getxOffset() + handler.getWindowWidth()) / 32 + 1);
        yStart = (int) Math.max(0, handler.getyOffset() / 32);
        yEnd = (int) Math.min(map.getHeight(), (handler.getyOffset() + handler.getWindowHeight()) / 32 + 1);
        for (MapLayer layer : map) {
            if (layer.getName().equalsIgnoreCase("NonStaticEntities")) {
                em.render(g);
            }
            if (layer instanceof TileLayer) {
                TileLayer t = (TileLayer) layer;
                for (int j = xStart; j < xEnd; j++) {
                    for (int i = yStart; i < yEnd; i++) {
                        tiled.core.Tile tile = t.getTileAt(j, i);
//                tiled.core.Tile tile = land.getTileAt(j, i);
                        if (tile != null) {
                            g.drawImage(tile.getImage(), (int) (j * 32 - handler.getxOffset()), (int) (i * 32 - handler.getyOffset()), null);
                        }
                    }
                }
            }
        }
        if (placeChanged) {
            g.setFont(handler.getFont());
            g.setColor(Color.red);
            g.drawString(actualPlace, 10, 20);
        }
    }

    public boolean collisionWithTile(int x, int y) {
        Tile t = land.getTileAt(x, y);
        return !(t == null || land.getTileAt(x, y).getId() == 0);
    }

    private void loadWorld(String path) {
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap(path);
            for (MapLayer mapLayer : map) {
                if (mapLayer instanceof ObjectGroup) {
                    objs = (ObjectGroup) mapLayer;
                    for (MapObject mapObject : objs) {
                        if (mapObject.getName().equalsIgnoreCase("spawn")) {
                            spawnX = (int) mapObject.getX();
                            spawnY = (int) mapObject.getY();
                        }
                    }
                } else if (mapLayer instanceof TileLayer) {
                    TileLayer layer = (TileLayer) mapLayer;
                    if (layer.getName().equalsIgnoreCase("Baixo")) {
                        land = layer;
                    }
//                    if (layer.getName().equals("Land")) {
//                        land = layer;
//                    }
//                    for (int i = 0; i < layer.getWidth(); i++) {
//                        for (int j = 0; j < layer.getHeight(); j++) {
//                            tiled.core.Tile t = layer.getTileAt(i, j);
//                            if (t != null) {
//                                int id = t.getId();
//                                if (id == 1016 || id == 998 || id == 976 || id == 1000 || id == 982) {
//                                    em.addEntity(new Tree(i, j));
//                                } else if (id == 2792) {
//                                    em.addEntity(new House(i, j));
//                                } else if (id == 2856) {
//                                    em.addEntity(new Lab(i, j));
//                                } else if (id == 4124 || id == 4140) {
//                                    em.addEntity(new CityFence(i, j));
//                                }
//                            }
//                        }
//                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public int getxStart() {
        return xStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public int getyEnd() {
        return yEnd;
    }

}
