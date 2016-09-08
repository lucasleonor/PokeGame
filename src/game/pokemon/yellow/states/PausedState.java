package game.pokemon.yellow.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PausedState extends State {

    private GameState gameState;
    private Rectangle rec;
    private int opt;
    private List<String> menuItens;
    private BufferedImage frame;

    public PausedState(GameState state) {
        super();
        opt = 0;
        this.gameState = state;
        this.menuItens = new ArrayList<>();
        menuItens.add("Pokedex");
        menuItens.add("Pokemon");
        menuItens.add("Trainer");
        menuItens.add("Bag");
        menuItens.add("Save");
        menuItens.add("Option");
        int w = ((int) (int) ((windowW * 0.3 / 8))) * 8, h = ((int) ((menuItens.size() + 0.5) * 20 / 8)) * 8, y = (int) (windowH * 0.025), x = windowW - w - y;
        rec = new Rectangle(x, y, w, h);
        frame = StateManager.getFrame().getFrame(rec);
    }

    @Override
    public void tick() {
        if (timer == 0) {
            if (handler.getKeyManager().down) {
                opt++;
                timer++;
                if (opt > menuItens.size() - 1) {
                    opt = 0;
                }
            } else if (handler.getKeyManager().up) {
                opt--;
                timer++;
                if (opt < 0) {
                    opt = menuItens.size() - 1;
                }
            } else if (handler.getKeyManager().a) {
                if (opt == menuItens.size() - 1) {
                    StateManager.setCurrentState("Option");
                }
                if (opt == 1) {
                    StateManager.setCurrentState("Party");
                }
            }
            if (handler.getKeyManager().enter || handler.getKeyManager().b) {
                StateManager.setCurrentState("Game");
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
        gameState.render(g);
        g.setColor(Color.white);
        g.fillRect(rec.x, rec.y, rec.width, rec.height);
        g.setColor(Color.black);
        g.setFont(handler.getFont());
        g.drawString("-", rec.x + 10, rec.y + 20 * (opt + 1));
        for (int i = 0; i < menuItens.size(); i++) {
            g.drawString(menuItens.get(i), rec.x + 25, rec.y + 20 * (i + 1));
        }
        g.drawImage(frame, rec.x - 4, rec.y - 4, null);
    }

    public void changeFrame() {
        this.frame = StateManager.getFrame().getFrame(rec);
    }
}
