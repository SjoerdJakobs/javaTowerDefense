package OOFramework.Pathfinding.Astar;

import OOFramework.Maths.Vector2;

public class AStarNode
{
    public Vector2     GridPosition;
    public AStarNode   ParentNode;
    public boolean     HasBeenChecked;
    public boolean     NodeIsTraversable;
    public boolean     NodeIsInApath;
    public double      CustomAddedCost;
    public double      CostToDestination;
    public double      CostToBegin;
    public double      TotalCost;

    public AStarNode(double xPos, double yPos)
    {
        this(new Vector2(xPos, yPos), true,0.0);
    }

    public AStarNode(Vector2 vec2)
    {
        this(vec2, true,0.0);
    }

    public AStarNode(double xPos, double yPos, boolean nodeIsTraversable, double customAddedCost)
    {
        this(new Vector2(xPos, yPos),nodeIsTraversable,customAddedCost);
    }

    public AStarNode(Vector2 vec2, boolean nodeIsTraversable, double customAddedCost)
    {
        this.GridPosition       = vec2;
        this.ParentNode         = null;
        this.HasBeenChecked     = false;
        this.NodeIsTraversable  = nodeIsTraversable;
        this.NodeIsInApath      = false;
        this.CustomAddedCost    = customAddedCost;
        this.CostToDestination  = 0.0;
        this.CostToBegin        = 0.0;
        this.TotalCost          = 0.0;
    }
}
