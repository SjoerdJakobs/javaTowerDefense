package MainPackage.TowerDefense.Menu;

import MainPackage.Program;
import MainPackage.TowerDefense.TDTileData;
import MainPackage.TowerDefense.Towers.BulletTower;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.InputHandling.MouseEventCallback;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.geom.Point2D;

public class TowerIcon extends StandardObject {
    private final MouseEventCallback onMousePressCallback;
    private final MouseEventCallback onMouseDragCallback;
    private final MouseEventCallback onMouseReleaseCallback;

    private final MouseInput mouseInput;
    private final Rectangle rectangle;
    private final BoxCollider collider;
    private final Vector2 pos;
    private final Vector2 startPos;

    private boolean topLeftInPlaceable = false;
    private boolean topRightInPlaceable = false;
    private boolean bottomLeftInPlaceable = false;
    private boolean bottomRightInPlaceable = false;
    private boolean isCollidingWithTower = false;

    private final double width;
    private final double height;
    private double mouseOfsetX = 0;
    private double mouseOfsetY = 0;
    private boolean clicked = false;

    private final TDTileData[][] mapData;
    private final TowerMenu TM;

    public TowerIcon(TowerMenu towerMenu, Vector2 pos) {
        super(true, false, true, true);
        this.TM = towerMenu;

        this.mouseInput = MouseInput.getInstance();

        this.startPos = new Vector2(pos);
        this.pos = pos;
        this.width = 66;
        this.height = 66;

        this.mapData = Program.getProgramInstance().getGridManager().getMapData();

        this.onMousePressCallback = this::OnMousePressed;
        this.onMouseDragCallback = this::OnMouseDragged;
        this.onMouseReleaseCallback = this::OnMouseReleased;

        this.mouseInput.getOnMousePressedToBeAdded().add(this.onMousePressCallback);
        this.mouseInput.setShouldAddToOnMousePressed(true);
        this.mouseInput.getOnMouseDraggedToBeAdded().add(this.onMouseDragCallback);
        this.mouseInput.setShouldAddToOnMouseDragged(true);
        this.mouseInput.getOnMouseReleasedToBeAdded().add(this.onMouseReleaseCallback);
        this.mouseInput.setShouldAddToOnMouseReleased(true);

        this.collider = new BoxCollider(new Vector2(pos.x, pos.y + 8), width - 10, height - 10);
        this.collider.collisionCallback = this::OnCollision;
        this.collider.preCollisionCallback = this::OnPreCollision;
        this.collider.setColliderTag(ColliderTag.TOWER);

        this.rectangle = new Rectangle((int) pos.x, (int) pos.y, (int) width, (int) height, 0);
        this.rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        this.rectangle.setRectangleColor(Color.blue);
        this.rectangle.SetImageByFileName("bulletTower.png");
    }

    public void OnMousePressed(MouseEvent e) {
        if (collider.ContainsPoint(mouseInput.getMousePos())) {
            clicked = true;
            mouseOfsetX = e.getX() - pos.x;
            mouseOfsetY = e.getY() - pos.y;
        }
    }

    public void OnMouseDragged(MouseEvent e) {
        if (clicked) {
            pos.x = e.getX() - mouseOfsetX;
            pos.y = e.getY() - mouseOfsetY;
            rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
            collider.setPos(new Vector2(pos.x, pos.y + 8));
        }
    }

    public void OnMouseReleased(MouseEvent e) {
        if (clicked && bottomRightInPlaceable && bottomLeftInPlaceable && topLeftInPlaceable && topRightInPlaceable && !isCollidingWithTower) {
            new BulletTower(new Vector2(pos.x, pos.y + 9));
        }
        pos.x = startPos.x;
        pos.y = startPos.y;
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        collider.setPos(new Vector2(pos.x, pos.y + 8));
        clicked = false;
    }

    public void OnPreCollision() {
        topRightInPlaceable = false;
        topLeftInPlaceable = false;
        bottomRightInPlaceable = false;
        bottomLeftInPlaceable = false;
        isCollidingWithTower = false;
    }

    public void OnCollision(Collider2D other) {
        if (other.getColliderTag() == ColliderTag.PLACEABLE_AREA) {
            if (!topLeftInPlaceable) {
                if (other.ContainsPoint(new Vector2(pos.x - ((width - 10) / 2), pos.y + 8 - ((height - 10) / 2)))) {
                    topLeftInPlaceable = true;
                }
            }
            if (!topRightInPlaceable) {
                if (other.ContainsPoint(new Vector2(pos.x + ((width - 10) / 2), pos.y + 8 - ((height - 10) / 2)))) {
                    topRightInPlaceable = true;
                }
            }
            if (!bottomLeftInPlaceable) {
                if (other.ContainsPoint(new Vector2(pos.x - ((width - 10) / 2), pos.y + 8 + ((height - 10) / 2)))) {
                    bottomLeftInPlaceable = true;
                }
            }
            if (!bottomRightInPlaceable) {
                if (other.ContainsPoint(new Vector2(pos.x + ((width - 10) / 2), pos.y + 8 + ((height - 10) / 2)))) {
                    bottomRightInPlaceable = true;
                }
            }
        } else if (other.getColliderTag() == ColliderTag.TOWER) {
            isCollidingWithTower = true;
        }

    }

    @Override
    protected void RenderLoop(double deltaTime) {
        //rectangle.FilledDraw(frameworkProgram.getGraphics2D());
        rectangle.ImageDraw(frameworkProgram.getGraphics2D());
    }
}
