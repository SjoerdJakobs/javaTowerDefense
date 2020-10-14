package OOFramework.Pathfinding.BFS;

import OOFramework.Maths.Vector2;

import java.util.ArrayList;

public class BreathFirstSearch {
    public ArrayList<BFSTile> nextList;
    private BFSTile[][] tileMap;
    private int xSize;
    private int ySize;

    public void CreateTileGrid(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        tileMap = new BFSTile[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                tileMap[i][j] = new BFSTile(new Vector2(i, j), false);
                tileMap[i][j].tilesFromTarget = -2;
            }
        }
    }

    public void SetWall(int x, int y) {
        if (IsInGrid(x, y)) {
            tileMap[x][y].isWall = true;
        }
    }

    public void Addroute(int x, int y, String route) {
        if (IsInGrid(x, y)) {
            nextList = new ArrayList<BFSTile>();

            tileMap[x][y].isDestination = true;
            tileMap[x][y].routes.put(route, new Vector2(0, 0));
            tileMap[x][y].hasBeenSet = true;
            tileMap[x][y].tilesFromTarget = 0;

            CheckNonDiagonalNeighbours(x, y, route, 0);

            while (!nextList.isEmpty()) {
                BFSTile checkTile = nextList.get(0);
                //for (BFSTile tile : nextList) {
                    //if (tile.tilesFromTarget <= checkTile.tilesFromTarget) {
                        //checkTile = tile;
                    //}
                //}
                nextList.remove(checkTile);
                CheckNonDiagonalNeighbours((int) checkTile.gridPos.x, (int) checkTile.gridPos.y, route, checkTile.tilesFromTarget);
            }

            tileMap[x][y].isDestination = false;

            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    tileMap[i][j].hasBeenSet = false;
                }
            }
        }
    }

    private boolean IsInGrid(int x, int y) {
        return x < xSize && x > -1 && y < ySize && y > -1;
    }

    //check all nondiagonal tiles to see if they have been set and if not if they are able to be set
    private void CheckNonDiagonalNeighbours(int x, int y, String route, int distance) {
        if ((x + y) % 2 == 0)
        {
            checkDownNeighbour (x,  y, route, distance);//S
            checkUpNeighbour   (x,  y, route, distance);//N
            checkLeftNeighbour (x,  y, route, distance);//W
            checkRightNeighbour(x,  y, route, distance);//E
        }
        else
        {
            checkLeftNeighbour (x,  y, route, distance);//E
            checkRightNeighbour(x,  y, route, distance);//W
            checkUpNeighbour   (x,  y, route, distance);//N
            checkDownNeighbour (x,  y, route, distance);//S
        }

    }

    private void checkRightNeighbour(int x, int y, String route, int distance)
    {
        if (IsInGrid(x + 1, y)) {
            final BFSTile checkTile = tileMap[x + 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                checkTile.routes.put(route, new Vector2(-1, 0));
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
    }

    private void checkLeftNeighbour(int x, int y, String route, int distance)
    {
        if (IsInGrid(x - 1, y)) {
            final BFSTile checkTile = tileMap[x - 1][y];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                checkTile.routes.put(route, new Vector2(1, 0));
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
    }

    private void checkUpNeighbour(int x, int y, String route, int distance)
    {
        if (IsInGrid(x, y + 1)) {
            final BFSTile checkTile = tileMap[x][y + 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                checkTile.routes.put(route, new Vector2(0, -1));
                checkTile.hasBeenSet = true;
                checkTile.tilesFromTarget = distance + 1;
                nextList.add(checkTile);
            }
        }
    }

    private void checkDownNeighbour(int x, int y, String route, int distance)
    {
        if (IsInGrid(x, y - 1)) {
            final BFSTile checkTile = tileMap[x][y - 1];
            if (!checkTile.isWall && !checkTile.hasBeenSet && !checkTile.isDestination) {
                checkTile.routes.put(route, new Vector2(0, 1));
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
