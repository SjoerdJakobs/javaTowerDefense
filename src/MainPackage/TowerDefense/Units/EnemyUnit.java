package MainPackage.TowerDefense.Units;

import MainPackage.Program;
import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BFSTile;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class EnemyUnit extends StandardObject {
    private CircleCollider collider;
    private Vector2 pos;
    private int gridPosX = 0;
    private int gridPosY = 0;
    private int nextGridPosX = 0;
    private int nextGridPosY = 0;
    private final double turnDelay;
    private double radius;
    private Circle circle;
    private Vector2 direction;
    private Vector2 avoidUnitAdjustment;
    private Vector2 avoidWallAdjustment;
    private final String routeName;

    private final BFSTile[][] movementGrid;
    private boolean startCheckingForWallCollision;
    private double health;
    private final double startHealth;
    private int movSpeed;
    private int damage;
    private final double size;
    private boolean hasAllteredDirection = false;
    private double alignCounter = 0;

    public EnemyUnit(Vector2 spawnPos, int health, int size, int movSpeed, int damage, double turnDelay, String routeName) {
        super(false, true, true, true, 1500, 1100);

        //get get get get get :p
        this.movementGrid = Program.getProgramInstance().getGridManager().getBFS().getTileMap();
        this.pos = spawnPos;
        this.size = size;
        this.radius = size * 0.5;
        this.health = health;
        this.movSpeed = movSpeed;
        this.damage = damage;
        this.routeName = routeName;
        this.turnDelay = turnDelay;
        this.health = health;
        this.startHealth = health;

        direction = new Vector2();
        avoidUnitAdjustment = new Vector2();
        avoidWallAdjustment = new Vector2();

        //this is all the collision code you need to set it up/
        this.collider = new CircleCollider(pos, radius);      //
        this.collider.collisionCallback = this::OnCollision; //
        this.collider.setColliderTag(ColliderTag.ENEMY_UNIT);//
        this.collider.setOwnerObject(this);                  //
        ///////////////////////////////////////////////////////

        this.circle = new Circle((int) pos.x, (int) pos.y, (int) radius, 0);
        circle.getCircle2D().setPosition(new Point2D.Double(pos.x, pos.y));
        circle.setCircleColor(Color.green);
        AlignWithGridPos(100);
    }

    public void AlignWithGridPos(double deltaTime) {
        nextGridPosX = (int) Math.floor(pos.x / 32);
        nextGridPosY = (int) Math.floor((pos.y + 4) / 32);

        if (nextGridPosY != gridPosY || gridPosX != nextGridPosX || hasAllteredDirection) {
            alignCounter += deltaTime;
            if (alignCounter >= turnDelay) {
                gridPosY = nextGridPosY;
                gridPosX = nextGridPosX;
                if (gridPosX < 0) {
                    gridPosX = 0;
                }
                if (gridPosY < 0) {
                    gridPosY = 0;
                }
                hasAllteredDirection = false;
                alignCounter = 0;
            }
        }
    }

    public void AvoidWalls() {
        nextGridPosX = (int) Math.floor(pos.x / 32);
        nextGridPosY = (int) Math.floor((pos.y + 4) / 32);
        avoidWallAdjustment = new Vector2();
        BFSTile T = movementGrid[nextGridPosX][nextGridPosY];
        if (T.hasWalToTheLeft) {
            if (pos.x < nextGridPosX * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(20, 0));
            }
        }
        if (T.hasWalToTheRight) {
            if (pos.x > nextGridPosX * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(-20, 0));
            }
        }
        if (T.hasWalBelow) {
            if (pos.y > nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(0, -20));
            }
        }
        if (T.hasWalAbove) {
            if (pos.y < nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(0, 20));
            }
        }
        if (T.hasWalToTheBottomLeft) {
            if (pos.x < nextGridPosX * 32 + size * 0.6 && pos.y > nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(10, -10));
            }
        }
        if (T.hasWalToTheBottomRight) {
            if (pos.x > nextGridPosX * 32 + size * 0.6 && pos.y > nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(-10, -10));
            }
        }
        if (T.hasWalToTheTopLeft) {
            if (pos.y > nextGridPosY * 32 + size * 0.6 && pos.y < nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(10, 10));
            }
        }
        if (T.hasWalToTheTopRight) {
            if (pos.x > nextGridPosX * 32 + size * 0.6 && pos.y < nextGridPosY * 32 + size * 0.6) {
                avoidWallAdjustment.AddToThis(new Vector2(-10, 10));
            }
        }
    }

    public void TakeDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            health = 0;
            Destroy();
        }
        circle.getCircle2D().setScale((float) (health / startHealth));
        circle.setCircleColor(LerpBetweenColors(Color.red, Color.green, (float) (health / startHealth)));
    }

    public void OnCollision(Collider2D other) {
        if (other.getColliderTag() == ColliderTag.ENEMY_UNIT) {
            //avoidUnitAdjustment = this.pos.subtract(other.getPos());
            //avoidUnitAdjustment.NormalizeThis();
            //avoidUnitAdjustment.MultiplyThisByDouble(2);
            //hasAllteredDirection = true;
            //alignCounter += turnDelay*0.5;
        }
        if (other.getColliderTag() == ColliderTag.TARGET) {
            setShouldDestruct(true);
        }
    }

    @Override
    protected void MainLoop(double deltaTime) {
        AvoidWalls();
        if (movementGrid[gridPosX][gridPosY].routes.get(routeName) != null) {

            direction = movementGrid[gridPosX][gridPosY].routes.get(routeName).Add(avoidWallAdjustment);//.Add(avoidUnitAdjustment.Add(avoidWallAdjustment));
            direction.NormalizeThis();
            this.pos = this.pos.Add(direction.MultiplyByDouble((deltaTime * movSpeed)));
            circle.getCircle2D().setPosition(new Point2D.Double(pos.x, pos.y));
            collider.setPos(pos);
            AlignWithGridPos(deltaTime);
            direction = new Vector2();
            avoidUnitAdjustment = new Vector2();
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        circle.FilledDraw(frameworkProgram.getGraphics2D());
    }

    //http://www.java2s.com/example/csharp/system.drawing/lerp-between-two-color.html
    public Color LerpBetweenColors(Color c1, Color c2, float p) {
        double bk = (1 - p);
        double a = c1.getAlpha() * bk + c2.getAlpha() * p;
        double r = c1.getRed() * bk + c2.getRed() * p;
        double g = c1.getGreen() * bk + c2.getGreen() * p;
        double b = c1.getBlue() * bk + c2.getBlue() * p;
        return new Color((int) r, (int) g, (int) b, (int) a);
    }

    @Override
    protected void Destroy() {
        super.Destroy();
        collider.OnDestroy();
    }

    public CircleCollider getCollider() {
        return collider;
    }

    public void setCollider(CircleCollider collider) {
        this.collider = collider;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMovSpeed() {
        return movSpeed;
    }

    public void setMovSpeed(int movSpeed) {
        this.movSpeed = movSpeed;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
