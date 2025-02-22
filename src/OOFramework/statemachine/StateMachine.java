package OOFramework.statemachine;

import java.util.HashMap;
import java.util.Map;

public final class StateMachine {
    public State currentState;
    private final Map<StateID, State> states = new HashMap<StateID, State>();
    private double deltaTime;
    private long timeSinceSwitch;

    public StateMachine() {

    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void StateMachineLoop(double deltaTime) {
        this.deltaTime = deltaTime;
        if (currentState != null) {
            //System.out.println("executeState" + currentState.stateID);
            currentState.CheckForStateSwitch();
            currentState.Logic();
        }
    }

    public void SetState(StateID stateID) {
        SetState(stateID, 0);
    }

    /**
     * set the currentState of the state machine and exit the former currentState
     *
     * @param stateID the id of the state that will become the current state
     */
    public void SetState(StateID stateID, long delay) {
        if (System.nanoTime() - timeSinceSwitch < delay * 1000_000_000L) {
            return;
        }

        if (!states.containsKey(stateID)) {
            throw new IllegalArgumentException("State unknown");
        }
        if (currentState != null) {
            currentState.Leave();
        }
        currentState = states.get(stateID);
        currentState.Enter();
        timeSinceSwitch = System.nanoTime();
    }

    /**
     * Adds a state to the list of possible states in state machine
     *
     * @param state the state to be added
     */
    public void AddState(State state) {
        if (states.containsKey(state.stateID) || state.stateID == StateID.NullStateID) {
            throw new IllegalArgumentException("State unknown");
        }
        state.stateMachine = this;
        states.put(state.stateID, state);
    }
}