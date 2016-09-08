package game.pokemon.yellow.entities;

import game.pokemon.yellow.Handler;
import static game.pokemon.yellow.entities.Entity.DEFAULT_HEIGHT;
import static game.pokemon.yellow.entities.Entity.DEFAULT_WIDTH;
import game.pokemon.yellow.gfx.Animation;
import game.pokemon.yellow.tiles.TileManager;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public abstract class NonStaticEntity extends Entity {

    public static final int SPEED = 2;
    protected Animation[] animations;
    protected boolean male;
    protected int xMove, yMove, aiming, targetX, targetY;
    protected boolean walking;

    public NonStaticEntity(Animation[] animations, boolean male, int x, int y) {
        super(x, y);
        handler = Handler.getINSTANCE();
        aiming = 0;
        this.x = x;
        this.y = y;
        this.male = male;
        this.animations = animations;
        xMove = 0;
        yMove = 0;
        targetX = x;
        targetY = y;
        walking = false;
        bounds = new Rectangle(16, DEFAULT_HEIGHT / 2 - 1, DEFAULT_WIDTH / 2 - 2, TileManager.TILEHEIGHT - 2);
    }

    public void move() {
        x += xMove;
        y += yMove;
        if (walking) {
            if (targetX == x && targetY == y) {
                walking = false;
                xMove = 0;
                yMove = 0;
            }
        }
        getMove();
    }

    protected void getMove() {
        xMove = 0;
        yMove = 0;
        int move = (int) (Math.random() * 10);
        walking = move < 4;
        switch (move) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void moveX() {
        if (xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / TileManager.TILEWIDHT;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / TileManager.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.width) / TileManager.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * TileManager.TILEWIDHT - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / TileManager.TILEWIDHT;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / TileManager.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.width) / TileManager.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * TileManager.TILEWIDHT - bounds.x + TileManager.TILEWIDHT + 1;
            }
        }
    }

    public void moveY() {
        if (yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / TileManager.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / TileManager.TILEWIDHT, ty) && !collisionWithTile((int) (x + bounds.x + bounds.height) / TileManager.TILEWIDHT, ty)) {
                y += yMove;
            } else {
                y = ty * TileManager.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        } else if (yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / TileManager.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / TileManager.TILEWIDHT, ty) && !collisionWithTile((int) (x + bounds.x + bounds.height) / TileManager.TILEWIDHT, ty)) {
                y += yMove;
            } else {
                y = ty * TileManager.TILEHEIGHT + TileManager.TILEHEIGHT - bounds.y + 1;
            }
        }
    }

    @Override
    public void tick() {
        if (walking)
            animations[aiming].tick();
        move();
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage image;
        image = animations[aiming].getCurrentFrame(walking);
        return image;
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().collisionWithTile(x, y);
    }

    public int getAiming() {
        return aiming;
    }

    public void setAiming(int aiming) {
        this.aiming = aiming;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

}
