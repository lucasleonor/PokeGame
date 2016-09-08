package game.pokemon.yellow.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Lucas G R Leonor
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean keyPress, up, down, left, right, shift, ctrl, enter, a, b;

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
            keyPress = true;
        } else {
            System.out.printf("Key not found %d - %s\n", e.getKeyCode(), e.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
            keyPress = false;
        }
    }

    public boolean isWalking() {
        return (up || down || left || right) && !ctrl;
    }

    public boolean isRunning() {
        return isWalking() && shift;
    }

    public void tick() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        shift = keys[KeyEvent.VK_SHIFT];
        ctrl = keys[KeyEvent.VK_CONTROL];
        enter = keys[KeyEvent.VK_ENTER];
        a = keys[KeyEvent.VK_Q];
        b = keys[KeyEvent.VK_E];
    }

}
