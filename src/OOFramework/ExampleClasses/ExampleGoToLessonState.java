package OOFramework.ExampleClasses;

import OOFramework.statemachine.State;
import OOFramework.statemachine.StateID;

public class ExampleGoToLessonState extends State
{
    private ExampleStudent Student;

    public ExampleGoToLessonState(ExampleStudent exampleStudent)
    {
        /**
         * make sure that"ExampleGoToLessonState" is added in the class StateID
         */
        super(StateID.ExampleGoToLessonState);
        this.Student = exampleStudent;
    }

    /**
     * this function gets called when you enter the state and gets called before the loops
     */
    @Override
    protected void enter()
    {
        super.enter();
        System.out.println("lets go to the lesson");
    }

    /**
     * this function gets called after the start and awake function but before the logic loop.
     * this function gets called every program cycle
     */
    @Override
    protected void checkForStateSwitch()
    {
        super.checkForStateSwitch();
        if(Student.getPeeValue() >= Student.getPeeThreshold())
        {
            stateMachine.SetState(StateID.ExampleGoToToiletState);
        }
    }

    double timer = 0;
    /**
     * this function gets called after the start, awake and checkForStateSwitch function but before the leave function.
     * this function gets called every program cycle
     */
    @Override
    protected void logic()
    {
        super.logic();
        //deltatime is time inbetween frames
        timer += stateMachine.getDeltaTime();
        if(timer >= 1) {
            System.out.println("iam in or going to the lesson");
            timer = 0;
        }
        Student.setPeeValue(Student.getPeeValue() + stateMachine.getDeltaTime());
    }

    /**
     * this function gets called when you leave this state and after all other functions
     */
    @Override
    protected void leave()
    {
        super.leave();
        System.out.println("going to 'going to toilet state'");
    }
}