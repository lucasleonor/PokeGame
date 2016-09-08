package game.pokemon.yellow.config;

import game.pokemon.yellow.states.StateManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public class ConfigManager {

    private List<Config> configs;
    private static ConfigManager INSTANCE;

    private ConfigManager() {
        configs = new ArrayList<>();
        ArrayList<Object> listAux = new ArrayList<>();
        listAux.add("Slow");
        listAux.add("Mid");
        listAux.add("Fast");
        configs.add(new Config("Text Speed", listAux));
        listAux = new ArrayList<>();
        listAux.add("On");
        listAux.add("Off");
        configs.add(new Config("Sounds", listAux));
        listAux = new ArrayList<>();
        listAux.add("On");
        listAux.add("Off");
        configs.add(new Config("Music", listAux));
        listAux = new ArrayList<>();
        for (int i = 0; i < StateManager.getFrames().size(); i++) {
            listAux.add(i + 1);
        }
        configs.add(new Config("Frames", listAux));
    }

    public static ConfigManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    public List<Config> getConfigs() {
        return configs;
    }

}
