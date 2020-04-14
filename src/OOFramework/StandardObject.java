package OOFramework;

public abstract class StandardObject extends BaseObject
{
    private boolean usesInput;
    private boolean usesMain;
    private boolean usesRenderer;
    private int renderPriority = 1000;
    private int objectPriority = 1000;

    protected StandardObject(FrameworkProgram frameworkProgram)
    {
        this(frameworkProgram, true, true, true, true, 1000, 1000);
    }

    protected StandardObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated)
    {
        this(frameworkProgram, usesInput, usesMain, usesRenderer, startsActivated, 1000, 1000);
    }

    /**
     * look at the exampleObject class for a more extensive explanation of the standard object funtions that a standard object uses
     * you can find it in package OOFramework.ExampleClasses
     * @param frameworkProgram when you create an object you need to give the framework itself as a variable
     * @param usesInput put this to true if you want your object to have a update loop.
     * @param usesMain put this to true if you want your object to have a main/logic loop.
     * @param usesRenderer put this to true if you want your object to have a render loop.
     *                     if any of these variables are changed during runtime you need to call the AddToLists() method
     * @param startsActivated if this is set to true the object starts its work right away otherwise it starts in an sleeping/inactive state
     * @param renderPriority this number shows when this object should be rendered. lowest number gets rendered first and highest number last.
     *                       for the sake of performance, if the render order does not matter give them the same number.
     *                       if you have 1000 objects each with a different number it will take longer to sort them.
     */
    protected StandardObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated, int renderPriority, int objectPriority)
    {
        super(frameworkProgram, startsActivated);
        this.usesInput = usesInput;
        this.usesMain = usesMain;
        this.usesRenderer = usesRenderer;
        this.objectPriority = objectPriority;
        this.renderPriority = renderPriority;
        this.AddToLists();

        //System.out.println("Standard");
    }

    protected void InputLoop(double deltaTime)
    {

    }

    protected void MainLoop(double deltaTime)
    {

    }

    protected void RenderLoop(double deltaTime)
    {

    }

    @Override
    protected void RemoveFromLists()
    {
        super.RemoveFromLists();
        if (usesInput) {
            getFrameworkProgram().getInputObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromInputList(true);
        }

        if (usesMain)
        {
            getFrameworkProgram().getMainObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromMainGroup(true);
        }

        if (usesRenderer) {
            getFrameworkProgram().getRenderObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromRenderGroup(true);
        }
    }

    @Override
    protected void AddToLists()
    {
        super.AddToLists();
        if (usesInput) {
            getFrameworkProgram().getInputObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToInputList(true);
        }

        if (usesMain)
        {
            getFrameworkProgram().getMainObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToMainGroup(true);
        }

        if (usesRenderer) {
            getFrameworkProgram().getRenderObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToRenderGroup(true);
        }
    }

    @Override
    protected void Destroy()
    {
        super.Destroy();
    }


    //beyond this point just getters/setters
    public int getRenderPriority() {
        return renderPriority;
    }

    public int getObjectPriority() {
        return objectPriority;
    }

    public boolean UsesInput()
    {
        return usesInput;
    }

    public void setUsesInput(boolean usesInput)
    {
        this.usesInput = usesInput;
    }

    public boolean UsesMain()
    {
        return usesMain;
    }

    public void setUsesMain(boolean usesMain)
    {
        this.usesMain = usesMain;
    }

    public boolean UsesRenderer()
    {
        return usesRenderer;
    }

    public void setUsesRenderer(boolean usesRenderer)
    {
        this.usesRenderer = usesRenderer;
    }
}

