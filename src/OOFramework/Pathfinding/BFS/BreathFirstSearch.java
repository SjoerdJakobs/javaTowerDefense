package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;

public class BreathFirstSearch
{
    private BFSTile[][] tileMap;

    private boolean shouldSwap = true;

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

    public ArrayList<BFSTile> nextList;
    public void Addroute(int x, int y, String route)
    {
        if(IsInGrid(x,y))
        {
            nextList = new ArrayList<BFSTile>();

            tileMap[x][y].isDestination = true;
            tileMap[x][y].routes.put(route, new Vector2(0,0));
            tileMap[x][y].hasBeenSet = true;
            tileMap[x][y].tilesFromTarget = 0;

            CheckNonDiagonalNeighbours(x,y,route,0);

            while (!nextList.isEmpty())
            {
                BFSTile checkTile = nextList.get(0);
                for (BFSTile tile : nextList)
                {
                    if (tile.tilesFromTarget <= checkTile.tilesFromTarget)
                    {
                        checkTile = tile;
                    }
                }
                nextList.remove(checkTile);
                CheckNonDiagonalNeighbours((int)checkTile.gridPos.x,(int)checkTile.gridPos.y,route,checkTile.tilesFromTarget);
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

    int start = 0;
    //check all nondiagonal tiles to see if they have been set and if not if they are able to be set
    private void CheckNonDiagonalNeighbours(int x, int y, String route, int distance)
    {
        for (int i = 0; i < 4; i++) {
            //System.out.print((start + i)%4 );
            if((start + i)%4 == 0)
            {
                if (IsInGrid(x + 1, y)) {
                    final BFSTile checkTile = tileMap[x + 1][y];
                    if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                        checkTile.routes.put(route, new Vector2(-1, 0));
                        checkTile.hasBeenSet = true;
                        checkTile.tilesFromTarget = distance+1;
                        nextList.add(checkTile);
                    }
                }
            }else if((start + i)%4 == 2) {
                if (IsInGrid(x, y + 1)) {
                    final BFSTile checkTile = tileMap[x][y + 1];
                    if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                        checkTile.routes.put(route, new Vector2(0, -1));
                        checkTile.hasBeenSet = true;
                        checkTile.tilesFromTarget = distance+1;
                        nextList.add(checkTile);
                    }
                }
            }else if((start + i)%4 == 3) {
                if (IsInGrid(x, y - 1)) {
                    final BFSTile checkTile = tileMap[x][y - 1];
                    if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                        checkTile.routes.put(route, new Vector2(0, 1));
                        checkTile.hasBeenSet = true;
                        checkTile.tilesFromTarget = distance+1;
                        nextList.add(checkTile);
                    }
                }
            } else if((start + i)%4 == 1) {
                if (IsInGrid(x - 1, y)) {
                    final BFSTile checkTile = tileMap[x - 1][y];
                    if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                        checkTile.routes.put(route, new Vector2(1, 0));
                        checkTile.hasBeenSet = true;
                        checkTile.tilesFromTarget = distance+1;
                        nextList.add(checkTile);
                    }
                }
            }
        }
        //System.out.println(" ");
        start+=1;
        if(start == 4)
        {
            start= 0;
        }
    }

    public BFSTile[][] getTileMap() {
        return tileMap;
    }
}
