package MainPackage.TowerDefense;

import OOFramework.Maths.Vector2;
import javafx.scene.image.Image;

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

    private Image tileArt;

    public TDTileData(Vector2 gridPos)
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
    }
}
