package game.pokemon.yellow.entities.persons;

import game.pokemon.yellow.entities.NonStaticEntity;
import game.pokemon.yellow.entities.pokemons.Pokemon;
import game.pokemon.yellow.gfx.Animation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public abstract class Trainer extends NonStaticEntity {

    protected List<Pokemon> team;

    public Trainer(Animation[] animations, boolean male, int x, int y) {
        super(animations, male, x, y);
        team = new ArrayList<>();
    }

    public List<Pokemon> getTeam() {
        return team;
    }

}
