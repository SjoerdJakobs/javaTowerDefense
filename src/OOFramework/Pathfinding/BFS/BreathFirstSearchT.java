package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;

import java.util.ArrayList;

public class BreathFirstSearchT
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

            CheckNonDiagonalNeighbours(x,y,route,0, tileMap[x][y].goingTo,true);

            while (!nextList.isEmpty())
            {
                BFSTile checkTile = nextList.get(0);
                for (BFSTile tile : nextList)
                {
                    if (tile.tilesFromTarget < checkTile.tilesFromTarget)
                    {
                        checkTile = tile;
                    }
                }
                nextList.remove(checkTile);
                CheckNonDiagonalNeighbours((int)checkTile.gridPos.x,(int)checkTile.gridPos.y,route,checkTile.tilesFromTarget,checkTile.goingTo, false);
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
    private void CheckNonDiagonalNeighbours(int x, int y, String route, int distance, Vector2 isGoingTo, boolean isStart)
    {
        if (IsInGrid(x + 1, y)) {
            final BFSTile checkTile = tileMap[x + 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                if(isStart)
                {
                    checkTile.routes.put(route, new Vector2(-1, 0));
                    checkTile.goingTo = new Vector2(-1, 0);
                }
                else
                {
                    if(IsInGrid(x + 1 + (int)isGoingTo.x, y + (int)isGoingTo.y))
                    {
                        final BFSTile checkTile2 = tileMap[x + 1 + (int)isGoingTo.x][y + (int)isGoingTo.y];
                        if(!checkTile2.isWall)
                        {
                            checkTile.routes.put(route, new Vector2(isGoingTo.x, isGoingTo.y));
                            checkTile.goingTo = new Vector2(isGoingTo.x, isGoingTo.y);
                        }
                        else
                        {
                            checkTile.routes.put(route, new Vector2(-1, 0));
                            checkTile.goingTo = new Vector2(-1, 0);
                        }
                    }
                }
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;

                nextList.add(checkTile);
            }
        }
        if (IsInGrid(x, y + 1)) {
            final BFSTile checkTile = tileMap[x][y + 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                if(isStart)
                {
                    checkTile.routes.put(route, new Vector2(0, -1));
                    checkTile.goingTo = new Vector2(0, -1);
                }
                else
                {
                    if(IsInGrid(x + (int)isGoingTo.x, y + 1 + (int)isGoingTo.y))
                    {
                        final BFSTile checkTile2 = tileMap[x + (int)isGoingTo.x][y + 1 +(int)isGoingTo.y];
                        if(!checkTile2.isWall)
                        {
                            checkTile.routes.put(route, new Vector2(isGoingTo.x, isGoingTo.y));
                            checkTile.goingTo = new Vector2(isGoingTo.x, isGoingTo.y);
                        }
                        else
                        {
                            checkTile.routes.put(route, new Vector2(0, -1));
                            checkTile.goingTo = new Vector2(0, -1);
                        }
                    }
                }
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
        if (IsInGrid(x, y - 1)) {
            final BFSTile checkTile = tileMap[x][y - 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                if(isStart)
                {
                    checkTile.routes.put(route, new Vector2(0, 1));
                    checkTile.goingTo = new Vector2(0, 1);
                }
                else
                {
                    if(IsInGrid(x + (int)isGoingTo.x, y - 1 + (int)isGoingTo.y))
                    {
                        final BFSTile checkTile2 = tileMap[x + (int)isGoingTo.x][y - 1 +(int)isGoingTo.y];
                        if(!checkTile2.isWall)
                        {
                            checkTile.routes.put(route, new Vector2(isGoingTo.x, isGoingTo.y));
                            checkTile.goingTo = new Vector2(isGoingTo.x, isGoingTo.y);
                        }
                        else
                        {
                            checkTile.routes.put(route, new Vector2(0, 1));
                            checkTile.goingTo = new Vector2(0, 1);
                        }
                    }
                }
                checkTile.routes.put(route, new Vector2(0, 1));
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
        if (IsInGrid(x - 1, y)) {
            final BFSTile checkTile = tileMap[x - 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                if(isStart)
                {
                    checkTile.routes.put(route, new Vector2(1, 0));
                    checkTile.goingTo = new Vector2(1, 0);
                }
                else
                {
                    if(IsInGrid(x - 1 + (int)isGoingTo.x, y + (int)isGoingTo.y))
                    {
                        final BFSTile checkTile2 = tileMap[x - 1 + (int)isGoingTo.x][y + (int)isGoingTo.y];
                        if(!checkTile2.isWall)
                        {
                            checkTile.routes.put(route, new Vector2(isGoingTo.x, isGoingTo.y));
                            checkTile.goingTo = new Vector2(isGoingTo.x, isGoingTo.y);
                        }
                        else
                        {
                            checkTile.routes.put(route, new Vector2(1, 0));
                            checkTile.goingTo = new Vector2(1, 0);
                        }
                    }
                }
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
    }

    public BFSTile[][] getTileMap() {
        return tileMap;
    }
}
