package MainPackage.TowerDefense;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.Maths.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import static OOFramework.Modules.CONSTANTS.STANDARD_IMAGE_FILENAME_PREFIX;

public class TDTileData {
    public Vector2 gridPos;
    public Vector2 mapPos;
    private BoxCollider collider;
    private double width;
    private double height;

    public boolean hasTower;
    public boolean canPlaceTower;
    public boolean hasPathObstacle;
    public boolean hasPlayerObstacle;

    public boolean isRoad;
    public boolean isRiver;
    public boolean isWall;

    public double movementVariable;

    public boolean isBackGround;
    public boolean shouldDraw;

    public Image tileArt;

    public TDTileData(Vector2 gridPos, TileArtEnum tileArtType) {
        this.gridPos = gridPos;
        this.hasTower = false;
        this.canPlaceTower = false;
        this.hasPathObstacle = false;
        this.hasPlayerObstacle = false;
        this.isRoad = false;
        this.isWall = false;
        this.isRiver = false;
        this.movementVariable = 1;
        this.isBackGround = true;
        this.shouldDraw = true;

        SetImage(tileArtType);
    }

    public void SetImage(TileArtEnum tileArtType) {
        // Read from an input stream
        InputStream is;

        switch (tileArtType) {
            case ROAD:
                is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX+"tile014.png");
                break;
            case WATER:
                is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX+"tile028.png");
                break;
            case GROUND:
                is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX+"tile001.png");
                break;
            default:
                is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX+"Default.png");
                break;
        }
        try {
            tileArt = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
