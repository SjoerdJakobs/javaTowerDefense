package OOFramework.FXGraphics2dClasses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;

import static OOFramework.Modules.CONSTANTS.STANDARD_IMAGE_FILENAME_PREFIX;


public class Circle {
    private Renderable circle2D;
    private Color circleColor;
    private Image art;
    private final int radius;
    private final int xPos;
    private final int yPos;

    public Circle(int xPos, int yPos, int radius, float rotation) {
        this.circle2D = new Renderable(new Ellipse2D.Double(-radius, -radius, radius + radius, radius + radius), new Point2D.Double(xPos, yPos), rotation, 1);
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = yPos;
        SetImageByFileName("Default.png");
    }

    public void SetImageByFileName(String fileName) {
        InputStream is;
        is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX + fileName);
        try {
            art = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Draw(Graphics2D graphics2D) {
        graphics2D.setColor(circleColor);
        graphics2D.draw(circle2D.getTransformedShape());
    }

    public void FilledDraw(Graphics2D graphics2D) {
        Shape shape = circle2D.getTransformedShape();
        graphics2D.setColor(circleColor);
        graphics2D.fill(shape);
        graphics2D.draw(shape);
    }

    public void FilledDrawWithLine(Graphics2D graphics2D, Color lineColor) {
        Shape shape = circle2D.getTransformedShape();
        graphics2D.setColor(circleColor);
        graphics2D.fill(shape);
        graphics2D.setColor(lineColor);
        graphics2D.draw(shape);
    }

    public void ImageDraw(Graphics2D graphics2D) {
        //ASSERT_MSG.ASSERT_MSG(true, "dont forget to fix the centering problem");
        graphics2D.drawImage(art, circle2D.getTransform(), null);
    }


    //getters and setters from here
    public Color getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }

    public Renderable getCircle2D() {
        return circle2D;
    }

    public void setCircle2D(Renderable circle2D) {
        this.circle2D = circle2D;
    }

    public Image getArt() {
        return art;
    }

    public void setArt(Image art) {
        this.art = art;
    }
}
