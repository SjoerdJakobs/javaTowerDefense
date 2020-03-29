package MainPackage.TowerDefense;

import MainPackage.Program;
import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Circle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BFSTile;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class EnemyUnit extends StandardObject
{
    private CircleCollider collider;
    private Vector2 pos;
    private int gridPosX = 0;
    private int gridPosY = 0;
    private int nextGridPosX = 0;
    private int nextGridPosY = 0;
    private double turnDelay;
    private double radius;
    private Circle circle;
    private Vector2 direction;
    private String routeName;

    private BFSTile[][] movementGrid;

    private int health;
    private int movSpeed;
    private int damage;

    public EnemyUnit(FrameworkProgram frameworkProgram, Vector2 spawnPos, int health, int size, int movSpeed, int damage, double turnDelay, String routeName)
    {
        super(frameworkProgram, false, true, true, true, 1500, 1000);

        //get get get get get :p
        this.movementGrid = Program.getProgramInstance().getGridManager().getBFS().getTileMap();
        this.pos =          spawnPos;
        this.radius =       size*0.5;
        this.health =       health;
        this.movSpeed =     movSpeed;
        this.damage =       damage;
        this.routeName =    routeName;
        this.turnDelay =    turnDelay;

        //this is all the collision code you need to set it up/
        this.collider = new CircleCollider(pos,radius);      //
        this.collider.collisionCallback = this::OnCollision; //
        this.collider.setColliderTag(ColliderTag.ENEMY_UNIT);//
        this.collider.setOwnerObject(this);                  //
        ///////////////////////////////////////////////////////

        this.circle = new Circle((int)pos.x, (int)pos.y, (int)radius,0);
        circle.getCircle2D().setPosition(new Point2D.Double(pos.x,pos.y));
        circle.setCircleColor(Color.yellow);
        AlignWithGridPos(100);
    }

    private double alignCounter = 0;
    public void AlignWithGridPos(double deltaTime)
    {
        nextGridPosX = (int)Math.floor(pos.x/32);
        nextGridPosY = (int)Math.floor((pos.y+4)/32);

        if(nextGridPosY != gridPosY || gridPosX != nextGridPosX)
        {
            alignCounter += deltaTime;
            if(alignCounter > turnDelay)
            {
                gridPosY = nextGridPosY;
                gridPosX = nextGridPosX;
                alignCounter = 0;
            }
        }
    }

    public void OnCollision(Collider2D other)
    {
        if(other.getColliderTag() == ColliderTag.TARGET)
        {
            setShouldDestruct(true);
        }
    }

    @Override
    protected void MainLoop(double deltaTime)
    {
        this.pos = pos.Add(movementGrid[gridPosX][gridPosY].routes.get(routeName).MultiplyByDouble((deltaTime*movSpeed)));
        circle.getCircle2D().setPosition(new Point2D.Double(pos.x,pos.y));
        collider.setPos(pos);
        AlignWithGridPos(deltaTime);
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        getFrameworkProgram().getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        circle.FilledDraw(getFrameworkProgram().getGraphics2D());
    }

    @Override
    protected void Destroy()
    {
        super.Destroy();
        collider.OnDestroy();
    }

    public CircleCollider getCollider()
    {
        return collider;
    }

    public void setCollider(CircleCollider collider)
    {
        this.collider = collider;
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public void setPos(Vector2 pos)
    {
        this.pos = pos;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public int getMovSpeed()
    {
        return movSpeed;
    }

    public void setMovSpeed(int movSpeed)
    {
        this.movSpeed = movSpeed;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public Circle getCircle()
    {
        return circle;
    }

    public void setCircle(Circle circle)
    {
        this.circle = circle;
    }
}
