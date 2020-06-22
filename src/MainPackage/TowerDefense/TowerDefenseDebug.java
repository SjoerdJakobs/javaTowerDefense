package MainPackage.TowerDefense;

import OOFramework.Debug.DebugDrawer;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.DEBUG_MODE;

public class TowerDefenseDebug extends StandardObject {
    private static ArrayList<Rectangle> debugTDPlacable;
    private static boolean debugTDTiles = false;
    private static TowerDefenseDebug INSTANCE = null;
    private final FXGraphics2D graphics2D;
    private final FrameworkProgram frameworkProgram;

    private TowerDefenseDebug(FrameworkProgram frameworkProgram) {
        super(true, true, true, true, 10000, 10000);
        if (!DEBUG_MODE) {
            this.setActive(false);
        }
        graphics2D = frameworkProgram.getGraphics2D();
        this.frameworkProgram = frameworkProgram;
    }

    public static TowerDefenseDebug getInstance() {
        if (INSTANCE == null) {
            synchronized (DebugDrawer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TowerDefenseDebug(FrameworkProgram.getProgramInstance());
                }
            }
        }
        return INSTANCE;
    }

    public static void StartDebugTD(TDTileData[][] tdTileData) {
        debugTDPlacable = new ArrayList<Rectangle>();

        for (int i = 0; i < tdTileData.length; i++) {
            for (int j = 0; j < tdTileData[0].length; j++) {
                if (tdTileData[i][j].canPlaceTower) {
                    Rectangle rect = new Rectangle(16 + i * 32, 12 + j * 32, 32, 32, 0);
                    rect.setRectangleColor(Color.RED);
                    debugTDPlacable.add(rect);
                }
            }
        }

        debugTDTiles = true;
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        if (debugTDTiles) {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            for (Rectangle r : debugTDPlacable) {
                r.FilledDraw(graphics2D);
            }
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
