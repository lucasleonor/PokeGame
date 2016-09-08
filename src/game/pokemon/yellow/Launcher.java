package game.pokemon.yellow;

/**
 *
 * @author Lucas G R Leonor
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("Pokémon!", 500, 300);
        game.start();
    }

}
