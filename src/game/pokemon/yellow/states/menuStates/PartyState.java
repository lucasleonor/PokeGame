package game.pokemon.yellow.states.menuStates;

import game.pokemon.yellow.entities.pokemons.Pokemon;
import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.states.State;
import game.pokemon.yellow.states.StateManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public class PartyState extends State {

    private List<Pokemon> team;
    private List<String> options;
    private BufferedImage menuBg, cancel, selectedPokemonBg[], pokemonBg[], optFrame;
    private int pokeSelect, optSelect, x, y, w, h;
    private Rectangle rec;
    private double multiplier;
    private boolean selected, switching;

    public PartyState() {
        options = new ArrayList<>();
        team = handler.getPlayer().getTeam();
        pokeSelect = 0;
        optSelect = 0;
        cancel = Assets.getCancelPokemon();
        menuBg = Assets.getFundoPokemon();
        selectedPokemonBg = Assets.getSelecionadoPokemon();
        pokemonBg = Assets.getNaoSelecionadoPokemon();
        multiplier = Math.min(1.0 * windowW / menuBg.getWidth(), 1.0 * windowH / menuBg.getHeight());
        w = (int) (menuBg.getWidth() * multiplier);
        h = (int) (menuBg.getHeight() * multiplier);
        x = (windowW - w) / 2;
        y = (windowH - h) / 2;
        options.add("Summary");
        options.add("Switch");
        options.add("Item");
        options.add("Cancel");
        rec = new Rectangle(0, 0, (int) (70 * multiplier / 8) * 8, ((options.size() * 20 + 10) / 8) * 8);
        optFrame = StateManager.getFrame().getFrame(rec);
        selected = false;
        switching = false;
    }

    @Override
    public void tick() {
        if (timer == 0) {
            team = handler.getPlayer().getTeam();
            if (handler.getKeyManager().up) {
                if (selected) {
                    optSelect--;
                    if (optSelect < 0) {
                        optSelect = options.size() - 1;
                    }
                } else if (switching) {
                    int trocaAux = pokeSelect - 1;
                    switchPokemon(trocaAux);
                } else {
                    pokeSelect--;
                    if (pokeSelect < 0) {
                        pokeSelect = team.size();
                    }
                }
            } else if (handler.getKeyManager().down) {
                if (selected) {
                    optSelect++;
                    if (optSelect >= options.size()) {
                        optSelect = 0;
                    }
                } else if (switching) {
                    int trocaAux = pokeSelect + 1;
                    switchPokemon(trocaAux);
                } else {
                    pokeSelect++;
                    if (pokeSelect > team.size()) {
                        pokeSelect = 0;
                    }
                }
            } else if ((!selected && !switching && handler.getKeyManager().b) || (handler.getKeyManager().a && pokeSelect == team.size())) {
                StateManager.setCurrentState("Pause");
            } else if (handler.getKeyManager().b) {
                if (switching) {
                    switching = false;
                    selected = true;
                } else if (selected) {
                    selected = false;
                }
            } else if (handler.getKeyManager().a) {
                if (selected) {
                    selected = false;
                    String opt = options.get(optSelect);
                    switch (opt) {
                        case "Cancel":
                            selected = false;
                            break;
                        case "Summary":
                            StateManager.setCurrentState("Summary");
                            break;
                        case "Switch":
                            switching = true;
                            break;
                        case "Item":
                            break;
                    }
                } else if (switching) {
                    switching = false;
                    selected = true;
                } else {
                    selected = true;
                }
            }
            if (handler.getKeyManager().keyPress) {
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
        g.drawImage(menuBg, x, y, w, h, null);
        g.setFont(handler.getFont());
        int x, y;
        for (int i = 0; i < team.size(); i++) {
            Pokemon p = team.get(i);
            x = (int) (this.x + (128 * (i % 2)) * multiplier);
            y = (int) (this.y + (24 * i - 16 * (i % 2)) * multiplier);
            if (pokeSelect == i) {
                BufferedImage selecionado = (i == 0) ? this.selectedPokemonBg[0] : this.selectedPokemonBg[1];
                g.drawImage(selecionado, x, y, (int) (selecionado.getWidth() * multiplier), (int) (selecionado.getHeight() * multiplier), null);
            } else {
                BufferedImage nSelecionado = (i == 0) ? this.pokemonBg[0] : this.pokemonBg[1];
                g.drawImage(nSelecionado, x, y, (int) (nSelecionado.getWidth() * multiplier), (int) (nSelecionado.getHeight() * multiplier), null);
                if (pokeSelect == team.size()) {
                    g.drawImage(cancel, (int) (this.x + 199 * multiplier), (int) (this.y + 163 * multiplier), (int) (cancel.getWidth() * multiplier), (int) (cancel.getHeight() * multiplier), null);
                }
            }
            String health = p.getHealth() + " " + p.getMaxHealth();
            int nameX = (int) (x + 50 * multiplier), hX = (int) (nameX + 35 * multiplier - (p.getHealth() + "").length() * 12);
            g.setColor(Color.black);
            g.drawString(p.getNickname(), nameX + 2, (int) (y + 20 * multiplier) + 1);
            g.drawString(p.getLevel() + "", (int) (x + 24 * multiplier) + 2, (int) (y + 41 * multiplier) + 1);
            g.drawString(health, hX + 2, (int) (y + 40 * multiplier) + 1);
            g.setColor(Color.white);
            g.drawString(p.getNickname(), nameX, (int) (y + 20 * multiplier));
            g.drawString(p.getLevel() + "", (int) (x + 24 * multiplier), (int) (y + 41 * multiplier));
            g.drawString(health, hX, (int) (y + 40 * multiplier));
            g.drawImage(p.getPortrait().getCurrentFrame(true), (int) (x + 15 * multiplier), y, (int) (p.getPortrait().getWidth() * multiplier), (int) (p.getPortrait().getHeight() * multiplier), null);
            if (p.getItem() != null) {
                g.drawImage(p.getItem().getIcon(), (int) (x + 32 * multiplier), (int) (y + 20 * multiplier), (int) (27*multiplier), (int) (27*multiplier), null);
            }
        }
        if (selected) {
            x = (int) (this.x + 195 * multiplier);
            y = (int) (this.y + 200 * multiplier - rec.height);
            g.setColor(Color.white);
            g.fillRect(x - 20, y - 20, rec.width, rec.height);
            g.drawImage(optFrame, x - 24, y - 24, null);
            g.setColor(Color.black);
            for (int i = 0; i < options.size(); i++) {
                g.drawString(options.get(i), x, y + i * 20);
                if (i == optSelect) {
                    g.drawString("-", x - 15, y + i * 20);
                }
            }
        }
    }

    public Pokemon getPokeSelect() {
        return team.get(pokeSelect);
    }

    public void switchPokemon(int trocaAux) {
        if (trocaAux < 0) {
            trocaAux = team.size() - 1;
        } else if (trocaAux == team.size()) {
            trocaAux = 0;
        }
        Pokemon poke = team.get(pokeSelect), aux = team.get(trocaAux);
        if (pokeSelect == 0) {
            switchPokemon(aux, poke);
        } else if (trocaAux == 0) {
            switchPokemon(poke, aux);
        }
        team.set(trocaAux, poke);
        team.set(pokeSelect, aux);
        pokeSelect = trocaAux;
    }

    public void switchPokemon(Pokemon aux, Pokemon poke) {
        aux.setX(poke.getX());
        aux.setY(poke.getY());
        aux.setAiming(poke.getAiming());
        aux.setTargetX(poke.getTargetX());
        aux.setTargetY(poke.getTargetY());
        handler.getEntityManager().setPokemon(aux);
    }
}
