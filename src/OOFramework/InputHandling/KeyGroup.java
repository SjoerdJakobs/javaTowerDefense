package OOFramework.InputHandling;

import OOFramework.StandardObject;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public final class KeyGroup
{
    public KeyCode keyCode;
    public ArrayList<Key> keys;

    public KeyGroup(KeyCode keyCode) {
        this.keyCode = keyCode;
        keys = new ArrayList<Key>();
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }
}
