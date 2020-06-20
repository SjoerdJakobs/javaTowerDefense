package OOFramework.FXGraphics2dClasses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import static OOFramework.Modules.CONSTANTS.STANDARD_IMAGE_FILENAME_PREFIX;


public class Rectangle {
    private Renderable square2D;
    private Color rectangleColor;
    private Image art;
    private int xSizeOffset;
    private int ySizeOffset;

    public Rectangle(int xPos, int yPos, int xSize, int ySize, float rotation) {
        this.square2D = new Renderable(new Rectangle2D.Double(-(xSize / 2), -(ySize / 2), xSize, ySize), new Point2D.Double(xPos, yPos), rotation, 1);
        this.xSizeOffset = xSize / 2;
        this.ySizeOffset = ySize / 2;
        SetImageByFileName("Default.png");
    }

    public void SetImageByFileName(String fileName) {
        InputStream is;
        is = getClass().getResourceAsStream(STANDARD_IMAGE_FILENAME_PREFIX+fileName);
        try {
            art = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Draw(Graphics2D graphics2D) {
        graphics2D.setColor(rectangleColor);
        graphics2D.draw(square2D.getTransformedShape());
    }

    public void FilledDraw(Graphics2D graphics2D) {
        graphics2D.setColor(rectangleColor);
        graphics2D.fill(square2D.getTransformedShape());
        graphics2D.draw(square2D.getTransformedShape());
    }

    public void ImageDraw(Graphics2D graphics2D) {
        //ASSERT_MSG.ASSERT_MSG(true, "dont forget to fix the centering problem");
        graphics2D.drawImage(art, square2D.getTransform(xSizeOffset, ySizeOffset), null);
    }


    //getters and setters from here
    public Color getRectangleColor() {
        return rectangleColor;
    }

    public void setRectangleColor(Color rectangleColor) {
        this.rectangleColor = rectangleColor;
    }

    public Renderable getSquare2D() {
        return square2D;
    }

    public void setSquare2D(Renderable square2D) {
        this.square2D = square2D;
    }

    public Image getArt() {
        return art;
    }

    public void setArt(Image art) {
        this.art = art;
    }
}
