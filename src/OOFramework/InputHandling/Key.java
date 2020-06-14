package OOFramework.InputHandling;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Key
{
    private KeyState keyState;
    private KeyCode keyCode;
    private KeyboardEventCallback keyPressedCallback;
    private KeyboardEventCallback keyHoldCallback;
    private KeyboardEventCallback keyReleasedCallback;

    public Key(KeyCode keyCode)
    {
        this.keyCode = keyCode;

        this.keyPressedCallback = this::placeholderCallbackMethod;
        this.keyHoldCallback = this::placeholderCallbackMethod;
        this.keyReleasedCallback = this::placeholderCallbackMethod;

        this.keyState = KeyState.RELEASED;
    }

    public Key(KeyCode keyCode, KeyboardEventCallback kpc, KeyboardEventCallback khc, KeyboardEventCallback krc) {
        this.keyCode = keyCode;

        if(kpc != null)
        {
            this.keyPressedCallback = kpc;
        }
        else
        {
            this.keyPressedCallback = this::placeholderCallbackMethod;
        }
        if(khc != null)
        {
            this.keyHoldCallback = khc;
        }
        else
        {
            this.keyHoldCallback = this::placeholderCallbackMethod;
        }
        if(krc != null)
        {
            this.keyReleasedCallback = krc;
        }
        else
        {
            this.keyReleasedCallback = this::placeholderCallbackMethod;
        }

        this.keyState = KeyState.RELEASED;
    }

    private void placeholderCallbackMethod(KeyEvent e)
    {

    }

    public KeyState getKeyState() {
        return keyState;
    }

    public void setKeyState(KeyState keyState) {
        this.keyState = keyState;
    }

    public KeyboardEventCallback getKeyPressedCallback() {
        return keyPressedCallback;
    }

    public KeyboardEventCallback getKeyHoldCallback() {
        return keyHoldCallback;
    }

    public KeyboardEventCallback getKeyReleasedCallback() {
        return keyReleasedCallback;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }
}
