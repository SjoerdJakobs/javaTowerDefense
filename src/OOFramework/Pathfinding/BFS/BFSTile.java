package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;
import java.util.HashMap;

public class BFSTile {

    public Vector2 gridPos;
    public Vector2 mapPos;
    public int tilesFromTarget;
    public HashMap<String, Vector2> routes;
    public Vector2 goingTo;

    public boolean isWall;
    public boolean hasBeenSet;
    public boolean isDestination;

    public boolean hasWalToTheLeft;
    public boolean hasWalToTheTopLeft;
    public boolean hasWalToTheBottomLeft;

    public boolean hasWalToTheRight;
    public boolean hasWalToTheTopRight;
    public boolean hasWalToTheBottomRight;

    public boolean hasWalAbove;
    public boolean hasWalBelow;

    public BFSTile(Vector2 gridPos, boolean isWall)
    {
        routes = new HashMap<String, Vector2>();
        this.gridPos = gridPos;
        this.isWall = isWall;
        this.hasWalToTheLeft = false;
        this.hasWalToTheRight = false;
        this.hasWalAbove = false;
        this.hasWalBelow = false;
        this.goingTo = new Vector2();
    }

    public int getTilesFromTarget()
    {
        return tilesFromTarget;
    }
}
