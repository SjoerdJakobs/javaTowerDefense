package MainPackage.TowerDefense;

import MainPackage.Program;
import OOFramework.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BreathFirstSearchT2;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GridManager extends StandardObject
{
    private Program program;
    private DebugDrawer debugDrawer;
    private BufferedImage mapImage;
    private Graphics2D graphics2D;

    private BreathFirstSearchT2 BFS;

    public GridManager(FrameworkProgram frameworkProgram, Program program)
    {
        super(frameworkProgram, false, true, true, true, 10, 100);
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.BFS = new BreathFirstSearchT2();
        LoadMap();
    }

    public void LoadMap()
    {
        /**
         * thiss will be replaced by loading a file from a map editor, i could use TILE but i want to make one myself
         */
        TDTileData mapData[][] = new TDTileData[60][34];
        for(int i = 0; i < 60; i++)
        {
            for(int j = 0; j < 34; j++)
            {
                if(j < 5 || j > 25)
                {
                    mapData[i][j] = new TDTileData(new Vector2(i,j), TileArtEnum.ROAD);
                }
                else if(j == 5 || j ==25)
                {
                    mapData[i][j] = new TDTileData(new Vector2(i,j), TileArtEnum.WATER);
                }
                else
                {
                    mapData[i][j] = new TDTileData(new Vector2(i,j), TileArtEnum.GROUND);
                }
            }
        }
        //mirrored L
        for (int i = 0; i < 15; i++) {
            mapData[33][6+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 10; i++) {
            mapData[28][6+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 5; i++) {
            mapData[21][16+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 8; i++) {
            mapData[21+i][16].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 13; i++) {
            mapData[21+i][21].SetImage(TileArtEnum.WATER);
        }
        ///////////////

        //upsideDown L1
        for (int i = 0; i < 12; i++) {
            mapData[17][13+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 15; i++) {
            mapData[13][10+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 3; i++) {
            mapData[24][10+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 8; i++) {
            mapData[17+i][12].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 12; i++) {
            mapData[13+i][9].SetImage(TileArtEnum.WATER);
        }
        ///////////////

        //upsideDown L2
        for (int i = 0; i < 12; i++) {
            mapData[41][13+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 15; i++) {
            mapData[37][10+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 3; i++) {
            mapData[48][10+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 8; i++) {
            mapData[41+i][12].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 12; i++) {
            mapData[37+i][9].SetImage(TileArtEnum.WATER);
        }
        ///////////////

        for (int i = 0; i < 15; i++) {
            mapData[59-i][7+i].SetImage(TileArtEnum.WATER);
        }
        for (int i = 0; i < 14; i++) {
            mapData[59-i][8+i].SetImage(TileArtEnum.WATER);
        }

        /**
         * //////////////////////////////////////////////////////////////////////////////////////////////////////////
         */

        mapImage = new BufferedImage(1920,1088, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics imageGraphics = mapImage.getGraphics();
        for(int i = 0; i < 60; i++)
        {
            for(int j = 0; j < 34; j++)
            {
                imageGraphics.drawImage(mapData[i][j].tileArt, (i * 32), (-4 + j * 32), null);
            }
        }

        BFS.CreateTileGrid(60,34);
        for(int i = 0; i < 60; i++)
        {
            for(int j = 0; j < 34; j++)
            {
                if(j == 5)
                {
                    BFS.SetWall(i,j);
                }
                else if(j == 25)
                {
                    BFS.SetWall(i,j);
                }
            }
        }

        //mirrored L
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(33,6+i);
        }
        for (int i = 0; i < 10; i++) {
            BFS.SetWall(28,6+i);
        }
        for (int i = 0; i < 5; i++) {
            BFS.SetWall(21,16+i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(21+i,16);
        }
        for (int i = 0; i < 13; i++) {
            BFS.SetWall(21+i,21);
        }
        ///////////////

        //upsideDown L1
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(17,13+i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(13,10+i);
        }
        for (int i = 0; i < 3; i++) {
            BFS.SetWall(24,10+i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(17+i,12);
        }
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(13+i,9);
        }
        ///////////////

        //upsideDown L2
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(41,13+i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(37,10+i);
        }
        for (int i = 0; i < 3; i++) {
            BFS.SetWall(48,10+i);
        }
        for (int i = 0; i < 8; i++) {
            BFS.SetWall(41+i,12);
        }
        for (int i = 0; i < 12; i++) {
            BFS.SetWall(37+i,9);
        }
        ///////////////

        for (int i = 0; i < 16; i++) {
            BFS.SetWall(60-i,6+i);
        }
        for (int i = 0; i < 15; i++) {
            BFS.SetWall(60-i,7+i);
        }

        BFS.Addroute(59,17,"route0");

        DebugDrawer.StartDebugBFS(BFS.getTileMap(),"route0");
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        graphics2D.drawImage(mapImage, 0, 0, null);
    }

    public BreathFirstSearchT2 getBFS()
    {
        return BFS;
    }
}
