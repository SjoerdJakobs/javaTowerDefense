package MainPackage.TowerDefense;

import OOFramework.Maths.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TDTileData
{
    public Vector2 gridPos;
    public Vector2 mapPos;

    public boolean hasTower;
    public boolean canPlaceTower;
    public boolean hasPathObstacle;
    public boolean hasPlayerObstacle;

    public boolean isRoad;
    public boolean isRiver;

    public double movementVariable;

    public boolean isBackGround;
    public boolean shouldDraw;

    public Image tileArt;

    public TDTileData(Vector2 gridPos, TileArtEnum tileArtType)
    {
        this.gridPos            = gridPos;
        this.hasTower           = false;
        this.canPlaceTower      = false;
        this.hasPathObstacle    = false;
        this.hasPlayerObstacle  = false;
        this.isRoad             = false;
        this.isRiver            = false;
        this.movementVariable   = 1;
        this.isBackGround       = true;
        this.shouldDraw         = true;

        SetImage(tileArtType);
    }

    public void SetImage(TileArtEnum tileArtType)
    {
        File pathToFile;
        switch (tileArtType)
        {
            case ROAD:
                pathToFile = new File("assets/tile014.png");
                break;
            case WATER:
                pathToFile = new File("assets/tile028.png");
                break;
            case GROUND:
                pathToFile = new File("assets/tile001.png");
                break;
            default:
                pathToFile = new File("assets/Default.png");
                break;
        }
        try {
            tileArt = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
