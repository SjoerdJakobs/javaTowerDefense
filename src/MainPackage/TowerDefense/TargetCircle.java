package MainPackage.TowerDefense;

import MainPackage.Program;
import MainPackage.TowerDefense.Units.EnemyUnit;
import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.Maths.Vector2;
import OOFramework.Sound.SoundPlayer;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class TargetCircle extends StandardObject {
    private int health;


    private final CircleCollider collider;
    private final Vector2 pos;
    private double radius;
    private final double fullRadius;
    private Circle circle;
    private Rectangle rectangle;

    public TargetCircle(Vector2 pos, double radius) {
        super(false, false, true, true, 2000, 1000);
        this.pos = pos;
        this.radius = radius;
        this.fullRadius = radius;
        this.health = (int) radius;

        //this is all the collision code you need to set it up
        this.collider = new CircleCollider(pos, radius);     //
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.setColliderTag(ColliderTag.TARGET);   //
        //////////////////////////////////////////////////////

        this.circle = new Circle((int) pos.x, (int) pos.y, (int) radius, 0);
        circle.getCircle2D().setPosition(new Point2D.Double(pos.x, pos.y));
        circle.setCircleColor(Color.red);
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        circle.FilledDraw(frameworkProgram.getGraphics2D());
    }

    public void OnCollision(Collider2D other) {
        if (other.getColliderTag() == ColliderTag.ENEMY_UNIT) {
            EnemyUnit enemy = (EnemyUnit) other.getOwnerObject();
            health -= enemy.getDamage();
            SoundPlayer.Play("Explosion39.wav", 0.85f);
            this.radius = health;
            this.circle = new Circle((int) pos.x, (int) pos.y, (int) radius, 0);
            circle.setCircleColor(Color.red);
            if (health > 19) {
                this.collider.radius = radius;
            } else if (health <= 0) {
                Program.getProgramInstance().ExitProgram();
                //System.exit(0);
            }
        }
    }
}
