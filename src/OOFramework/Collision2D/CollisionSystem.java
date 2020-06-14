package OOFramework.Collision2D;

import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.Modules.ASSERT_MSG;
import OOFramework.StandardObject;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CollisionSystem extends StandardObject {

    private ArrayList<Collider2D> allColliders;
    private ArrayList<Collider2D> collidersToBeAdded;
    private ArrayList<Collider2D> collidersToBeRemoved;
    private boolean shouldAddToColliders = false;
    private boolean shouldRemoveFromColliders = false;
    private boolean emptyTheSystem = false;

    private static CollisionSystem INSTANCE = null;
    public static CollisionSystem getInstance() {
        if (INSTANCE == null) {
            synchronized (CollisionSystem.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CollisionSystem(FrameworkProgram.getProgramInstance());
                }
            }
        }
        return INSTANCE;
    }

    private CollisionSystem(FrameworkProgram frameworkProgram) {
        super(frameworkProgram, false, true, false, true, 1000, 1_000_000);
        allColliders = new ArrayList<Collider2D>();
        collidersToBeAdded = new ArrayList<Collider2D>();
        collidersToBeRemoved = new ArrayList<Collider2D>();

    }

    @Override
    protected void Start() {
        super.Start();
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        boolean hasCollided;
        for (int i = 0; i < allColliders.size(); i++) {
            allColliders.get(i).getPreCollisionCallback().run();
        }
        for (int i = 0; i < allColliders.size(); i++) {
            hasCollided = false;
            for (int j = 0; j < allColliders.size(); j++) {
                if (i != j) {
                    if (allColliders.get(i).Collide(allColliders.get(j).getColliderType(), allColliders.get(j))) {
                        hasCollided = true;
                        allColliders.get(i).getCollisionCallback().collide(allColliders.get(j));
                        allColliders.get(j).getCollisionCallback().collide(allColliders.get(i));
                    }
                }
            }
            allColliders.get(i).setIsColliding(hasCollided);
        }
        DebugDrawer.DebugCollision(allColliders);

        if (shouldAddToColliders) {
            for (Collider2D c : collidersToBeAdded) {
                allColliders.add(c);
            }
            collidersToBeAdded.clear();
            shouldAddToColliders = false;
        }

        if (shouldRemoveFromColliders) {
            for (Collider2D c : collidersToBeRemoved) {
                allColliders.remove(c);
            }
            collidersToBeRemoved.clear();
            shouldRemoveFromColliders = false;
        }

        if (emptyTheSystem) {
            allColliders.clear();
            emptyTheSystem = false;
        }
    }

    public void emptySystem() {
        emptyTheSystem = true;
    }

    public ArrayList<Collider2D> getCollidersToBeAdded() {
        return collidersToBeAdded;
    }

    public ArrayList<Collider2D> getCollidersToBeRemoved() {
        return collidersToBeRemoved;
    }

    public boolean isShouldAddToColliders() {
        return shouldAddToColliders;
    }

    public void setShouldAddToColliders(boolean shouldAddToColliders) {
        this.shouldAddToColliders = shouldAddToColliders;
    }

    public boolean isShouldRemoveFromColliders() {
        return shouldRemoveFromColliders;
    }

    public void setShouldRemoveFromColliders(boolean shouldRemoveFromColliders) {
        this.shouldRemoveFromColliders = shouldRemoveFromColliders;
    }
}
