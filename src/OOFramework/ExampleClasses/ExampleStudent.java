package OOFramework.ExampleClasses;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import OOFramework.statemachine.StateID;
import OOFramework.statemachine.StateMachine;

public class ExampleStudent extends StandardObject
{
    /**
     * this var will hold the statemachine for the student ai, this is yet to be instantiated
     */
    private StateMachine StudentStateMachine;
    /**
     *how much does this student need to pee
     */
    private double peeValue = 0;

    /**
     *when does this student need to go to thw toilet
     */
    private double peeThreshold = 5;

    /**
     * decide which loops are used and give the main framework to this object
     */
    public ExampleStudent(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated)
    {
        super(frameworkProgram, usesInput, usesMain, usesRenderer, startsActivated);
    }

    /**
     * decide which loops are used and give the main framework to this object
     */
    public ExampleStudent(FrameworkProgram frameworkProgram, double peeThreshold)
    {
        super(frameworkProgram, false, true, true, true);
        this.peeThreshold = peeThreshold;
    }

    /**
     * this is the first thing that gets called after its constructor
     */
    @Override
    protected void Start()
    {
        super.Start();
        /**
         * make a new statemachine, essentially the base of the ai for this student
         */
        StudentStateMachine = new StateMachine();

        /**
         * create the states that the student will use and then add them to the statemachine of the student.
         * dont forget to make a enum in the class StateID
         */
        StudentStateMachine.AddState(new ExampleGoToLessonState(this));
        StudentStateMachine.AddState(new ExampleGoToToiletState(this));

        /**
         * start and set the state in which the student starts
         */
        StudentStateMachine.SetState(StateID.ExampleGoToLessonState);
    }

    @Override
    protected void Awake()
    {
        super.Awake();
    }

    @Override
    protected void MainLoop(double deltaTime)
    {
        //deltatime is time inbetween frames and already gets given
        super.MainLoop(deltaTime);
        /**
         * run the statemachine loop
         */
        StudentStateMachine.StateMachineLoop(deltaTime);
    }

    //getters and setters below here
    public double getPeeValue()
    {
        return peeValue;
    }

    public void setPeeValue(double peeValue)
    {
        this.peeValue = peeValue;
    }

    public StateMachine getStudentStateMachine()
    {
        return StudentStateMachine;
    }

    public void setStudentStateMachine(StateMachine studentStateMachine)
    {
        StudentStateMachine = studentStateMachine;
    }

    public double getPeeThreshold() {
        return peeThreshold;
    }

    public void setPeeThreshold(double peeThreshold) {
        this.peeThreshold = peeThreshold;
    }
}
