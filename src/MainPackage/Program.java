package MainPackage;

import MainPackage.TowerDefense.GridManager;
import MainPackage.TowerDefense.Menu.TowerIcon;
import MainPackage.TowerDefense.Menu.TowerMenu;
import MainPackage.TowerDefense.TargetCircle;
import MainPackage.TowerDefense.TowerDefenseDebug;
import MainPackage.TowerDefense.Units.SpawnBlock;
import OOFramework.Collision2D.CollisionSystem;
import OOFramework.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.InputHandling.KeyboardInput;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.Sound.SoundPlayer;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Program extends FrameworkProgram {
    private static Program programInstance = null;
    private MouseInput mouseInput;
    private KeyboardInput keyboardInput;
    private TowerDefenseDebug towerDefenseDebug;
    private DebugDrawer debugDrawer;
    private GridManager gridManager;
    private CollisionSystem collisionSystem;
    private TargetCircle targetCircleObject;
    private SpawnBlock spawnBlockObject;
    private TowerMenu towerMenu;
    private TowerIcon towerIcon;

    public static Program getProgramInstance() {
        return programInstance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        programInstance = this;
        SoundPlayer.Loop("bensound-evolution.wav", 0.7f);

        //used by other classes to get mouse input
        mouseInput = MouseInput.getInstance();
        //used by other classes to get keyboard input
        keyboardInput = KeyboardInput.getInstance();
        //used by other classes for drawing their debugging;
        debugDrawer = DebugDrawer.getInstance();
        //used by other classes for drawing their debugging but only the project specific things
        towerDefenseDebug = TowerDefenseDebug.getInstance();
        //this manages all the collisions
        collisionSystem = CollisionSystem.getInstance();

        //creates the map and movement grid
        gridManager = new GridManager();

        //the target which the units will have to kill, also has the health bar in it
        targetCircleObject = new TargetCircle(new Vector2(1904, 556), 150);

        //object respongsible for spawning the enemy units
        spawnBlockObject = SpawnBlock.getInstance();

        //this handles placing and buying towers
        towerMenu = new TowerMenu();

        towerIcon = new TowerIcon(towerMenu, new Vector2(960, 900));


        //EnemyUnit unit = new EnemyUnit(this,new Vector2(32, 492),10,26,100,5,0.25,"route0");


        /*
        //2 test classes
        //CircleCollisionTestObject circleCollisionTestObject1 = new CircleCollisionTestObject(this,new Vector2(600,500), 100, true);
        BoxCollisionTestObject boxCollisionTestObject0 = new BoxCollisionTestObject(this, new Vector2(1000, CANVAS_HEIGHT - 1250), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject1 = new BoxCollisionTestObject(this, new Vector2(300, CANVAS_HEIGHT - 950), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject2 = new BoxCollisionTestObject(this, new Vector2(800, CANVAS_HEIGHT - 650), 500, 40, false);
        BoxCollisionTestObject boxCollisionTestObject3 = new BoxCollisionTestObject(this, new Vector2(600, CANVAS_HEIGHT - 350), 500, 40, false);
        BoxCollisionTestObject floor = new BoxCollisionTestObject(this, new Vector2(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 70), 999999999, 40, false);
        //BoxCollisionTestObject wallLeft = new BoxCollisionTestObject(this,new Vector2(0,CANVAS_HEIGHT/2), 50,CANVAS_HEIGHT, false);
        //BoxCollisionTestObject wallRight = new BoxCollisionTestObject(this,new Vector2(CANVAS_WIDTH,CANVAS_HEIGHT/2), 50,CANVAS_HEIGHT, false);

        CharacterCube testObj = new CharacterCube(new Vector2(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 250), 100, 100);
        //BoxCollisionTestObject boxCollisionTestObject2 = new BoxCollisionTestObject(this,new Vector2(600,500), 100,100, true);
        */
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

    public GridManager getGridManager() {
        return gridManager;
    }

    public TargetCircle getTargetCircleObject() {
        return targetCircleObject;
    }

    public SpawnBlock getSpawnBlockObject() {
        return spawnBlockObject;
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
