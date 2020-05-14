package OOFramework.Debug;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.Pathfinding.BFS.BFSTile;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.*;

public class DebugDrawer extends StandardObject
{
    private FXGraphics2D graphics2D;
    private FrameworkProgram frameworkProgram;

    public  float updateInterval = 0.5F;

    private float accum   = 0; // FPS accumulated over the interval
    private int   frames  = 0; // Frames drawn over the interval
    private float timeleft;    // Left time for current interval
    private float fps = 0f;

    Font font = new Font("Serif", Font.PLAIN, 24);

    public DebugDrawer(FrameworkProgram frameworkProgram)
    {
        super(frameworkProgram, true, true, true, true, 10000, 10000);
        if(!DEBUG_MODE)
        {
            this.setActive(false);
        }
        graphics2D = frameworkProgram.getGraphics2D();
        this.frameworkProgram = frameworkProgram;
        timeleft = updateInterval;
    }


    @Override
    protected void MainLoop(double deltaTime)
    {
        super.MainLoop(deltaTime);
        if(DEBUG_FPS)
        {
            timeleft -= deltaTime;
            accum += deltaTime;
            frames++;

            // Interval ended - update GUI text and start new interval
            if (timeleft <= 0.0) {
                // display two fractional digits (f2 format)
                fps = 1 / (accum / frames);

                timeleft = updateInterval;
                accum = 0.0F;
                frames = 0;
            }
        }
    }



    //BFS debugging
    private static boolean debugBFS = false;
    private static BFSTile bfsGrid[][];
    private static ArrayList<Rectangle> debugBFSPath;
    private static ArrayList<Rectangle> debugBFSWall;

    public static void StartDebugBFS(BFSTile bfsGrid[][], String routeName)
    {
        if(DEBUG_BFS) {
            bfsGrid = bfsGrid;
            debugBFS = true;
            debugBFSPath = new ArrayList<Rectangle>();
            debugBFSWall = new ArrayList<Rectangle>();

            for (int i = 0; i < bfsGrid.length; i++) {
                for (int j = 0; j < bfsGrid[0].length; j++) {
                    if (bfsGrid[i][j].isWall) {
                        Rectangle rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 0);
                        rect.setRectangleColor(Color.RED);
                        debugBFSWall.add(rect);
                    } else if (bfsGrid[i][j].routes.containsKey(routeName)) {
                        Rectangle rect;
                        if (bfsGrid[i][j].routes.get(routeName).x == 1 && bfsGrid[i][j].routes.get(routeName).y == 0) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 0);//0° × π/180
                            //System.out.print("> ");
                        } else if (bfsGrid[i][j].routes.get(routeName).x == -1 && bfsGrid[i][j].routes.get(routeName).y == 0) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 3.14159f);//180° × π/180
                            //System.out.print("< ");
                        } else if (bfsGrid[i][j].routes.get(routeName).y == 1 && bfsGrid[i][j].routes.get(routeName).x == 0) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 1.5708f);//90° × π/180
                            //System.out.print("v ");
                        } else if (bfsGrid[i][j].routes.get(routeName).y == -1 && bfsGrid[i][j].routes.get(routeName).x == 0) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 4.71239f);//270° × π/180
                            //System.out.print("^ ");
                        } else if (bfsGrid[i][j].routes.get(routeName).x == 1 && bfsGrid[i][j].routes.get(routeName).y == 1) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 0.785398f);//45° × π/180
                            //System.out.print("> ");
                        } else if (bfsGrid[i][j].routes.get(routeName).x == -1 && bfsGrid[i][j].routes.get(routeName).y == 1) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 2.35619f);//135° × π/180
                            //System.out.print("< ");
                        } else if (bfsGrid[i][j].routes.get(routeName).y == -1 && bfsGrid[i][j].routes.get(routeName).x == 1) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 5.49779f);//315° × π/180
                            //System.out.print("v ");
                        } else if (bfsGrid[i][j].routes.get(routeName).y == -1 && bfsGrid[i][j].routes.get(routeName).x == -1) {
                            rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 3.92699f);//225° × π/180
                            //System.out.print("^ ");
                        } else {
                            rect = new Rectangle(-64, -64, 32, 32, 0);
                        }
                        rect.SetImageByFilePath("assets/arrow.png");
                        debugBFSPath.add(rect);
                    }
                }
            }
        }
    }
    ///////////////


    private static boolean debugCollision = false;
    private static ArrayList<Collider2D> colliders2D;

    public static void DebugCollision(ArrayList<Collider2D> colliders)
    {
        if(DEBUG_COLLISION) {
            debugCollision = true;
            colliders2D = colliders;
        }
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        if(DEBUG_MODE)
        {
            if(DEBUG_FPS)
            {
                graphics2D.setFont(font);

                FontMetrics fontMetrics = graphics2D.getFontMetrics();
                if (fps < 10) {
                    graphics2D.setColor(Color.red);
                }
                else if (fps < 30)
                {
                    graphics2D.setColor(Color.yellow);
                }
                else
                {
                    graphics2D.setColor(Color.green);
                }

                graphics2D.drawString( String.format("%.02f", fps) + " FPS", CANVAS_WIDTH-114, 10+fontMetrics.getAscent());
            }


            if(debugBFS)
            {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                for(Rectangle r : debugBFSPath)
                {
                    r.ImageDraw(graphics2D);
                }
                for(Rectangle r : debugBFSWall)
                {
                    r.Draw(graphics2D);
                }
            }

            if(debugCollision)
            {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                for (Collider2D col : colliders2D)
                {
                    switch (col.getColliderType())
                    {
                        case CIRCLE_COLLIDER:
                            //circle = new Circle(256, 256,256,0);
                            CircleCollider ccol = (CircleCollider) col;
                            Ellipse2D ellipse2D = new Ellipse2D.Double(ccol.getPos().x - ccol.radius ,ccol.getPos().y - ccol.radius, ccol.radius*2,ccol.radius*2);
                            if(ccol.getIsColliding())
                            {
                                graphics2D.setColor(Color.red);
                            }
                            else
                            {
                                graphics2D.setColor(Color.black);
                            }
                            graphics2D.draw(ellipse2D);
                            break;
                        case BOX_COLLIDER:
                            BoxCollider bcol = (BoxCollider) col;
                            Rectangle2D rectangle2D = new Rectangle2D.Double(bcol.getPos().x - bcol.width*0.5 ,bcol.getPos().y - bcol.height*0.5, bcol.width,bcol.height);
                            if(bcol.getIsColliding())
                            {
                                graphics2D.setColor(Color.red);
                            }
                            else
                            {
                                graphics2D.setColor(Color.black);
                            }
                            graphics2D.draw(rectangle2D);
                            break;
                        default:
                            System.out.println("hmmm");
                            break;
                    }
                }
                graphics2D.setColor(Color.black);
            }
        }
    }
}
