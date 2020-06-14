package OOFramework.InputHandling;

import OOFramework.BaseObject;
import OOFramework.FrameworkProgram;
import OOFramework.PriorityGroup;
import OOFramework.StandardObject;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KeyboardInput
{
    private final FrameworkProgram frameworkProgram;

    private Map<KeyCode, KeyGroup> keyMap;

    private static KeyboardInput INSTANCE = null;
    public static KeyboardInput getInstance() {
        if (INSTANCE == null) {
            synchronized (KeyboardInput.class) {
                if (INSTANCE == null) {
                    INSTANCE = new KeyboardInput();
                }
            }
        }
        return INSTANCE;
    }

    public KeyboardInput()
    {
        this.frameworkProgram = FrameworkProgram.getProgramInstance();
        keyMap = new HashMap<>();
        Setup();
    }

    public void AddKey(Key key) {
        boolean hasGroup = false;

        Iterator<Map.Entry<KeyCode, KeyGroup>> it = keyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<KeyCode, KeyGroup> pair = it.next();
            if(pair.getKey() == key.getKeyCode())
            {
                pair.getValue().keys.add(key);
                hasGroup = true;
            }
        }
        if(!hasGroup)
        {
            KeyGroup newGroup = new KeyGroup(key.getKeyCode());
            newGroup.keys.add(key);
            keyMap.put(key.getKeyCode(),newGroup);
        }
    }
    public void RemoveKey(Key key) {
        Iterator<Map.Entry<KeyCode, KeyGroup>> it = keyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<KeyCode, KeyGroup> pair = it.next();
            if(pair.getKey() == key.getKeyCode())
            {
                Iterator<Key> i = pair.getValue().keys.iterator();
                while (i.hasNext()) {
                    Key k = i.next();
                    i.remove();
                }
            }
        }
    }

    public void Setup()
    {
        frameworkProgram.getStage().addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Iterator<Map.Entry<KeyCode, KeyGroup>> it = keyMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<KeyCode, KeyGroup> pair = it.next();
                    if(pair.getKey() == event.getCode())
                    {
                        for (Key k : pair.getValue().keys)
                        {
                            if(k.getKeyState() == KeyState.RELEASED)
                            {
                                k.getKeyPressedCallback().run(event);
                                k.setKeyState(KeyState.PRESSED);
                            }
                            else if(k.getKeyState() == KeyState.PRESSED)
                            {
                                k.getKeyHoldCallback().run(event);
                                k.setKeyState(KeyState.HOLD);
                            }
                        }
                    }
                }
            }
        });

        frameworkProgram.getStage().addEventFilter(KeyEvent.KEY_RELEASED,
                new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Iterator<Map.Entry<KeyCode, KeyGroup>> it = keyMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<KeyCode, KeyGroup> pair = it.next();
                    if(pair.getKey() == event.getCode())
                    {
                        for (Key k : pair.getValue().keys)
                        {
                            if(k.getKeyState() == KeyState.PRESSED || k.getKeyState() == KeyState.HOLD)
                            {
                                k.getKeyReleasedCallback().run(event);
                                k.setKeyState(KeyState.RELEASED);
                            }
                        }
                    }
                }
            }
        });
    }
}
