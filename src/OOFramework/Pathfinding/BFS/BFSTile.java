package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;
import java.util.HashMap;

public class BFSTile {
    public Vector2 gridPos;
    public Vector2 mapPos;
    public HashMap<String, Vector2> routes;

    public boolean isWall;
    public boolean hasBeenSet;
    public boolean isDestination;

    public BFSTile(Vector2 gridPos, boolean isWall)
    {
        routes = new HashMap<String, Vector2>();
        this.gridPos = gridPos;
        this.isWall = isWall;
    }
}
