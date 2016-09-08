package game.pokemon.yellow.entities.pokemons;

import game.pokemon.yellow.entities.NonStaticEntity;
import game.pokemon.yellow.entities.statics.Item;
import game.pokemon.yellow.gfx.Animation;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.inputs.PokemonLoader;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public class Pokemon extends NonStaticEntity {

    protected int maxHealth, health, xp;
    private BufferedImage back, front;
    private Animation portrait;
    private String nickname, name;
    private Item item;

    public Pokemon(int id, int x, int y) {
        super(Assets.getPokemonAnimations(id), true, x, y);
        portrait = Assets.getPokemonPortrait(id);
//        back = Assets.getPokemonBack(id);
//        front = Assets.getPokemonFront(id);
        maxHealth = 175;
        health = maxHealth;
        aiming = 2;
        name = PokemonLoader.getINSTANCE().getName(id);
        bounds = new Rectangle(32, 32, 32, 32);
        item = new Item();
    }

    @Override
    public void tick() {
        super.tick();
        portrait.tick();
    }

    @Override
    protected void getMove() {
        if (targetX == x && targetY == y) {
            targetX = handler.getPlayer().getLastX();
            targetY = handler.getPlayer().getLastY();
        }
        if (targetX != x) {
            walking = true;
            if (targetX > x) {
                aiming = 3;
                xMove = SPEED;
            } else {
                aiming = 1;
                xMove = -SPEED;
            }
        } else if (targetY != y) {
            walking = true;
            if (targetY > y) {
                aiming = 2;
                yMove = SPEED;
            } else {
                aiming = 0;
                yMove = -SPEED;
            }
        }
    }

    public BufferedImage getBack() {
        return back;
    }

    public BufferedImage getFront() {
        return front;
    }

    public Animation getPortrait() {
        return portrait;
    }

    public String getNickname() {
        if (nickname == null || nickname.length() == 0) {
            return name;
        }
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return 20;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
