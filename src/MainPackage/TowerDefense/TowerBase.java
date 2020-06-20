package MainPackage.TowerDefense;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public class TowerBase extends StandardObject
{
    //protected final double range;
    //protected final double damage;
    //protected final double atackspeed;
    //protected final int upgradeLevel;
    //protected final int ammo;

    protected TowerBase(boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated, int renderPriority, int objectPriority) {
        super(usesInput, usesMain, usesRenderer, startsActivated, renderPriority, objectPriority);
    }
}
