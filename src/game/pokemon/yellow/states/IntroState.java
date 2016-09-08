package game.pokemon.yellow.states;

import game.pokemon.yellow.gfx.Intro;
import java.awt.Graphics;

public class IntroState extends State {

    private Intro intro;

    public IntroState() {
        intro = new Intro();
    }

    @Override
    public void tick() {
        intro.tick();
        if (intro.getTimer() > 600) {
            if (handler.getKeyManager().keyPress) {
                if (intro.getTimer() > 1320) {
                    StateManager.setCurrentState("Menu");
                } else {
                    intro.setTimer(1319);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        intro.render(g);
    }

}
