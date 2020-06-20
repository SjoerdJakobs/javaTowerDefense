package OOFramework.ExampleClasses;

import OOFramework.statemachine.State;
import OOFramework.statemachine.StateID;

public class ExampleState extends State {
    public ExampleState() {
        super(StateID.ExampleState);
    }

    @Override
    protected void Enter() {
        super.Enter();
    }

    @Override
    protected void CheckForStateSwitch() {
        super.CheckForStateSwitch();
    }

    @Override
    protected void Logic() {
        super.Logic();
    }

    @Override
    protected void Leave() {
        super.Leave();
    }
}
