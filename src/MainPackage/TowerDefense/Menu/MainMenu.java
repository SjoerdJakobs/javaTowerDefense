package MainPackage.TowerDefense.Menu;

import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.StandardObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;

import static OOFramework.Modules.CONSTANTS.*;

public class MainMenu extends StandardObject
{
    private Graphics2D graphics2D;
    private final Rectangle backgroundImg;

    public MainMenu() {
        super(true, true, true, true);

        this.backgroundImg = new Rectangle((int) CANVAS_WIDTH/2, (int) CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT, 0);
        backgroundImg.setRectangleColor(Color.blue);
        InputStream is;
        try {
            backgroundImg.setArt(ImageIO.read(getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX + "space.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D = getFrameworkProgram().getGraphics2D();
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        backgroundImg.ImageDraw(graphics2D);
    }
}
