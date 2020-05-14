package MainPackage.TowerDefense;

import MainPackage.Program;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SpawnBlock extends StandardObject
{
    private BoxCollider collider;
    private Vector2     pos;
    private Vector2     posFromCorner;
    private double      width;
    private double      height;
    private boolean     atMouse;
    private Rectangle   rectangle;

    private ArrayList<EnemyUnit> spawnedUnits;

    public SpawnBlock(FrameworkProgram frameworkProgram, Vector2 pos, double width, double height)
    {
        super(frameworkProgram, false, true, true, true, 2000, 1000);
        this.pos = pos;
        this.posFromCorner = new Vector2(this.pos.x - width*0.5, this.pos.y - height*0.5);
        this.width = width;
        this.height = height;

        //this is all the collision code you need to set it up
        //this.collider = new BoxCollider(pos,width,height);  //
        //this.collider.collisionCallback = this::OnCollision;//
        //this.collider.setColliderTag(ColliderTag.SPAWN_BOX);//
        //////////////////////////////////////////////////////

        this.rectangle = new Rectangle((int)pos.x, (int)pos.y, (int)width,(int)height,0);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x,pos.y));
        rectangle.setRectangleColor(Color.green);

        spawnedUnits = new ArrayList<>();

        //SpawnUnit();
    }

    private double spawnTimer = 0;
    private double spawnDelay = 0.05;
    private double spawncounter = 500;
    @Override
    protected void MainLoop(double deltaTime)
    {
        super.MainLoop(deltaTime);
        spawnTimer += deltaTime;
        if(spawnTimer >= spawnDelay)
        {
            if(spawncounter > 0)
            {
                SpawnUnit();
                SpawnUnit();
                spawncounter -=2;
            }
            spawnTimer -= spawnDelay;
        }
    }

    private void SpawnUnit()
    {
        double newX = (this.posFromCorner.x + 15) + ((Math.random() * (width-15)));
        double newY = (this.posFromCorner.y + 15) + ((Math.random() * (height-20)));
        spawnedUnits.add(new EnemyUnit(getFrameworkProgram(),new Vector2(newX, newY),10,26,100,5,0.25,"route0"));
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        getFrameworkProgram().getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        rectangle.FilledDraw(getFrameworkProgram().getGraphics2D());
    }

    public void OnCollision(Collider2D other)
    {
        //System.out.println("colliding box");
    }
}
