package MainPackage.TowerDefense;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public class InputManager extends StandardObject
{
    protected InputManager(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated, int renderPriority, int objectPriority)
    {
        super(frameworkProgram, usesInput, usesMain, usesRenderer, startsActivated, renderPriority, objectPriority);
    }
}
