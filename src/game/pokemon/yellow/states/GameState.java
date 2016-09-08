package game.pokemon.yellow.states;

import game.pokemon.yellow.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author Lucas G R Leonor
 */
public class GameState extends State {

    private World world;

    GameState() {
        world = new World("Resources\\Worlds\\teste.tmx");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
        if (timer == 0) {
            if (handler.getKeyManager().enter) {
                StateManager.setCurrentState("Pause");
            }
        } else {
            timer++;
            if (timer == WAITING_TIME) {
                timer = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
