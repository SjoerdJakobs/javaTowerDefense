package OOFramework.InputHandling;

import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MouseInput extends StandardObject
{
    private final FrameworkProgram frameworkProgram;

    private static MouseInput INSTANCE = null;

    public static MouseInput getInstance() {
        if (INSTANCE == null) {
            synchronized (MouseInput.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MouseInput(FrameworkProgram.getProgramInstance());
                }
            }
        }
        return INSTANCE;
    }

    private Vector2 mousePos = new Vector2();

    private ArrayList<MouseEventCallback> onMouseMovedToBeAdded = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMousePressedToBeAdded = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMouseDraggedToBeAdded = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMouseReleasedToBeAdded = new ArrayList<MouseEventCallback>();
    private boolean shouldAddToOnMouseMoved = false;
    private boolean shouldAddToOnMousePressed = false;
    private boolean shouldAddToOnMouseDragged = false;
    private boolean shouldAddToOnMouseReleased = false;
    private ArrayList<MouseEventCallback> onMouseMovedToBeRemoved = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMousePressedToBeRemoved = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMouseDraggedToBeRemoved = new ArrayList<MouseEventCallback>();
    private ArrayList<MouseEventCallback> onMouseReleasedToBeRemoved = new ArrayList<MouseEventCallback>();
    private boolean shouldRemoveFromOnMouseMoved = false;
    private boolean shouldRemoveFromOnMousePressed = false;
    private boolean shouldRemoveFromOnMouseDragged = false;
    private boolean shouldRemoveFromOnMouseReleased = false;
    private final ArrayList<MouseEventCallback> onMouseMoved    = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMousePressed  = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMouseDragged  = new ArrayList<>();
    private final ArrayList<MouseEventCallback> onMouseReleased = new ArrayList<>();

    private MouseInput(FrameworkProgram frameworkProgram)
    {
        super(false, true, false, true, 1000, 1_000_000);
        this.frameworkProgram = frameworkProgram;
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

    @Override
    protected void MainLoop(double deltaTime) {

        if (shouldAddToOnMouseMoved) {
            for (MouseEventCallback mec : onMouseMovedToBeAdded) {
                onMouseMoved.add(mec);
            }
            onMouseMovedToBeAdded.clear();
            shouldAddToOnMouseMoved = false;
        }

        if (shouldRemoveFromOnMouseMoved) {
            for (MouseEventCallback mec : onMouseMovedToBeRemoved) {
                onMouseMoved.remove(mec);
            }
            onMouseMovedToBeRemoved.clear();
            shouldRemoveFromOnMouseMoved = false;
        }

        if (shouldAddToOnMousePressed) {
            for (MouseEventCallback mec : onMousePressedToBeAdded) {
                onMousePressed.add(mec);
            }
            onMousePressedToBeAdded.clear();
            shouldAddToOnMousePressed = false;
        }

        if (shouldRemoveFromOnMousePressed) {
            for (MouseEventCallback mec : onMousePressedToBeRemoved) {
                onMousePressed.remove(mec);
            }
            onMousePressedToBeRemoved.clear();
            shouldRemoveFromOnMousePressed = false;
        }

        if (shouldAddToOnMouseDragged) {
            for (MouseEventCallback mec : onMouseDraggedToBeAdded) {
                onMouseDragged.add(mec);
            }
            onMouseDraggedToBeAdded.clear();
            shouldAddToOnMouseDragged = false;
        }

        if (shouldRemoveFromOnMouseDragged) {
            for (MouseEventCallback mec : onMouseDraggedToBeRemoved) {
                onMouseDragged.remove(mec);
            }
            onMouseDraggedToBeRemoved.clear();
            shouldRemoveFromOnMouseDragged = false;
        }

        if (shouldAddToOnMouseReleased) {
            for (MouseEventCallback mec : onMouseReleasedToBeAdded) {
                onMouseReleased.add(mec);
            }
            onMouseReleasedToBeAdded.clear();
            shouldAddToOnMouseReleased = false;
        }

        if (shouldRemoveFromOnMouseReleased) {
            for (MouseEventCallback mec : onMouseReleasedToBeRemoved) {
                onMouseReleased.remove(mec);
            }
            onMouseReleasedToBeRemoved.clear();
            shouldRemoveFromOnMouseReleased = false;
        }
    }

    public Vector2 getMousePos() {
        return mousePos;
    }

    public ArrayList<MouseEventCallback> getOnMouseDraggedToBeAdded() {
        return onMouseDraggedToBeAdded;
    }

    public ArrayList<MouseEventCallback> getOnMouseReleasedToBeAdded() {
        return onMouseReleasedToBeAdded;
    }

    public ArrayList<MouseEventCallback> getOnMouseMovedToBeAdded() {
        return onMouseMovedToBeAdded;
    }

    public ArrayList<MouseEventCallback> getOnMousePressedToBeAdded() {
        return onMousePressedToBeAdded;
    }

    public ArrayList<MouseEventCallback> getOnMouseMovedToBeRemoved() {
        return onMouseMovedToBeRemoved;
    }

    public ArrayList<MouseEventCallback> getOnMousePressedToBeRemoved() {
        return onMousePressedToBeRemoved;
    }

    public ArrayList<MouseEventCallback> getOnMouseDraggedToBeRemoved() {
        return onMouseDraggedToBeRemoved;
    }

    public ArrayList<MouseEventCallback> getOnMouseReleasedToBeRemoved() {
        return onMouseReleasedToBeRemoved;
    }

    public boolean isShouldRemoveFromOnMouseReleased() {
        return shouldRemoveFromOnMouseReleased;
    }

    public void setShouldRemoveFromOnMouseReleased(boolean shouldRemoveFromOnMouseReleased) {
        this.shouldRemoveFromOnMouseReleased = shouldRemoveFromOnMouseReleased;
    }

    public boolean isShouldRemoveFromOnMouseDragged() {
        return shouldRemoveFromOnMouseDragged;
    }

    public void setShouldRemoveFromOnMouseDragged(boolean shouldRemoveFromOnMouseDragged) {
        this.shouldRemoveFromOnMouseDragged = shouldRemoveFromOnMouseDragged;
    }

    public boolean isShouldRemoveFromOnMousePressed() {
        return shouldRemoveFromOnMousePressed;
    }

    public void setShouldRemoveFromOnMousePressed(boolean shouldRemoveFromOnMousePressed) {
        this.shouldRemoveFromOnMousePressed = shouldRemoveFromOnMousePressed;
    }

    public boolean isShouldAddToOnMouseReleased() {
        return shouldAddToOnMouseReleased;
    }

    public void setShouldAddToOnMouseReleased(boolean shouldAddToOnMouseReleased) {
        this.shouldAddToOnMouseReleased = shouldAddToOnMouseReleased;
    }

    public boolean isShouldAddToOnMouseDragged() {
        return shouldAddToOnMouseDragged;
    }

    public void setShouldAddToOnMouseDragged(boolean shouldAddToOnMouseDragged) {
        this.shouldAddToOnMouseDragged = shouldAddToOnMouseDragged;
    }

    public boolean isShouldAddToOnMouseMoved() {
        return shouldAddToOnMouseMoved;
    }

    public void setShouldAddToOnMouseMoved(boolean shouldAddToOnMouseMoved) {
        this.shouldAddToOnMouseMoved = shouldAddToOnMouseMoved;
    }

    public boolean isShouldAddToOnMousePressed() {
        return shouldAddToOnMousePressed;
    }

    public void setShouldAddToOnMousePressed(boolean shouldAddToOnMousePressed) {
        this.shouldAddToOnMousePressed = shouldAddToOnMousePressed;
    }

    public boolean isShouldRemoveFromOnMouseMoved() {
        return shouldRemoveFromOnMouseMoved;
    }

    public void setShouldRemoveFromOnMouseMoved(boolean shouldRemoveFromOnMouseMoved) {
        this.shouldRemoveFromOnMouseMoved = shouldRemoveFromOnMouseMoved;
    }

}
