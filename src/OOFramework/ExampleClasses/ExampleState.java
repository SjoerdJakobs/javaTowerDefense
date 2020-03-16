package OOFramework.ExampleClasses;

import OOFramework.statemachine.State;
import OOFramework.statemachine.StateID;

public class ExampleState extends State
{
    public ExampleState()
    {
        super(StateID.ExampleState);
    }

    @Override
    protected void enter()
    {
        super.enter();
    }

    @Override
    protected void checkForStateSwitch()
    {
        super.checkForStateSwitch();
    }

    @Override
    protected void logic()
    {
        super.logic();
    }

    @Override
    protected void leave()
    {
        super.leave();
    }
}
