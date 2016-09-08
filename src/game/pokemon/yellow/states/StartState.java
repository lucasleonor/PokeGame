package game.pokemon.yellow.states;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Lucas G R Leonor
 */
public class StartState extends State {

    private int opt;

    StartState() {
        opt = 1;
    }

    @Override
    public void tick() {
        if (timer == 0) {
            if (handler.getKeyManager().down) {
                opt++;
                timer++;
                if (opt > 3) {
                    opt = 1;
                }
            } else if (handler.getKeyManager().up) {
                opt--;
                timer++;
                if (opt < 1) {
                    opt = 3;
                }
            } else if (handler.getKeyManager().right) {
                if (opt == 1) {
                    StateManager.setCurrentState("Game");
                }
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
        g.setColor(Color.white);
        g.fillRect(0, 0, windowW, windowH);
        g.setFont(handler.getFont());
        g.setColor(Color.black);
        g.drawString("-", 10, opt * 20);
        g.drawString("CONTINUE", 30, 20);
        g.drawString("NEW GAME", 30, 40);
        g.drawString("OPTION", 30, 60);
    }

}
