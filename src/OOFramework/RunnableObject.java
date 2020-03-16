package OOFramework;

public abstract class RunnableObject extends BaseObject implements Runnable
{
    protected RunnableObject(FrameworkProgram frameworkProgram, boolean startsActivated)
    {
        super(frameworkProgram, startsActivated);
    }

    @Override
    public void run()
    {

    }
}
