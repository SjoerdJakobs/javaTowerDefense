package OOFramework.Debug;

import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.atomic.AtomicBoolean;

public class CircleCollisionTestObject extends StandardObject
{
    private CircleCollider collider;
    private Vector2 pos;
    private double radius;
    private boolean atMouse;
    private Circle circle;

    public CircleCollisionTestObject(FrameworkProgram frameworkProgram, Vector2 pos, double radius, boolean atMouse)
    {
        super(frameworkProgram, true, true, true, true, 1000, 1000);
        this.atMouse = atMouse;
        this.pos = pos;
        this.radius = radius;

        //this is all the collision code you need to set it up
        this.collider = new CircleCollider(pos,radius);     //
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.setColliderTag(ColliderTag.ENEMY_UNIT);     //
        //////////////////////////////////////////////////////

        this.circle = new Circle((int)pos.x, (int)pos.y, (int)radius,0);
        circle.getCircle2D().setPosition(new Point2D.Double(pos.x,pos.y));
        circle.setCircleColor(Color.orange);

        if(atMouse)
        {
            frameworkProgram.getCanvas().setOnMouseDragged(e ->
            {
                pos.x = e.getX();
                pos.y = e.getY();
                circle.getCircle2D().setPosition(new Point2D.Double(pos.x,pos.y));
                collider.setPos(pos);
            });
        }
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        getFrameworkProgram().getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        circle.FilledDraw(getFrameworkProgram().getGraphics2D());
    }

    public void OnCollision(Collider2D other)
    {
        //System.out.println(other.getColliderTag());
        if(!atMouse)
        {
            pos.x++;
            circle.getCircle2D().setPosition(new Point2D.Double(pos.x,pos.y));
        }
    }
}
