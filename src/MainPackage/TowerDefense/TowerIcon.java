package MainPackage.TowerDefense;

import MainPackage.Program;
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

    private Vector2 topRight;
    private Vector2 topLeft;
    private Vector2 BottomRight;
    private Vector2 BottomLeft;

    private double width;
    private double height;
    private double mouseOfsetX = 0;
    private double mouseOfsetY = 0;
    private boolean clicked = false;

    private TDTileData mapData[][];
    private TowerMenu TM;

    public TowerIcon(TowerMenu towerMenu, Vector2 pos) {
        super(true,false,true,true);
        TM = towerMenu;

        this.mouseInput = MouseInput.getInstance();

        this.startPos = new Vector2();
        this.startPos.x = pos.x;
        this.startPos.y = pos.y;
        this.pos = pos;
        this.width = 45;
        this.height = 45;

        this.mapData = Program.getProgramInstance().getGridManager().getMapData();

        this.onMousePressCallback = this::OnMousePressed;
        this.onMouseDragCallback = this::OnMouseDragged;
        this.onMouseReleaseCallback = this::OnMouseReleased;

        mouseInput.getOnMousePressedToBeAdded().add(this.onMousePressCallback);
        mouseInput.setShouldAddToOnMousePressed(true);
        mouseInput.getOnMouseDraggedToBeAdded().add(this.onMouseDragCallback);
        mouseInput.setShouldAddToOnMouseDragged(true);
        mouseInput.getOnMouseReleasedToBeAdded().add(this.onMouseReleaseCallback);
        mouseInput.setShouldAddToOnMouseReleased(true);

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
        if(clicked) {
            new TowerIcon(TM, new Vector2(e.getX() - mouseOfsetX, e.getY() - mouseOfsetY));
        }
        pos.x = startPos.x;
        pos.y = startPos.y;
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        collider.setPos(pos);
        clicked = false;
        //if(mapData[(int)Math.floor((e.getX() - mouseOfsetX - (width*0.5)))/34][(int)Math.floor((e.getX() - mouseOfsetX - (width*0.5))/34)].canPlaceTower)
        //{
            ///System.out.println("innnnnnn");
        //}
    }

    public void OnCollision(Collider2D other) {

    }

    @Override
    protected void RenderLoop(double deltaTime) {
        rectangle.FilledDraw(frameworkProgram.getGraphics2D());
    }
}
