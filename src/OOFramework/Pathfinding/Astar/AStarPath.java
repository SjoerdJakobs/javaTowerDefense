package OOFramework.Pathfinding.Astar;

import java.util.ArrayList;

public class AStarPath
{

    public AStarPath(boolean ignoreDanger)
    {
        this(0,ignoreDanger);
    }

    public AStarPath(int pathOwner)
    {
        this(pathOwner,true);
    }

    public AStarPath(int pathOwner, boolean ignoreDanger)
    {
        this.PathOwner = pathOwner;
        this.IgnoreDanger = ignoreDanger;

        this.ThisPathIsStillViable = true;
        this.NodesInThisPath = new ArrayList<AStarNode>();
    }

    public ArrayList<AStarNode> NodesInThisPath;
    public int PathOwner;

    public boolean IgnoreDanger;
    public boolean ThisPathIsStillViable;
}
