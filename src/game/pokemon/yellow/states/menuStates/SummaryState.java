package game.pokemon.yellow.states.menuStates;

import game.pokemon.yellow.entities.pokemons.Pokemon;
import game.pokemon.yellow.states.State;
import game.pokemon.yellow.states.StateManager;
import java.awt.Graphics;

/**
 *
 * @author Lucas G R Leonor
 */
public class SummaryState extends State {

    private Pokemon pokemon;

    @Override
    public void tick() {
        if (handler.getKeyManager().b) {
            StateManager.setCurrentState("Party");
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(pokemon.getImage(), 0, 0, null);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon Pokemon) {
        this.pokemon = Pokemon;
    }

}
