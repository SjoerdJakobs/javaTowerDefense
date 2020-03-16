package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;
import java.util.LinkedList;
import java.util.Queue;

public class BreathFirstSearch
{
    private BFSTile[][] tileMap;

    private int xSize;
    private int ySize;

    public void CreateTileGrid(int xSize, int ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        tileMap = new BFSTile[xSize][ySize];
        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                tileMap[i][j] = new BFSTile(new Vector2(i,j),false);
            }
        }
    }

    public void SetWall(int x, int y)
    {
        if (IsInGrid(x, y))
        {
            tileMap[x][y].isWall = true;
        }
    }

    public Queue<BFSTile> nextList;
    public void Addroute(int x, int y, String route)
    {
        if(IsInGrid(x,y))
        {
            nextList = new LinkedList<BFSTile>();

            tileMap[x][y].isDestination = true;
            tileMap[x][y].routes.put(route, new Vector2(0,0));
            tileMap[x][y].hasBeenSet = true;

            CheckNonDiagonalNeighbours(x,y,route);

            while (!nextList.isEmpty())
            {
                final BFSTile checkTile = nextList.poll();
                CheckNonDiagonalNeighbours((int)checkTile.gridPos.x,(int)checkTile.gridPos.y,route);
            }

            tileMap[x][y].isDestination = false;

            for (int i = 0; i < xSize; i++)
            {
                for (int j = 0; j < ySize; j++)
                {
                    tileMap[i][j].hasBeenSet = false;
                }
            }
        }
    }

    private boolean IsInGrid(int x, int y)
    {
        if(x < xSize && x > -1 && y < ySize && y > -1 ) {
            return true;
        }
        else {
            return false;
        }
    }

    //check all nondiagonal tiles to see if they have been set and if not if they are able to be set
    private void CheckNonDiagonalNeighbours(int x, int y, String route)
    {
        if(IsInGrid(x,y+1))
        {
            final BFSTile checkTile = tileMap[x][y+1];
            if(!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination)
            {
                checkTile.routes.put(route, new Vector2(0,-1));
                checkTile.hasBeenSet = true;
                nextList.add(checkTile);
            }
        }

        if(IsInGrid(x+1,y))
        {
            final BFSTile checkTile = tileMap[x+1][y];
            if(!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination)
            {
                checkTile.routes.put(route, new Vector2(-1,0));
                checkTile.hasBeenSet = true;
                nextList.add(checkTile);
            }
        }

        if(IsInGrid(x,y-1))
        {
            final BFSTile checkTile = tileMap[x][y-1];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination)
            {
                checkTile.routes.put(route, new Vector2(0, 1));
                checkTile.hasBeenSet = true;
                nextList.add(checkTile);
            }
        }

        if(IsInGrid(x-1,y))
        {
            final BFSTile checkTile = tileMap[x - 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination)
            {
                checkTile.routes.put(route, new Vector2(1, 0));
                checkTile.hasBeenSet = true;
                nextList.add(checkTile);
            }
        }
    }

    public BFSTile[][] getTileMap() {
        return tileMap;
    }
}
