package MainPackage;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static OOFramework.Modules.CONSTANTS.CANVAS_HEIGHT;
import static OOFramework.Modules.CONSTANTS.CANVAS_WIDTH;

public class Rectangle2 extends StandardObject
{
    private Graphics2D graphics2D;
    private Renderable square2D;
    private Canvas canvas;

    protected Rectangle2(FrameworkProgram frameworkProgram, Graphics2D graphics2D, int startPosX,int startPosY) {
        super(frameworkProgram, true, true, true, true,999, 1000);
        this.graphics2D = graphics2D;
        this.square2D = new Renderable(new Rectangle2D.Double(-150,-150,300,300), new Point2D.Double(startPosX,startPosY), 0, 1);
        this.canvas = frameworkProgram.getCanvas();
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        //square2D.setPosition(new Point2D.Double(square2D.getPosition().getX()+deltaTime*650,square2D.getPosition().getY()));
        //square2D.setRotation(square2D.getRotation()+ (float)deltaTime*10.0f);
        if(square2D.getPosition().getX()>=CANVAS_WIDTH)
        {
            //square2D.setPosition(new Point2D.Double(-50,Math.random()*CANVAS_HEIGHT));
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        graphics2D.setColor(Color.RED);
        graphics2D.draw(square2D.getTransformedShape());
        graphics2D.fill(square2D.getTransformedShape());
        graphics2D.setColor(Color.BLACK);
    }
}
