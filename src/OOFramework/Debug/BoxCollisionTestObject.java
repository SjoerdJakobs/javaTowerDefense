package OOFramework.Debug;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class BoxCollisionTestObject extends StandardObject
{
    private BoxCollider collider;
    private Vector2 pos;
    private double width;
    private double height;
    private boolean atMouse;
    private Rectangle rectangle;

    public BoxCollisionTestObject(FrameworkProgram frameworkProgram, Vector2 pos, double width, double height, boolean atMouse)
    {
        super(frameworkProgram, true, true, true, true, 1000, 1000);
        this.atMouse = atMouse;
        this.pos = pos;
        this.width = width;
        this.height = height;

        //this is all the collision code you need to set it up
        this.collider = new BoxCollider(pos,width,height);  //
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.setColliderTag(ColliderTag.TEST);     //
        //////////////////////////////////////////////////////

        this.rectangle = new Rectangle((int)pos.x, (int)pos.y, (int)width,(int)height,0);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x,pos.y));
        rectangle.setRectangleColor(Color.blue);
        if(atMouse)
        {
            frameworkProgram.getCanvas().setOnMouseDragged(e ->
            {
                pos.x = e.getX();
                pos.y = e.getY();
                rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x,pos.y));
                collider.setPos(pos);
            });
        }
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        getFrameworkProgram().getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        rectangle.FilledDraw(getFrameworkProgram().getGraphics2D());
    }

    public void OnCollision(Collider2D other)
    {
        //System.out.println(collider.getIsColliding());
        if(!atMouse)
        {
            pos.x++;
            rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x,pos.y));
        }
    }
}
