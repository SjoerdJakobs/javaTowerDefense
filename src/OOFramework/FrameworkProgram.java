package OOFramework;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
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

    private ArrayList<StandardObject> inputObjectsToBeAdded = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> mainObjectsToBeAdded = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> renderObjectsToBeAdded = new ArrayList<StandardObject>();
    private boolean shouldAddToInputList = false;
    private boolean shouldAddToMainGroup = false;
    private boolean shouldAddToRenderGroup = false;

    private ArrayList<StandardObject> inputObjectsToBeRemoved = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> mainObjectsToBeRemoved = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> renderObjectsToBeRemoved = new ArrayList<StandardObject>();
    private boolean shouldRemoveFromInputList = false;
    private boolean shouldRemoveFromMainGroup = false;
    private boolean shouldRemoveFromRenderGroup = false;

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
                if(!isRunning())
                {
                    stop();
                    System.exit(0);
                }
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
            if(!this.paused.get()) {
                object.InputLoop(unscaledDeltaTime);
            }
        }

        for (PriorityGroup group : mainGroups)
        {
            if(!this.paused.get()) {
                for (StandardObject object : group.standardObjects)
                {
                    object.MainLoop(deltaTime);
                }
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

        if(shouldAddToInputList) {
            for(StandardObject ro : inputObjectsToBeAdded) {
                inputObjects.add(ro);
            }
            inputObjectsToBeAdded.clear();
            shouldAddToInputList = false;
        }

        if(shouldRemoveFromInputList) {
            for(StandardObject ro : inputObjectsToBeRemoved) {
                inputObjects.remove(ro);
            }
            inputObjectsToBeRemoved.clear();
            shouldRemoveFromInputList = false;
        }

        if(shouldAddToMainGroup) {
            for(StandardObject ro : mainObjectsToBeAdded) {
                boolean hasGroup = false;
                for (PriorityGroup group : mainGroups) {
                    if (group.priorityNr == ro.getObjectPriority()) {
                        group.standardObjects.add(ro);
                        hasGroup = true;
                    }
                }
                if (!hasGroup) {
                    final PriorityGroup addGroup = new PriorityGroup(ro.getObjectPriority());
                    addGroup.standardObjects.add(ro);
                    mainGroups.add(addGroup);
                }
            }
            mainGroups.sort(Comparator.comparing(PriorityGroup::getPriorityNr));
            mainObjectsToBeAdded.clear();
            shouldAddToMainGroup = false;
        }

        if(shouldRemoveFromMainGroup) {
            for(StandardObject ro : mainObjectsToBeRemoved) {
                for (PriorityGroup group : mainGroups) {
                    if (group.priorityNr == ro.getObjectPriority()) {
                        group.standardObjects.remove(ro);
                    }
                }
            }
            mainGroups.sort(Comparator.comparing(PriorityGroup::getPriorityNr));
            mainObjectsToBeRemoved.clear();
            shouldRemoveFromMainGroup = false;
        }

        if(shouldAddToRenderGroup) {
            for(StandardObject ro : renderObjectsToBeAdded) {
                boolean hasGroup = false;
                for (PriorityGroup group : renderGroups) {
                    if (group.priorityNr == ro.getRenderPriority()) {
                        group.standardObjects.add(ro);
                        hasGroup = true;
                    }
                }
                if (!hasGroup) {
                    final PriorityGroup addGroup = new PriorityGroup(ro.getRenderPriority());
                    addGroup.standardObjects.add(ro);
                    renderGroups.add(addGroup);
                }
            }
            renderGroups.sort(Comparator.comparing(PriorityGroup::getPriorityNr));
            renderObjectsToBeAdded.clear();
            shouldAddToRenderGroup = false;
        }

        if(shouldRemoveFromRenderGroup) {
            for(StandardObject ro : renderObjectsToBeRemoved) {
                for (PriorityGroup group : renderGroups) {
                    if (group.priorityNr == ro.getRenderPriority()) {
                        group.standardObjects.remove(ro);
                    }
                }
            }
            renderGroups.sort(Comparator.comparing(PriorityGroup::getPriorityNr));
            renderObjectsToBeRemoved.clear();
            shouldRemoveFromRenderGroup = false;
        }

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void UpdateLists()
    {

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


    public boolean isRunning()
    {
        return running.get();
    }

    public boolean isPaused()
    {
        return paused.get();
    }

    public void setPaused(boolean setPaused)
    {
        this.paused.set(setPaused);
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

    public ArrayList<StandardObject> getInputObjectsToBeAdded()
    {
        return inputObjectsToBeAdded;
    }

    public ArrayList<StandardObject> getMainObjectsToBeAdded()
    {
        return mainObjectsToBeAdded;
    }

    public ArrayList<StandardObject> getRenderObjectsToBeAdded()
    {
        return renderObjectsToBeAdded;
    }

    public boolean isShouldAddToInputList()
    {
        return shouldAddToInputList;
    }

    public void setShouldAddToInputList(boolean shouldAddToInputList)
    {
        this.shouldAddToInputList = shouldAddToInputList;
    }

    public boolean isShouldAddToMainGroup()
    {
        return shouldAddToMainGroup;
    }

    public void setShouldAddToMainGroup(boolean shouldAddToMainGroup)
    {
        this.shouldAddToMainGroup = shouldAddToMainGroup;
    }

    public boolean isShouldAddToRenderGroup()
    {
        return shouldAddToRenderGroup;
    }

    public void setShouldAddToRenderGroup(boolean shouldAddToRenderGroup)
    {
        this.shouldAddToRenderGroup = shouldAddToRenderGroup;
    }

    public ArrayList<StandardObject> getInputObjectsToBeRemoved()
    {
        return inputObjectsToBeRemoved;
    }

    public ArrayList<StandardObject> getMainObjectsToBeRemoved()
    {
        return mainObjectsToBeRemoved;
    }

    public ArrayList<StandardObject> getRenderObjectsToBeRemoved()
    {
        return renderObjectsToBeRemoved;
    }

    public boolean isShouldRemoveFromInputList()
    {
        return shouldRemoveFromInputList;
    }

    public void setShouldRemoveFromInputList(boolean shouldRemoveFromInputList)
    {
        this.shouldRemoveFromInputList = shouldRemoveFromInputList;
    }

    public boolean isShouldRemoveFromMainGroup()
    {
        return shouldRemoveFromMainGroup;
    }

    public void setShouldRemoveFromMainGroup(boolean shouldRemoveFromMainGroup)
    {
        this.shouldRemoveFromMainGroup = shouldRemoveFromMainGroup;
    }

    public boolean isShouldRemoveFromRenderGroup()
    {
        return shouldRemoveFromRenderGroup;
    }

    public void setShouldRemoveFromRenderGroup(boolean shouldRemoveFromRenderGroup)
    {
        this.shouldRemoveFromRenderGroup = shouldRemoveFromRenderGroup;
    }

    public double getDeltaTime()
    {
        return deltaTime;
    }

    public double getTimeScale()
    {
        return timeScale;
    }

    public void setTimeScale(double timeScale)
    {
        this.timeScale = timeScale;
    }

    public double getUnscaledDeltaTime()
    {
        return unscaledDeltaTime;
    }
}

