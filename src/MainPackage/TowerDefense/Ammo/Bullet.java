package MainPackage.TowerDefense.Ammo;

import MainPackage.TowerDefense.Units.EnemyUnit;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.Maths.Vector2;

import java.awt.*;

public class Bullet extends AmmoBase {
    public Bullet(EnemyUnit target, Vector2 startPos, double damage, double moveSpeed) {
        super(target, startPos, damage, moveSpeed);
        lifeDuration = 1.5;
        bulletArt = new Rectangle((int) startPos.x, (int) startPos.y, 16, 30, 0);
        bulletArt.SetImageByFileName("bulletSmall.png");
        collider = new BoxCollider(startPos, 16, 16);
        collider.setColliderTag(ColliderTag.STANDARD_BULLET);
        direction = enemyTarget.getPos().Subtract(this.pos);
        direction.NormalizeThis();
        bulletArt.getSquare2D().setRotation((float) Vector2.LookAtVector(pos, enemyTarget.getPos()));
        pos.AddToThis(direction.MultiplyByDouble(moveSpeed / 25));//now it spawns at the end of the barrel
        bulletArt.getSquare2D().setPosition(new Point((int) pos.x, (int) pos.y));
        collider.setPos(pos);
        this.collider.collisionCallback = this::OnCollision;
    }

    public void OnCollision(Collider2D other) {
        if (other.getColliderTag() == ColliderTag.ENEMY_UNIT) {
            EnemyUnit hitUnit = (EnemyUnit) other.getOwnerObject();
            hitUnit.TakeDamage((int) damage);
            collider.OnDestroy();
            this.Destroy();
        }
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        if (enemyTarget.getHealth() > 0) {
            direction = enemyTarget.getPos().Subtract(this.pos);
            direction.NormalizeThis();
            bulletArt.getSquare2D().setRotation((float) Vector2.LookAtVector(pos, enemyTarget.getPos()));
        }

        this.pos.AddToThis(direction.MultiplyByDouble(moveSpeed * deltaTime));
        bulletArt.getSquare2D().setPosition(new Point((int) pos.x, (int) pos.y));
        collider.setPos(pos);
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        bulletArt.ImageDraw(frameworkProgram.getGraphics2D());
    }

    @Override
    protected void Destroy() {
        super.Destroy();
        collider.OnDestroy();
    }
}
