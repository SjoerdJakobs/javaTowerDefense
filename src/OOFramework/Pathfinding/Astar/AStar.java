package OOFramework.Pathfinding.Astar;

import OOFramework.Maths.Vector2;

public class AStar
{
    public boolean AStarGridHasBeenSetUp = false;

    public AStarNode[][] AStarGrid;
    //boolean GetPath(AstarPath * a_pathForUse,uint8_t a_owner, glm::vec2 a_startNode, glm::vec2 a_destinationNode, bool a_ignoreDanger = false);

    public void SetUpAstarGrid(int xSize, int ySize)
    {
        for (int i = 0; i < xSize; i++)
        {
            for(int j = 0; j < ySize; j++)
            {
                AStarGrid[i][j] = new AStarNode(i,j);
            }
        }
        AStarGridHasBeenSetUp = true;
    }

    public Boolean GetPath(AStarPath  aPathForUse,int owner, Vector2 startNode, Vector2 destinationNode, boolean ignoreDanger)
    {
        return true;
    }


    void CalculateNodeCosts()
    {

    }

    void CalculateNodeCostsToBegin()
    {

    }

    void CalculateNodeCostsToDestination()
    {

    }

    void CalculateNeighbours()
    {

    }

    void SetNodeParent()
    {

    }
}
