package MainPackage;

import MainPackage.TowerDefense.Debug.DebugDrawer;
import MainPackage.TowerDefense.GridManager;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Program extends FrameworkProgram
{
    private DebugDrawer debugDrawer;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        debugDrawer = new DebugDrawer(this);
        GridManager gridManager = new GridManager(this,this);
    }

    private ArrayList<OOFramework.FXGraphics2dClasses.Rectangle> rects = new ArrayList<Rectangle>();
    @Override
    protected void Init() {
        super.Init();

    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }

    public DebugDrawer getDebugDrawer()
    {
        return debugDrawer;
    }
}
