package MainPackage.TowerDefense.Ammo;

import MainPackage.TowerDefense.Units.EnemyUnit;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;

public abstract class AmmoBase extends StandardObject {
    protected final boolean followTarget;
    protected EnemyUnit enemyTarget;
    protected double moveSpeed;
    protected double lifeDuration;
    protected double lifeCounter = 0;
    protected double damage;
    protected Vector2 pos;
    protected Vector2 direction;
    protected Rectangle bulletArt;
    protected BoxCollider collider;

    protected AmmoBase(EnemyUnit target, Vector2 startPos, double damage, double moveSpeed) {
        this(target, startPos, damage, moveSpeed, true);
    }

    protected AmmoBase(EnemyUnit target, Vector2 startPos, double damage, double moveSpeed, boolean shouldFollowTarget) {
        super(false, true, true, true);
        this.followTarget = shouldFollowTarget;
        this.pos = startPos;
        this.enemyTarget = target;
        this.damage = damage;
        this.moveSpeed = moveSpeed;
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        lifeCounter += deltaTime;
        if (lifeCounter >= lifeDuration) {
            Destroy();
        }
    }

    @Override
    protected void Destroy() {
        super.Destroy();
    }
}
