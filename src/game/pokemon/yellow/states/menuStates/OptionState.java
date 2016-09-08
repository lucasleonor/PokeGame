package game.pokemon.yellow.states.menuStates;

import game.pokemon.yellow.config.ConfigManager;
import game.pokemon.yellow.states.State;
import game.pokemon.yellow.states.StateManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public class OptionState extends State {

    private Rectangle rec;
    private BufferedImage frame;
    private int opt;
    private ConfigManager config;

    public OptionState() {
        int w = ((int) (windowW * 0.9 / 8)) * 8, h = ((int) (windowH * 0.9 / 8)) * 8, x = (windowW - w) / 2, y = (windowH - h) / 2;
        rec = new Rectangle(x, y, w, h);
        frame = StateManager.getFrame().getFrame(rec);
        opt = 0;
        config = ConfigManager.getINSTANCE();
    }

    @Override
    public void tick() {
        if (timer == 0) {
            if (handler.getKeyManager().right) {
                if (config.getConfigs().get(opt).getName().equals("Frames")) {
                    StateManager.nextFrame();
                }
                config.getConfigs().get(opt).next();
                timer++;
            } else if (handler.getKeyManager().left) {
                if (config.getConfigs().get(opt).getName().equals("Frames")) {
                    StateManager.previousFrame();
                }
                config.getConfigs().get(opt).previous();
                timer++;
            } else if (handler.getKeyManager().up) {
                opt--;
                if (opt < 0) {
                    opt = config.getConfigs().size() - 1;
                }
                timer++;
            } else if (handler.getKeyManager().down) {
                opt++;
                if (opt >= config.getConfigs().size()) {
                    opt = 0;
                }
                timer++;
            } else if (handler.getKeyManager().b || handler.getKeyManager().a) {
                StateManager.setCurrentState("Pause");
                timer++;
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
        g.setColor(Color.black);
        g.fillRect(0, 0, windowW, windowH);
        g.setColor(Color.white);
        g.fillRect(rec.x, rec.y, rec.width, rec.height);
        g.setColor(Color.black);
        g.setFont(handler.getFont());
        for (int i = 0; i < config.getConfigs().size(); i++) {
            g.drawString(config.getConfigs().get(i).getName(), rec.x + 25, rec.y + (i + 1) * 20);
            g.drawString(config.getConfigs().get(i).getActive().toString(), (rec.x + rec.width) / 2, rec.y + (i + 1) * 20);
        }
        g.drawString("-", rec.x + 10, rec.y + (opt + 1) * 20);
        g.drawImage(frame, rec.x - 4, rec.y - 4, null);
    }

    public void changeFrame() {
        this.frame = StateManager.getFrame().getFrame(rec);
    }
}
