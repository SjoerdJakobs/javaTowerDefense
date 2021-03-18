package MainPackage.TowerDefense.Towers;

import MainPackage.TowerDefense.Ammo.Bullet;
import MainPackage.TowerDefense.AmmoTypeEnum;
import MainPackage.TowerDefense.Units.EnemyUnit;
import MainPackage.TowerDefense.Units.SpawnBlock;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.InputHandling.MouseEventCallback;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.Sound.SoundPlayer;
import OOFramework.StandardObject;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public abstract class TowerBase extends StandardObject {
    protected final MouseEventCallback onMousePressCallback;
    protected final MouseInput mouseInput;
    protected final Rectangle towerBuilding;
    protected final Rectangle towerGun;
    protected final Circle rangeIndicator;
    protected final BoxCollider collider;
    protected final Vector2 pos;

    protected final Vector2 topRight;
    protected final Vector2 topLeft;
    protected final Vector2 BottomRight;
    protected final Vector2 BottomLeft;
    protected final SpawnBlock spawnBlock;
    protected int range;
    protected double damage;
    protected double attackCooldown;
    protected double attackCooldownCounter;
    protected int upgradeLevel;
    protected AmmoTypeEnum ammo;
    protected EnemyUnit target;
    protected boolean hasTarget;
    protected double targetDistance;
    protected boolean showRange;
    private final int width;
    private final int height;

    protected TowerBase(Vector2 _pos, int size, int gunOffset, int colliderOffset, int range) {
        super(false, true, true, true, 1000, 1000);

        this.mouseInput = MouseInput.getInstance();
        this.onMousePressCallback = this::OnMousePressed;
        this.mouseInput.getOnMousePressedToBeAdded().add(this.onMousePressCallback);
        this.mouseInput.setShouldAddToOnMousePressed(true);

        this.pos = new Vector2(_pos);
        this.width = size;
        this.height = size;
        this.range = range;

        this.topLeft = new Vector2(pos.x - (width / 2), pos.y - (height / 2));
        this.topRight = new Vector2(pos.x + (width / 2), pos.y - (height / 2));
        this.BottomLeft = new Vector2(pos.x - (width / 2), pos.y + (height / 2));
        this.BottomRight = new Vector2(pos.x + (width / 2), pos.y + (height / 2));

        this.collider = new BoxCollider(pos, width - colliderOffset, height - colliderOffset);
        this.collider.setColliderTag(ColliderTag.TOWER);

        this.towerBuilding = new Rectangle((int) pos.x, (int) pos.y, width, height, 0);
        this.towerBuilding.setRectangleColor(Color.BLUE);
        this.towerGun = new Rectangle((int) pos.x, (int) pos.y, width + gunOffset, height + gunOffset, 0);
        this.towerGun.setRectangleColor(Color.BLUE);
        this.rangeIndicator = new Circle((int) pos.x, (int) pos.y, range, 0);

        this.hasTarget = false;
        this.showRange = false;
        this.spawnBlock = SpawnBlock.getInstance();
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        FollowTarget();
        if (attackCooldownCounter >= attackCooldown) {
            if (hasTarget) {
                Fire();
                attackCooldownCounter = 0;
            }
        }
        attackCooldownCounter += deltaTime;
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);

        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        towerBuilding.ImageDraw(frameworkProgram.getGraphics2D());
        //towerGun.Draw(frameworkProgram.getGraphics2D());
        towerGun.ImageDraw(frameworkProgram.getGraphics2D());
        if (showRange) {
            rangeIndicator.Draw(frameworkProgram.getGraphics2D());
        }
    }

    protected void FollowTarget() {
        double dist = 0;
        //towerGun.getSquare2D().setRotation((float) Vector2.LookAtVector(this.pos.Subtract(new Vector2(0,0)),mouseInput.getMousePos()));

        if (!hasTarget) {
            for (EnemyUnit eu : spawnBlock.getSpawnedUnits()) {
                dist = Vector2.Distance(eu.getPos(), this.pos);
                if (dist <= range && eu.getHealth() > 0) {
                    target = eu;
                    hasTarget = true;
                    towerGun.getSquare2D().setRotation((float) Vector2.LookAtVector(this.pos, target.getPos()));
                    break;
                }
            }
        } else {
            if (target != null) {
                dist = Vector2.Distance(target.getPos(), this.pos);
                if (dist > range) {
                    hasTarget = false;
                }
                towerGun.getSquare2D().setRotation((float) Vector2.LookAtVector(this.pos, target.getPos()));
            } else {
                hasTarget = false;
            }
        }
    }

    protected void Fire() {
        switch (ammo) {
            case BULLETS:
                if (Math.random() > 0.66) {
                    SoundPlayer.Play("cannon_shot_1.wav", 0.70f);
                } else if (Math.random() > 0.33) {
                    SoundPlayer.Play("cannon_shot_1.wav", 0.75f);
                } else {
                    SoundPlayer.Play("cannon_shot_1.wav", 0.80f);
                }
                Bullet b = new Bullet(target, new Vector2(pos), damage, 1200);
                hasTarget = false;

                break;
            case ROCKETS:

                break;
            case LASER:

                break;
        }
    }

    public void OnMousePressed(MouseEvent e) {
        //System.out.println("nou");
        if (collider.ContainsPoint(mouseInput.getMousePos())) {
            showRange = !showRange;
        }
    }

    public Vector2 getPos() {
        return pos;
    }
}
