package game.pokemon.yellow.inputs;

import javax.json.JsonObject;

/**
 *
 * @author Lucas G R Leonor
 */
public class PokemonLoader {

    private JsonObject jos[];
    private static final PokemonLoader INSTANCE = new PokemonLoader();

    private PokemonLoader() {
        jos = new JsonObject[151];
    }

    public String getName(int id) {
        id--;
        if (jos[id] == null) {
            load(id);
        }
        return jos[id].getString("name");
    }

    private void load(int id) {
        jos[id] = FileReader.getContent("Resources/Data/Pokemons/" + (id + 1) + ".json");
    }

    public static PokemonLoader getINSTANCE() {
        return INSTANCE;
    }
}
