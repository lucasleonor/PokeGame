package game.pokemon.yellow.entities.persons;

import game.pokemon.yellow.entities.pokemons.Pokemon;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Lucas G R Leonor
 */
public class Player extends Trainer {

    private ArrayList<Pokemon> capturados;
    private int lastX, lastY;

    public Player(boolean male, int x, int y) {
        super(Assets.getPlayerAnimations(male), male, x - 16, y - DEFAULT_HEIGHT / 2);
        bounds = new Rectangle(x - this.x, y - this.y, DEFAULT_WIDTH / 2 - 1, TileManager.TILEHEIGHT - 1);
        lastX = this.x;
        lastY = this.y - TileManager.TILEHEIGHT;
        team.add(new Pokemon(25, lastX, lastY));
        team.add(new Pokemon(30, lastX, lastY));
        team.get(1).setHealth(80);
        team.get(1).setMaxHealth(90);
        team.add(new Pokemon(3, lastX, lastY));
    }

    public int getAiming() {
        return aiming;
    }

    @Override
    public void tick() {
        if (handler.getEntityManager().getPokemon() != team.get(0)) {
            handler.getEntityManager().setPokemon(team.get(0));
        }
        handler.getGameCamera().centerOnPlayer(this);
        super.tick();
    }

    @Override
    protected void getMove() {
        if (walking) {
            return;
        }
        xMove = 0;
        yMove = 0;
        walking = handler.getKeyManager().isWalking();
        if (handler.getKeyManager().down) {
            aiming = 0;
            if (walking && !collisionWithTile(getActualTileX(), getActualTileY() + 1)) {
                yMove = SPEED;
                targetY += TileManager.TILEHEIGHT;
            }
        } else if (handler.getKeyManager().left) {
            aiming = 1;
            if (walking && !collisionWithTile(getActualTileX() - 1, getActualTileY())) {
                xMove = -SPEED;
                targetX -= TileManager.TILEWIDHT;
            }
        } else if (handler.getKeyManager().up) {
            aiming = 2;
            if (walking && !collisionWithTile(getActualTileX(), getActualTileY() - 1)) {
                yMove = -SPEED;
                targetY -= TileManager.TILEHEIGHT;
            }
        } else if (handler.getKeyManager().right) {
            aiming = 3;
            if (walking && !collisionWithTile(getActualTileX() + 1, getActualTileY())) {
                xMove = SPEED;
                targetX += TileManager.TILEWIDHT;
            }
        }
        walking = walking && (xMove != 0 || yMove != 0);
        if (walking) {
            lastX = x;
            lastY = y;
        }
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

}
