package game.pokemon.yellow.config;

import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public class Config {

    private String name;
    private List<Object> values;
    private int active;

    public Config(String name, List<Object> values) {
        this.name = name;
        this.values = values;
        this.active = 0;
    }

    public Config(String name, List<Object> values, int active) {
        this.name = name;
        this.values = values;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public Object getActive() {
        return values.get(active);
    }

    public void next() {
        active++;
        if (active >= values.size()) {
            active = 0;
        }
    }

    public void previous() {
        active--;
        if (active < 0) {
            active = values.size() - 1;
        }
    }

}
