package MainPackage;

import MainPackage.TowerDefense.*;
import MainPackage.TowerDefense.Debug.BoxCollisionTestObject;
import MainPackage.TowerDefense.Debug.CircleCollisionTestObject;
import MainPackage.TowerDefense.Debug.DebugDrawer;
import OOFramework.Collision2D.CollisionSystem;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import javafx.stage.Stage;

import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.*;
import static javafx.application.Application.launch;

public class Program extends FrameworkProgram
{
    private static Program  programInstance;
    public  static Program  getProgramInstance()
    {
        return programInstance;
    }

    private DebugDrawer     debugDrawer;
    private GridManager     gridManager;
    private CollisionSystem collisionSystem;
    private TargetCircle    targetCircleObject;
    private SpawnBlock      spawnBlockObject;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        programInstance = this;

        //used by other classes for drawing their debugging;
        debugDrawer = new DebugDrawer(this);

        //creates the map and movement grid
        gridManager = new GridManager(this,this);

        //this manages all the collisions
        collisionSystem = new CollisionSystem(this);

        //the target which the units will have to kill, also has the health bar in it
        targetCircleObject = new TargetCircle(this,new Vector2(1904,556), 150);

        //object respongsible for spawning the enemy units
        spawnBlockObject = new SpawnBlock(this,new Vector2(32, 492), 64,608);

        PlaceTowerBlock placeTowerBlock0 = new PlaceTowerBlock(new Vector2(496, 556), 64,450);
        PlaceTowerBlock placeTowerBlock1 = new PlaceTowerBlock(new Vector2(641, 348), 224,32);

        PlaceTowerBlock placeTowerBlock2 = new PlaceTowerBlock(new Vector2(992, 428), 96,450);
        PlaceTowerBlock placeTowerBlock3 = new PlaceTowerBlock(new Vector2(831, 605), 224,96);

        PlaceTowerBlock placeTowerBlock4 = new PlaceTowerBlock(new Vector2(1266, 556), 64,450);
        PlaceTowerBlock placeTowerBlock5 = new PlaceTowerBlock(new Vector2(1411, 348), 224,32);

        //EnemyUnit unit = new EnemyUnit(this,new Vector2(32, 492),10,26,100,5,0.25,"route0");


        //2 test classes
        CircleCollisionTestObject circleCollisionTestObject1 = new CircleCollisionTestObject(this,new Vector2(600,500), 100, true);
        //BoxCollisionTestObject boxCollisionTestObject1 = new BoxCollisionTestObject(this,new Vector2(600,500), 100,100, false);
        //BoxCollisionTestObject boxCollisionTestObject2 = new BoxCollisionTestObject(this,new Vector2(600,500), 100,100, true);

    }

    @Override
    protected void Init() {
        super.Init();

    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();

    }

    @Override
    public void ExitProgram() {
        super.ExitProgram();
    }

    public DebugDrawer getDebugDrawer()
    {
        return debugDrawer;
    }

    public GridManager getGridManager()
    {
        return gridManager;
    }

    public void setGridManager(GridManager gridManager)
    {
        this.gridManager = gridManager;
    }

    public CollisionSystem getCollisionSystem()
    {
        return collisionSystem;
    }

    public void setCollisionSystem(CollisionSystem collisionSystem)
    {
        this.collisionSystem = collisionSystem;
    }

    public TargetCircle getTargetCircleObject()
    {
        return targetCircleObject;
    }

    public void setTargetCircleObject(TargetCircle targetCircleObject)
    {
        this.targetCircleObject = targetCircleObject;
    }

    public SpawnBlock getSpawnBlockObject()
    {
        return spawnBlockObject;
    }

    public void setSpawnBlockObject(SpawnBlock spawnBlockObject)
    {
        this.spawnBlockObject = spawnBlockObject;
    }
}
