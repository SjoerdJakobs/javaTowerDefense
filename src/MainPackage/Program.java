package MainPackage;

import OOFramework.ExampleClasses.ExampleStudent;
import OOFramework.FrameworkProgram;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.CANVAS_HEIGHT;
import static OOFramework.Modules.CONSTANTS.CANVAS_WIDTH;
import static javafx.application.Application.launch;

public class Program extends FrameworkProgram
{

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    private ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
    @Override
    protected void Init() {
        super.Init();
        timeScale = 1;
        Rectangle2 r = new Rectangle2(this,graphics2D,CANVAS_WIDTH/2,CANVAS_HEIGHT/2);
        for(int i = 0; i<200;i++)
        {
            rects.add(new Rectangle(this,graphics2D,((int)(Math.random()*2100))-100,(int)(Math.random()*1080)));
        }
    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }
}
