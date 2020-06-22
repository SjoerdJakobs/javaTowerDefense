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
