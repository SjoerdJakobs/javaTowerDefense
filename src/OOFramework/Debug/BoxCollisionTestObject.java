package OOFramework.Debug;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.geom.Point2D;

public class BoxCollisionTestObject extends StandardObject {
    private final BoxCollider collider;
    private final Vector2 pos;
    private final double width;
    private final double height;
    private final boolean atMouse;
    private final Rectangle rectangle;

    private double mouseOfsetX = 0;
    private double mouseOfsetY = 0;

    public BoxCollisionTestObject(FrameworkProgram frameworkProgram, Vector2 pos, double width, double height, boolean atMouse) {
        super(true, true, true, true, 1000, 1000);
        this.atMouse = atMouse;
        this.pos = pos;
        this.width = width;
        this.height = height;

        //this is all the collision code you need to set it up
        this.collider = new BoxCollider(pos, width, height);  //
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.setColliderTag(ColliderTag.TEST);     //
        //////////////////////////////////////////////////////

        this.rectangle = new Rectangle((int) pos.x, (int) pos.y, (int) width, (int) height, 0);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        rectangle.setRectangleColor(Color.blue);
        if (atMouse) {
            frameworkProgram.getCanvas().setOnMouseDragged(e ->
            {

            });
        }

    }

    @Override
    protected void RenderLoop(double deltaTime) {
        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        rectangle.FilledDraw(frameworkProgram.getGraphics2D());
    }

    public void OnMouseDragged(MouseEvent e) {
        pos.x = e.getX() - mouseOfsetX;
        pos.y = e.getY() - mouseOfsetY;
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        collider.setPos(pos);
    }

    public void OnMousePressed(MouseEvent e) {
        mouseOfsetX = e.getX() - pos.x;
        mouseOfsetY = e.getY() - pos.y;
    }

    public void OnCollision(Collider2D other) {
        //System.out.println(collider.getIsColliding());
        if (!atMouse) {
            //pos.x++;
            rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        }
    }
}
