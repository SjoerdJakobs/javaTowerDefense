package OOFramework;

import com.sun.org.apache.xpath.internal.objects.XNull;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static OOFramework.Modules.CONSTANTS.*;

public abstract class FrameworkProgram extends Application
{
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean paused  = new AtomicBoolean(false);

    private final AtomicReference<ArrayList<BaseObject>> objects = new AtomicReference<>(new ArrayList<BaseObject>());

    private final AtomicReference<ArrayList<RunnableObject>> runnableObjects = new AtomicReference<>(new ArrayList<RunnableObject>());

    private ArrayList<StandardObject> inputObjects  = new ArrayList<StandardObject>();
    private ArrayList<PriorityGroup> mainGroups = new ArrayList<PriorityGroup>();
    private ArrayList<PriorityGroup> renderGroups = new ArrayList<PriorityGroup>();

    private BufferStrategy bufferStrategy = null;

    //deltatime influenced by timeScale and if the program is paused or not
    private double deltaTime = 0;
    //the current scale for delata time, 2 would speed everything up and 0.5 would slow everything down
    protected double timeScale = 1;

    //deltatime that will always give the time between frames
    private double unscaledDeltaTime = 0;

    protected Stage stage;
    protected Canvas canvas;
    protected FXGraphics2D graphics2D;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.stage.setScene(new Scene(new Group(canvas)));
        this.stage.setTitle(TITLE);
        this.stage.setMaximized(true);
        //this.stage.setFullScreen(true);
        //this.stage.setResizable(false);
        this.stage.show();
        Init();

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                Run(graphics2D);
                last = now;
            }
        }.start();
    }




    long lastTime = System.nanoTime();

    public void Run(FXGraphics2D g2d)
    {

        /**
         * calculate deltatime
         */
        long time = System.nanoTime();
        unscaledDeltaTime = (((double) (time - lastTime) / 1000_000_000));//the true delta time in seconds
        if(this.paused.get()) {
            deltaTime = 0;
        }else {
            deltaTime = unscaledDeltaTime * timeScale;//scaled delta time in seconds
        }
        lastTime = time;

        //uncomment to print the deltatime in seconds
        //String s = String.format("%.5f", deltaTime);
        //System.out.println(s);

        AddToLoop();

        //input uses the unscaledDeltaTime since this loop should not be used for program logic
        for (StandardObject object : inputObjects) {
            object.InputLoop(unscaledDeltaTime);
        }

        for (PriorityGroup group : mainGroups)
        {
            for (StandardObject object : group.standardObjects)
            {
                object.MainLoop(deltaTime);
            }
        }

        //clear screen
        g2d.setBackground(Color.white);
        g2d.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);

        for (PriorityGroup group : renderGroups)
        {
            for (StandardObject object : group.standardObjects)
            {
                object.RenderLoop(deltaTime);
            }
        }

        Iterator<BaseObject> it = objects.get().iterator();
        while (it.hasNext()) {
            BaseObject bo = it.next();
            if (bo.ShouldDestruct()) {
                bo.Destroy();
                it.remove();
            } else if (bo.isActive() && !bo.isActivated()) {
                bo.AddToLists();
                bo.setActivated(true);
                bo.Awake();
            } else if (!bo.isActive() && bo.isActivated()) {
                bo.RemoveFromLists();
                bo.setActivated(false);
                bo.Sleep();
            }
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void Init()
    {
        running.set(true);
    }

    protected void AddToLoop()
    {

    }

    protected void ExitProgram()
    {
        running.set(false);
    }


    public AtomicBoolean isRunning()
    {
        return running;
    }

    public AtomicBoolean isPaused()
    {
        return paused;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public FXGraphics2D getGraphics2D() {
        return graphics2D;
    }

    public AtomicReference<ArrayList<BaseObject>> getObjects()
    {
        return objects;
    }

    public AtomicReference<ArrayList<RunnableObject>> getRunnableObjects()
    {
        return runnableObjects;
    }

    public ArrayList<StandardObject> getInputObjects()
    {
        return inputObjects;
    }

    public ArrayList<PriorityGroup> getMainGroups()
    {
        return mainGroups;
    }

    public ArrayList<PriorityGroup> getRenderGroups()
    {
        return renderGroups;
    }
}

