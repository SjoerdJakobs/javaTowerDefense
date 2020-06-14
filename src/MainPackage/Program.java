package MainPackage;

import MainPackage.TowerDefense.*;
import OOFramework.Collision2D.CollisionSystem;
import OOFramework.Debug.BoxCollisionTestObject;
import OOFramework.Debug.CharacterCube;
import OOFramework.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.InputHandling.KeyboardInput;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.Sound.SoundPlayer;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static OOFramework.Modules.CONSTANTS.CANVAS_HEIGHT;
import static OOFramework.Modules.CONSTANTS.CANVAS_WIDTH;

public class Program extends FrameworkProgram
{
    private MouseInput mouseInput;
    private KeyboardInput keyboardInput;
    private DebugDrawer debugDrawer;
    private GridManager gridManager;
    private CollisionSystem collisionSystem;
    private TargetCircle targetCircleObject;
    private SpawnBlock spawnBlockObject;
    private TowerMenu towerMenu;
    private TowerIcon towerIcon;

    private static Program programInstance = null;
    public static Program getProgramInstance() {
        return programInstance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        programInstance = this;
        SoundPlayer.Loop("BensoundFunnysong.wav", 0.65f);

        //used by other classes to get mouse input
        mouseInput = MouseInput.getInstance();

        //used by other classes to get keyboard input
        keyboardInput = KeyboardInput.getInstance();

        //used by other classes for drawing their debugging;
        debugDrawer = DebugDrawer.getInstance();

        //creates the map and movement grid
        //gridManager = new GridManager(this,this);

        //this manages all the collisions
        collisionSystem = CollisionSystem.getInstance();

        //the target which the units will have to kill, also has the health bar in it
        /*targetCircleObject = new TargetCircle(this,new Vector2(1904,556), 150);

        //object respongsible for spawning the enemy units
        spawnBlockObject = new SpawnBlock(this,new Vector2(32, 492), 64,608);

        //this handles placing and buying towers
        towerMenu = new TowerMenu();

        towerIcon = new TowerIcon(this,towerMenu,new Vector2(960,900));


        //
        PlaceTowerBlock placeTowerBlock0 = new PlaceTowerBlock(new Vector2(496, 556), 15,401);
        PlaceTowerBlock placeTowerBlock1 = new PlaceTowerBlock(new Vector2(641, 348), 175,1);

        PlaceTowerBlock placeTowerBlock2 = new PlaceTowerBlock(new Vector2(992, 428), 96,450);
        PlaceTowerBlock placeTowerBlock3 = new PlaceTowerBlock(new Vector2(831, 605), 224,96);

        PlaceTowerBlock placeTowerBlock4 = new PlaceTowerBlock(new Vector2(1266, 556), 64,450);
        PlaceTowerBlock placeTowerBlock5 = new PlaceTowerBlock(new Vector2(1411, 348), 224,32);


        //EnemyUnit unit = new EnemyUnit(this,new Vector2(32, 492),10,26,100,5,0.25,"route0");
        */

        //2 test classes
        //CircleCollisionTestObject circleCollisionTestObject1 = new CircleCollisionTestObject(this,new Vector2(600,500), 100, true);
        BoxCollisionTestObject boxCollisionTestObject0 = new BoxCollisionTestObject(this, new Vector2(1000, CANVAS_HEIGHT - 1250), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject1 = new BoxCollisionTestObject(this, new Vector2(300, CANVAS_HEIGHT - 950), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject2 = new BoxCollisionTestObject(this, new Vector2(800, CANVAS_HEIGHT - 650), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject3 = new BoxCollisionTestObject(this, new Vector2(600, CANVAS_HEIGHT - 350), 500, 40, false);
        BoxCollisionTestObject floor = new BoxCollisionTestObject(this, new Vector2(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 70), 999999999, 40, false);
        //BoxCollisionTestObject wallLeft = new BoxCollisionTestObject(this,new Vector2(0,CANVAS_HEIGHT/2), 50,CANVAS_HEIGHT, false);
        //BoxCollisionTestObject wallRight = new BoxCollisionTestObject(this,new Vector2(CANVAS_WIDTH,CANVAS_HEIGHT/2), 50,CANVAS_HEIGHT, false);

        CharacterCube testObj = new CharacterCube(this, new Vector2(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 250), 100, 100);
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

    public DebugDrawer getDebugDrawer() {
        return debugDrawer;
    }

    public GridManager getGridManager() {
        return gridManager;
    }

    public void setGridManager(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    public CollisionSystem getCollisionSystem() {
        return collisionSystem;
    }

    public void setCollisionSystem(CollisionSystem collisionSystem) {
        this.collisionSystem = collisionSystem;
    }

    public TargetCircle getTargetCircleObject() {
        return targetCircleObject;
    }

    public void setTargetCircleObject(TargetCircle targetCircleObject) {
        this.targetCircleObject = targetCircleObject;
    }

    public SpawnBlock getSpawnBlockObject() {
        return spawnBlockObject;
    }

    public void setSpawnBlockObject(SpawnBlock spawnBlockObject) {
        this.spawnBlockObject = spawnBlockObject;
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
