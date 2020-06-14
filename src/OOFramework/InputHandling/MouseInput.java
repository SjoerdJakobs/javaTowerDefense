package OOFramework.InputHandling;

import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MouseInput
{
    private final FrameworkProgram frameworkProgram;

    private static MouseInput INSTANCE = null;
    public static MouseInput getInstance() {
        if (INSTANCE == null) {
            synchronized (MouseInput.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MouseInput();
                }
            }
        }
        return INSTANCE;
    }

    private Vector2 mousePos = new Vector2();

    private final ArrayList<MouseEventCallback> onMouseMoved    = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMousePressed  = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMouseDragged  = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMouseReleased = new ArrayList<>();

    private MouseInput() {
        frameworkProgram = FrameworkProgram.getProgramInstance();
        frameworkProgram.getStage().addEventFilter(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        mousePos.x = e.getX();
                        mousePos.y = e.getY();
                        for (MouseEventCallback mec : onMouseMoved) {
                            mec.run(e);
                        }
                    }
                });
        frameworkProgram.getStage().addEventFilter(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        for (MouseEventCallback mec : onMousePressed) {
                            mec.run(e);
                        }
                    }
                });
        frameworkProgram.getStage().addEventFilter(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        for (MouseEventCallback mec : onMouseDragged) {
                            mec.run(e);
                        }
                    }
                });
        frameworkProgram.getStage().addEventFilter(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        for (MouseEventCallback mec : onMouseReleased) {
                            mec.run(e);
                        }
                    }
                });
    }

    public Vector2 getMousePos() {
        return mousePos;
    }

    public ArrayList<MouseEventCallback> getOnMouseMoved() {
        return onMouseMoved;
    }

    public ArrayList<MouseEventCallback> getOnMousePressed() {
        return onMousePressed;
    }

    public ArrayList<MouseEventCallback> getOnMouseDragged() {
        return onMouseDragged;
    }

    public ArrayList<MouseEventCallback> getOnMouseReleased() {
        return onMouseReleased;
    }
}
