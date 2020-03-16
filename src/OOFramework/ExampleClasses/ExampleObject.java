package OOFramework.ExampleClasses;
import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public class ExampleObject extends StandardObject
{
    /**
     * happens at: when this object is made
     * how often: once
     * execution order: 0
     */
    protected ExampleObject(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);
    }

    /**
     * happens at: when this object is made
     * how often: once
     * execution order: 0
     */
    public ExampleObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated) {
        super(frameworkProgram, usesInput, usesMain, usesRenderer, startsActivated);

        System.out.println("lowest");
    }

    /**
     * happens at: when this object is made
     * how often: once
     * execution order: 1
     */
    @Override
    protected void Start() {
        super.Start();
        //load my shit
    }

    /**
     * happens at: when this object is set to awake, if the object gets created in a awake state it gets called too
     * how often: once
     * execution order: 2
     */
    @Override
    protected void Awake() {
        super.Awake();
    }

    /**
     * happens at: if the object is created to have an input loop
     * how often: every program loop
     * execution order: 3
     * recieves: deltatime          the time inbetween frames
     */
    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
    }

    /**
     * happens at: if the object is created to have an main loop
     * how often: every program loop
     * execution order: 4
     * recieves: deltatime          the time inbetween frames
     */
    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
    }

    /**
     * happens at: if the object is created to have an render loop
     * how often: every program loop
     * execution order: 5
     * recieves: deltatime          the time inbetween frames
     */
    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
    }

    /**
     * happens at: when this object is set to sleep, if the object gets created in a sleep state it does not get called
     * how often: once
     * execution order: 6
     */
    @Override
    protected void Sleep()
    {
        super.Sleep();
    }

    /**
     * happens at: when this object is set to be destroyed
     * how often: once
     * execution order: 7
     */
    @Override
    protected void Destroy() {
        super.Destroy();
    }
}
