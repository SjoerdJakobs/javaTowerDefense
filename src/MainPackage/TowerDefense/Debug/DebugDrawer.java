package MainPackage.TowerDefense.Debug;

import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Pathfinding.BFS.BFSTile;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.DEBUG_MODE;

public class DebugDrawer extends StandardObject
{
    private FXGraphics2D graphics2D;

    //BFS debugging
    private static boolean debugBFS = false;
    private static BFSTile bfsGrid[][];
    private static ArrayList<Rectangle> debugBFSPath;
    private static ArrayList<Rectangle> debugBFSWall;
    ///////////////

    public DebugDrawer(FrameworkProgram frameworkProgram)
    {
        super(frameworkProgram, true, true, true, true, 10000, 10000);
        if(!DEBUG_MODE)
        {
            this.setActive(false);
        }
        graphics2D = frameworkProgram.getGraphics2D();
    }

    public static void StartDebugFBS(BFSTile bfsGrid[][], String routeName)
    {
        bfsGrid = bfsGrid;
        debugBFS = true;
        debugBFSPath = new ArrayList<Rectangle>();
        debugBFSWall = new ArrayList<Rectangle>();

        for (int i = 0; i < bfsGrid.length; i++)
        {
            for (int j = 0; j < bfsGrid[0].length; j++)
            {
                if(bfsGrid[i][j].isWall)
                {
                    Rectangle rect = new Rectangle(-4 + i*32, j*32,32,32, 0);
                    rect.setRectangleColor(Color.RED);
                    debugBFSWall.add(rect);
                }
                else if(bfsGrid[i][j].routes.containsKey(routeName))
                {
                    Rectangle rect;
                    if(bfsGrid[i][j].routes.get(routeName).x ==  1)
                    {
                        rect = new Rectangle(-20 + i*32, -16+ j*32,32,32, 0);
                        //System.out.print("> ");
                    }else if(bfsGrid[i][j].routes.get(routeName).x == -1)
                    {
                        rect = new Rectangle(-20 + i*32, -16+ j*32, 32,32,3.12f);
                        //System.out.print("< ");
                    }else if(bfsGrid[i][j].routes.get(routeName).y ==  1)
                    {
                        rect = new Rectangle(-20 + i*32, -16+ j*32, 32,32,1.56f);//1.56
                        //System.out.print("v ");
                    }else if(bfsGrid[i][j].routes.get(routeName).y == -1)
                    {
                        rect = new Rectangle(-20 + i*32, -16+ j*32, 32,32,4.7f);
                        //System.out.print("^ ");
                    }
                    else
                    {
                        rect = new Rectangle(-20 + i*32, -16+ j*32,32,32, 0);
                    }
                    rect.SetImageByFilePath("C:\\stack\\JavaProjecten\\javaTowerDefense\\assets\\arrow.png");
                    debugBFSPath.add(rect);
                }
            }
        }
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        if(DEBUG_MODE)
        {
            if(debugBFS)
            {
                for(Rectangle r : debugBFSPath)
                {
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    r.ImageDraw(graphics2D);
                }
                for(Rectangle r : debugBFSWall)
                {
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    r.Draw(graphics2D);
                }
            }
        }
    }
}
