package MainPackage.TowerDefense;

import MainPackage.Program;
import OOFramework.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BreathFirstSearchT2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GridManager extends StandardObject {
    private Program program;
    private DebugDrawer debugDrawer;
    private BufferedImage mapImage;
    private Graphics2D graphics2D;

    private BreathFirstSearchT2 BFS;

    private TDTileData mapData[][];

    public GridManager(FrameworkProgram frameworkProgram, Program program) {
        super(false, true, true, true, 10, 100);
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.BFS = new BreathFirstSearchT2();
        LoadMap();
    }

    public void LoadMap() {
        /**
         * thiss will be replaced by loading a file from a map editor, i could use TILE but i want to make one myself
         */
        mapData = new TDTileData[60][34];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 34; j++) {
                if (j < 5 || j > 25) {
                    mapData[i][j] = new TDTileData(new Vector2(i, j), TileArtEnum.ROAD);
                    mapData[i][j].isBackGround = true;
                } else if (j == 5 || j == 25) {
                    mapData[i][j] = new TDTileData(new Vector2(i, j), TileArtEnum.WATER);
                    mapData[i][j].isRiver= true;
                } else {
                    mapData[i][j] = new TDTileData(new Vector2(i, j), TileArtEnum.GROUND);
                    mapData[i][j].isRoad = true;
                }
                mapData[i][j].mapPos = new Vector2((i * 32), (-4 + j * 32));
            }
        }
        //mirrored L
        for (int i = 0; i < 15; i++) {
            mapData[33][6 + i].SetImage(TileArtEnum.WATER);
            mapData[33][6 + i].isRiver= true;
        }
        for (int i = 0; i < 10; i++) {
            mapData[28][6 + i].SetImage(TileArtEnum.WATER);
            mapData[28][6 + i].isRiver= true;
        }
        for (int i = 0; i < 5; i++) {
            mapData[21][16 + i].SetImage(TileArtEnum.WATER);
            mapData[21][16 + i].isRiver= true;
        }
        for (int i = 0; i < 8; i++) {
            mapData[21 + i][16].SetImage(TileArtEnum.WATER);
            mapData[21 + i][16].isRiver= true;
        }
        for (int i = 0; i < 13; i++) {
            mapData[21 + i][21].SetImage(TileArtEnum.WATER);
            mapData[21 + i][21].isRiver= true;
        }
        SetPlaceableTiles(mapData,mapData[23][17]);
        ///////////////

        //upsideDown L1
        for (int i = 0; i < 12; i++) {
            mapData[17][13 + i].SetImage(TileArtEnum.WATER);
            mapData[17][13 + i].isRiver= true;
        }
        for (int i = 0; i < 15; i++) {
            mapData[13][10 + i].SetImage(TileArtEnum.WATER);
            mapData[13][10 + i].isRiver= true;
        }
        for (int i = 0; i < 3; i++) {
            mapData[24][10 + i].SetImage(TileArtEnum.WATER);
            mapData[24][10 + i].isRiver= true;
        }
        for (int i = 0; i < 8; i++) {
            mapData[17 + i][12].SetImage(TileArtEnum.WATER);
            mapData[17 + i][12].isRiver= true;
        }
        for (int i = 0; i < 12; i++) {
            mapData[13 + i][9].SetImage(TileArtEnum.WATER);
            mapData[13 + i][9].isRiver= true;
        }
        SetPlaceableTiles(mapData,mapData[16][12]);
        ///////////////

        //upsideDown L2
        for (int i = 0; i < 12; i++) {
            mapData[41][13 + i].SetImage(TileArtEnum.WATER);
            mapData[41][13 + i].isRiver= true;
        }
        for (int i = 0; i < 15; i++) {
            mapData[37][10 + i].SetImage(TileArtEnum.WATER);
            mapData[37][10 + i].isRiver= true;
        }
        for (int i = 0; i < 3; i++) {
            mapData[48][10 + i].SetImage(TileArtEnum.WATER);
            mapData[48][10 + i].isRiver= true;
        }
        for (int i = 0; i < 8; i++) {
            mapData[41 + i][12].SetImage(TileArtEnum.WATER);
            mapData[41 + i][12].isRiver= true;
        }
        for (int i = 0; i < 12; i++) {
            mapData[37 + i][9].SetImage(TileArtEnum.WATER);
            mapData[37 + i][9].isRiver= true;
        }
        SetPlaceableTiles(mapData,mapData[40][10]);
        ///////////////

        for (int i = 0; i < 15; i++) {
            mapData[59 - i][7 + i].SetImage(TileArtEnum.WATER);
            mapData[59 - i][7 + i].isRiver= true;
        }
        for (int i = 0; i < 14; i++) {
            mapData[59 - i][8 + i].SetImage(TileArtEnum.WATER);
            mapData[59 - i][8 + i].isRiver= true;
        }

        TowerDefenseDebug.StartDebugTD(mapData);

        /**
         * //////////////////////////////////////////////////////////////////////////////////////////////////////////
         */

        mapImage = new BufferedImage(1920, 1088, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics imageGraphics = mapImage.getGraphics();
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 34; j++) {
                imageGraphics.drawImage(mapData[i][j].tileArt, (i * 32), (-4 + j * 32), null);
            }
        }

        BFS.CreateTileGrid(60, 34);
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 34; j++) {
                if (j == 5) {
                    BFS.SetWall(i, j);
                } else if (j == 25) {
                    BFS.SetWall(i, j);
                }
            }
        }

        //mirrored L
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(33, 6 + i);
        }
        for (int i = 0; i < 10; i++) {
            BFS.SetWall(28, 6 + i);
        }
        for (int i = 0; i < 5; i++) {
            BFS.SetWall(21, 16 + i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(21 + i, 16);
        }
        for (int i = 0; i < 13; i++) {
            BFS.SetWall(21 + i, 21);
        }
        ///////////////

        //upsideDown L1
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(17, 13 + i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(13, 10 + i);
        }
        for (int i = 0; i < 3; i++) {
            BFS.SetWall(24, 10 + i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(17 + i, 12);
        }
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(13 + i, 9);
        }
        ///////////////

        //upsideDown L2
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(41, 13 + i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(37, 10 + i);
        }
        for (int i = 0; i < 3; i++) {
            BFS.SetWall(48, 10 + i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(41 + i, 12);
        }
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(37 + i, 9);
        }
        ///////////////

        for (int i = 0; i < 16; i++) {
            BFS.SetWall(60 - i, 6 + i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(60 - i, 7 + i);
        }

        BFS.Addroute(59, 17, "route0");

        DebugDrawer.StartDebugBFS(BFS.getTileMap(), "route0");
    }

    private void SetPlaceableTiles(TDTileData tdTileDatas[][], TDTileData tdTileData ) {
        Queue<TDTileData> open = new LinkedList<>();
        ArrayList<TDTileData> closed = new ArrayList<>();

        open.add(tdTileData);
        TDTileData checkTDT;
        TDTileData neighbourTDT;
        while (!open.isEmpty()) {
            checkTDT = open.poll();
            closed.add(checkTDT);
            checkTDT.canPlaceTower = true;

            //down
            neighbourTDT = tdTileDatas[(int) checkTDT.gridPos.x][(int) checkTDT.gridPos.y + 1];
            if (!neighbourTDT.isRiver && !open.contains(neighbourTDT) && !closed.contains(neighbourTDT)) {
                neighbourTDT.canPlaceTower = true;
                open.add(neighbourTDT);
            }

            //up
            neighbourTDT = tdTileDatas[(int) checkTDT.gridPos.x][(int) checkTDT.gridPos.y - 1];
            if (!neighbourTDT.isRiver && !open.contains(neighbourTDT) && !closed.contains(neighbourTDT)) {
                neighbourTDT.canPlaceTower = true;
                open.add(neighbourTDT);
            }

            //left
            neighbourTDT = tdTileDatas[(int) checkTDT.gridPos.x - 1][(int) checkTDT.gridPos.y];
            if (!neighbourTDT.isRiver && !open.contains(neighbourTDT) && !closed.contains(neighbourTDT)) {
                neighbourTDT.canPlaceTower = true;
                open.add(neighbourTDT);
            }

            //right
            neighbourTDT = tdTileDatas[(int) checkTDT.gridPos.x + 1][(int) checkTDT.gridPos.y];
            if (!neighbourTDT.isRiver && !open.contains(neighbourTDT) && !closed.contains(neighbourTDT)) {
                neighbourTDT.canPlaceTower = true;
                open.add(neighbourTDT);
            }
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        graphics2D.drawImage(mapImage, 0, 0, null);
    }

    public BreathFirstSearchT2 getBFS() {
        return BFS;
    }

    public TDTileData[][] getMapData() {
        return mapData;
    }
}
