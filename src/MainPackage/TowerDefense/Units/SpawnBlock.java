package MainPackage.TowerDefense.Units;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.InputHandling.MouseInput;
import OOFramework.Maths.Vector2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SpawnBlock extends StandardObject {
    private static SpawnBlock INSTANCE = null;
    private BoxCollider collider;
    private final Vector2 pos;
    private final Vector2 posFromCorner;
    private final double width;
    private final double height;
    private boolean atMouse;
    private final Rectangle rectangle;
    private final ArrayList<EnemyUnit> spawnedUnits;
    private double spawnTimer = 0;
    private final double spawnDelay = 0.1;
    private double spawncounter = 500;

    private SpawnBlock(Vector2 pos, double width, double height) {
        super(false, true, true, true, 2000, 1000);
        this.pos = pos;
        this.posFromCorner = new Vector2(this.pos.x - width * 0.5, this.pos.y - height * 0.5);
        this.width = width;
        this.height = height;

        //this is all the collision code you need to set it up
        //this.collider = new BoxCollider(pos,width,height);  //
        //this.collider.collisionCallback = this::OnCollision;//
        //this.collider.setColliderTag(ColliderTag.SPAWN_BOX);//
        //////////////////////////////////////////////////////

        this.rectangle = new Rectangle((int) pos.x, (int) pos.y, (int) this.width, (int) this.height, 0);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        rectangle.setRectangleColor(Color.green);

        spawnedUnits = new ArrayList<>();

        //SpawnUnit();
    }

    public static SpawnBlock getInstance() {
        if (INSTANCE == null) {
            synchronized (MouseInput.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SpawnBlock(new Vector2(32, 492), 64, 608);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        spawnTimer += deltaTime;
        if (spawnTimer >= spawnDelay) {
            if (spawncounter > 0) {
                SpawnUnit();
                spawncounter -= 1;
            }
            spawnTimer -= spawnDelay;
        }
    }

    private void SpawnUnit() {
        double newX = (this.posFromCorner.x + 15) + ((Math.random() * (width - 15)));
        double newY = (this.posFromCorner.y + 15) + ((Math.random() * (height - 20)));
        spawnedUnits.add(new EnemyUnit(new Vector2(newX, newY), 100, 40, 100, 5, 0.30, "route0"));
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        rectangle.FilledDraw(frameworkProgram.getGraphics2D());
    }

    public void OnCollision(Collider2D other) {
        //System.out.println("colliding box");
    }

    public ArrayList<EnemyUnit> getSpawnedUnits() {
        return spawnedUnits;
    }
}
