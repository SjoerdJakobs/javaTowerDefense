package MainPackage.TowerDefense;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.InputHandling.MouseEventCallback;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.function.Consumer;

public class TowerIcon extends StandardObject
{

    private MouseEventCallback onMousePressCallback;
    private MouseEventCallback onMouseDragCallback;
    private MouseEventCallback onMouseReleaseCallback;
    private MouseInput mouseInput;
    private Rectangle rectangle;
    private BoxCollider collider;
    private Vector2 pos;
    private Vector2 startPos;
    private double width;
    private double height;
    private double mouseOfsetX = 0;
    private double mouseOfsetY = 0;
    private boolean clicked = false;

    public TowerIcon(FrameworkProgram frameworkProgram, TowerMenu towerMenu, Vector2 pos) {
        super(frameworkProgram,true,false,true,true);
        this.mouseInput = MouseInput.getInstance();

        this.startPos = new Vector2();
        this.startPos.x = pos.x;
        this.startPos.y = pos.y;
        this.pos = pos;
        this.width = 45;
        this.height = 45;

        this.onMousePressCallback = this::OnMousePressed;
        this.onMouseDragCallback = this::OnMouseDragged;
        this.onMouseReleaseCallback = this::OnMouseReleased;
        mouseInput.getOnMousePressed().add(this.onMousePressCallback);
        mouseInput.getOnMouseDragged().add(this.onMouseDragCallback);
        mouseInput.getOnMouseReleased().add(this.onMouseReleaseCallback);

        this.collider = new BoxCollider(pos, width, height);
        this.collider.collisionCallback = this::OnCollision;
        this.collider.setColliderTag(ColliderTag.TEST);

        this.rectangle = new Rectangle((int) pos.x, (int) pos.y, (int) width, (int) height, 0);
        this.rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        this.rectangle.setRectangleColor(Color.blue);
    }

    public void OnMousePressed(MouseEvent e)
    {
        if(collider.ContainsPoint(mouseInput.getMousePos()))
        {
            clicked = true;
            mouseOfsetX = e.getX() - pos.x;
            mouseOfsetY = e.getY() - pos.y;
        }
    }

    public void OnMouseDragged(MouseEvent e) {
        if (clicked)
        {
            pos.x = e.getX() - mouseOfsetX;
            pos.y = e.getY() - mouseOfsetY;
            rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
            collider.setPos(pos);
        }
    }
    public void OnMouseReleased(MouseEvent e)
    {
        clicked = false;
        pos.x = startPos.x;
        pos.y = startPos.y;
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
    }

    public void OnCollision(Collider2D other) {

    }

    @Override
    protected void RenderLoop(double deltaTime) {
        rectangle.FilledDraw(getFrameworkProgram().getGraphics2D());
    }
}
