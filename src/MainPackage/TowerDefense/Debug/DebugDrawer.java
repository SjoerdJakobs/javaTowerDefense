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
    private static ArrayList<Rectangle> debugBFSRectangles;
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
        debugBFSRectangles = new ArrayList<Rectangle>();

        for (int i = 0; i < bfsGrid.length; i++)
        {
            for (int j = 0; j < bfsGrid[0].length; j++)
            {
                if(bfsGrid[i][j].routes.containsKey(routeName))
                {
                    Rectangle rect;
                    if(bfsGrid[i][j].routes.get(routeName).x ==  1)
                    {
                        rect = new Rectangle(-36 + i*32, -32 + j*32,32,32, 0);
                        System.out.print("> ");
                    }else if(bfsGrid[i][j].routes.get(routeName).x == -1)
                    {
                        rect = new Rectangle(-36 + i*32, -32 + j*32, 32,32,180);
                        System.out.print("< ");
                    }else if(bfsGrid[i][j].routes.get(routeName).y ==  1)
                    {
                        rect = new Rectangle(-36 + i*32, -32 + j*32, 32,32,90);
                        System.out.print("v ");
                    }else if(bfsGrid[i][j].routes.get(routeName).y == -1)
                    {
                        rect = new Rectangle(-36 + i*32, -32 + j*32, 32,32,270);
                        System.out.print("^ ");
                    }
                    else
                    {
                        rect = new Rectangle(-36 + i*32, -32 + j*32, 32,32,0);
                    }
                    rect.SetImageByFilePath("C:\\stack\\JavaProjecten\\javaTowerDefense\\assets\\arrow.png");
                    debugBFSRectangles.add(rect);
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
                for(Rectangle r : debugBFSRectangles)
                {
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    r.ImageDraw(graphics2D);
                }
            }
        }
    }
}
