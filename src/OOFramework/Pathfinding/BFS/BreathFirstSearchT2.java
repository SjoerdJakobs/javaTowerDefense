package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;

import java.util.*;

public class BreathFirstSearchT2
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

    public LinkedList<BFSTile> frontier;
    public HashSet<BFSTile> came_from;
    Comparator<BFSTile> intSorter = Comparator.comparing(BFSTile::getTilesFromTarget);

    public void Addroute(int x, int y, String route)
    {
        if(IsInGrid(x,y))
        {
            frontier = new LinkedList<>();

            tileMap[x][y].isDestination = true;
            tileMap[x][y].routes.put(route, new Vector2(0,0));
            tileMap[x][y].hasBeenSet = true;
            tileMap[x][y].tilesFromTarget = 0;

            frontier.add(tileMap[x][y]);

            came_from = new HashSet<BFSTile>();
            came_from.add(tileMap[x][y]);

            while (!frontier.isEmpty())
            {
                BFSTile checkTile = frontier.poll();
                CheckNonDiagonalNeighbours((int)checkTile.gridPos.x,(int)checkTile.gridPos.y,route,checkTile.tilesFromTarget);
                CheckDiagonalNeighbours((int)checkTile.gridPos.x,(int)checkTile.gridPos.y,route,checkTile.tilesFromTarget);
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
        if (IsInGrid(x + 1, y)) {
            final BFSTile checkTile = tileMap[x + 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(-1, 0));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheRight = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheRight = true;
        }
        if (IsInGrid(x, y + 1)) {
            final BFSTile checkTile = tileMap[x][y + 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(0, -1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalBelow = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalBelow = true;
        }
        if (IsInGrid(x - 1, y)) {
            final BFSTile checkTile = tileMap[x - 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(1, 0));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheLeft = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheLeft = true;
        }
        if (IsInGrid(x, y - 1)) {
            final BFSTile checkTile = tileMap[x][y - 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(0, 1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }

            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalAbove = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalAbove = true;
        }
    }

    private void CheckDiagonalNeighbours(int x, int y, String route, int distance)
    {
        if (IsInGrid(x + 1, y - 1)) {
            final BFSTile checkTile = tileMap[x + 1][y-1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(-1, 1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheTopRight = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheTopRight = true;
        }
        if (IsInGrid(x + 1, y + 1)) {
            final BFSTile checkTile = tileMap[x+1][y + 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(-1, -1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheBottomRight = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheBottomRight = true;
        }
        if (IsInGrid(x - 1, y - 1)) {
            final BFSTile checkTile = tileMap[x - 1][y - 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(1, 1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }
            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheTopLeft = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheTopLeft = true;
        }
        if (IsInGrid(x - 1, y + 1)) {
            final BFSTile checkTile = tileMap[x-1][y + 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet &&!checkTile.isDestination) {
                if (!came_from.contains(checkTile)) {
                    checkTile.routes.put(route, new Vector2(1, -1));
                    checkTile.hasBeenSet = true;
                    checkTile.tilesFromTarget = distance + 1;
                    frontier.add(checkTile);
                    came_from.add(checkTile);
                }
            }

            else if(checkTile.isWall)
            {
                tileMap[x][y].hasWalToTheBottomLeft = true;
            }
        }
        else
        {
            tileMap[x][y].hasWalToTheBottomLeft = true;
        }
    }

    public BFSTile[][] getTileMap() {
        return tileMap;
    }
}
