package MainPackage.TowerDefense;

import MainPackage.Program;
import MainPackage.TowerDefense.Debug.DebugDrawer;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BreathFirstSearch;
import OOFramework.StandardObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GridManager extends StandardObject
{
    private Program program;
    private DebugDrawer debugDrawer;
    private BufferedImage mapImage;
    private Graphics2D graphics2D;
    private BreathFirstSearch BFS;

    public GridManager(FrameworkProgram frameworkProgram, Program program)
    {
        super(frameworkProgram, false, true, true, true, 10, 100);
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.BFS = new BreathFirstSearch();
        LoadMap();
    }

    public void LoadMap()
    {

        /**
         * thiss will be replaced by loading a file from a map editor, i could use TILE but i want to make one myself
         */
        TDTileData mapData[][] = new TDTileData[62][36];
        for(int i = 0; i < 62; i++)
        {
            for(int j = 0; j < 36; j++)
            {
                if(j == 17)
                {
                    mapData[i][j] = new TDTileData(new Vector2(i,j), TileArtEnum.ROAD);
                }
                else
                {
                    mapData[i][j] = new TDTileData(new Vector2(i,j), TileArtEnum.GROUND);
                }
            }
        }
        /**
         * //////////////////////////////////////////////////////////////////////////////////////////////////////////
         */

        mapImage = new BufferedImage(1920,1088, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics imageGraphics = mapImage.getGraphics();
        for(int i = 0; i < 62; i++)
        {
            for(int j = 0; j < 36; j++)
            {
                imageGraphics.drawImage(mapData[i][j].tileArt, (-36 + i * 32), (-32 + j * 32), null);
            }
        }
        BFS.CreateTileGrid(62,36);
        for(int i = 0; i < 62; i++)
        {
            for(int j = 0; j < 36; j++)
            {
                if(j == 10)
                {
                    if(i != 31)
                    {
                        BFS.SetWall(i,j);
                    }
                }
                else if(j == 24)
                {
                    BFS.SetWall(i,j);
                }
            }
        }

        for (int i = 0; i < 11; i++) {
            BFS.SetWall(30,10+i);
        }
        for (int i = 0; i < 11; i++) {
            BFS.SetWall(15,13+i);
        }
        for (int i = 0; i < 9; i++) {
            BFS.SetWall(16+i,13);
        }
        for (int i = 0; i < 9; i++) {
            BFS.SetWall(21+i,20);
        }

        BFS.Addroute(61,17,"route0");
        DebugDrawer.StartDebugFBS(BFS.getTileMap(),"route0");
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        graphics2D.drawImage(mapImage, 0, 0, null);
    }
}
