package MainPackage.TowerDefense;

import OOFramework.FXGraphics2dClasses.Renderable;
import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ActiveTDTile extends StandardObject {
    TDTileData tileData;

    private Graphics2D graphics2D;
    private Renderable square2D;
    private Canvas canvas;

    public ActiveTDTile(FrameworkProgram frameworkProgram, Graphics2D graphics2D, TDTileData tileData, int xPos, int yPos) {
        super(frameworkProgram, true, true, true, true, 1000, 1000);

        this.graphics2D = graphics2D;
        this.square2D = new Renderable(new Rectangle2D.Double(-25, -25, 50, 50), new Point2D.Double(xPos, yPos), 0, 1);
        this.canvas = frameworkProgram.getCanvas();
        this.tileData = tileData;
    }
}
