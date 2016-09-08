package game.pokemon.yellow.states;

import game.pokemon.yellow.gfx.Assets;
import game.pokemon.yellow.gfx.Frame;
import game.pokemon.yellow.states.menuStates.OptionState;
import game.pokemon.yellow.states.menuStates.PartyState;
import game.pokemon.yellow.states.menuStates.SummaryState;
import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public class StateManager {

    private static State menuState, gameState, introState, pauseState, optionState, partyState, summaryState;
    private static State currentState;
    private static int actualFrame;
    private static List<Frame> frames;

    static {
        currentState = null;
        frames = Assets.getMenuFrames();
        actualFrame = 0;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(String state) {
        if ("Menu".equalsIgnoreCase(state)) {
            if (menuState == null) {
                menuState = new StartState();
            }
            currentState = menuState;
        } else if ("Game".equalsIgnoreCase(state)) {
            if (gameState == null) {
                gameState = new GameState();
            }
            gameState.setTimer(1);
            currentState = gameState;
        } else if ("Pause".equalsIgnoreCase(state)) {
            if (pauseState == null) {
                if (gameState == null) {
                    return;
                }
                pauseState = new PausedState((GameState) gameState);
            }
            pauseState.setTimer(1);
            currentState = pauseState;
        } else if ("Option".equalsIgnoreCase(state)) {
            if (optionState == null) {
                optionState = new OptionState();
            }
            optionState.setTimer(1);
            currentState = optionState;
        } else if ("Summary".equalsIgnoreCase(state)) {
            if (summaryState == null) {
                summaryState = new SummaryState();
            }
            ((SummaryState) summaryState).setPokemon(((PartyState)partyState).getPokeSelect());
            summaryState.setTimer(1);
            currentState = summaryState;
        } else if ("Party".equalsIgnoreCase(state)) {
            if (partyState == null) {
                partyState = new PartyState();
            }
            partyState.setTimer(1);
            currentState = partyState;
        } else {
            if (introState == null) {
                introState = new IntroState();
            }
            currentState = introState;
        }
    }

    public static void nextFrame() {
        StateManager.actualFrame++;
        if (actualFrame > frames.size() - 1) {
            actualFrame = 0;
        }
        ((PausedState) pauseState).changeFrame();
        ((OptionState) optionState).changeFrame();
    }

    public static void previousFrame() {
        StateManager.actualFrame--;
        if (actualFrame < 0) {
            actualFrame = frames.size() - 1;
        }
        ((PausedState) pauseState).changeFrame();
        ((OptionState) optionState).changeFrame();
    }

    public static Frame getFrame() {
        return frames.get(actualFrame);
    }

    public static List<Frame> getFrames() {
        return frames;
    }

}
