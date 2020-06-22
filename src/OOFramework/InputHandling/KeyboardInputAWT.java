package OOFramework.InputHandling;


/*
public class KeyboardInputAWT
{

    private static final int KEY_COUNT = 256;

    // Current state of the keyboard
    private boolean[] currentKeys = null;

    // Polled keyboard state
    private KeyState[] keys = null;

    public KeyboardInputAWT(FrameworkProgram frameworkProgram) {
        currentKeys = new boolean[ KEY_COUNT ];
        keys = new KeyState[ KEY_COUNT ];
        for( int i = 0; i < KEY_COUNT; ++i ) {
            keys[ i ] = KeyState.RELEASED;
        }

        frameworkProgram.getStage().addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {

                        }
                    }
                });
    }

    public synchronized void poll() {
        for( int i = 0; i < KEY_COUNT; ++i ) {
            // Set the key state
            if( currentKeys[ i ] ) {
                // If the key is down now, but was not
                // down last frame, set it to ONCE,
                // otherwise, set it to PRESSED
                if( keys[ i ] == KeyState.RELEASED )
                    keys[ i ] = KeyState.ONCE;
                else
                    keys[ i ] = KeyState.PRESSED;
            } else {
                keys[ i ] = KeyState.RELEASED;
            }
        }
    }

    public boolean keyDown( int keyCode ) {
        return keys[ keyCode ] == KeyState.ONCE ||
                keys[ keyCode ] == KeyState.PRESSED;
    }

    public boolean keyDownOnce( int keyCode ) {
        return keys[ keyCode ] == KeyState.ONCE;
    }

    public synchronized void keyPressed( KeyCode keyCode )
    {
        //if( keyCode.impl_getCode() >= 0 && keyCode < KEY_COUNT ) {
        //    currentKeys[ keyCode ] = true;
        //}
    }

    public synchronized void keyReleased( KeyEvent e ) {
        //int keyCode = e.getKeyCode();
        //if( keyCode >= 0 && keyCode < KEY_COUNT ) {
        //    currentKeys[ keyCode ] = false;
        //}
    }

    public void keyTyped( KeyEvent e ) {
        // Not needed
    }
}
*/