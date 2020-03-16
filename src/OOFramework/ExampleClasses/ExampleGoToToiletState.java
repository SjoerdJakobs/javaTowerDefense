package OOFramework.ExampleClasses;

import OOFramework.statemachine.State;
import OOFramework.statemachine.StateID;

public class ExampleGoToToiletState extends State
{
    private ExampleStudent Student;

    public ExampleGoToToiletState(ExampleStudent exampleStudent)
    {
        /**
         * make sure that"ExampleGoToToiletState" is added in the class StateID
         */
        super(StateID.ExampleGoToToiletState);
        this.Student = exampleStudent;
    }

    /**
     * this function gets called when you enter the state and gets called before the loops
     */
    @Override
    protected void enter()
    {
        super.enter();
        System.out.println("lets go to the toilet");
    }

    /**
     * this function gets called after the start and awake function but before the logic loop and leave function.
     * this function gets called every program cycle
     */
    @Override
    protected void checkForStateSwitch()
    {
        super.checkForStateSwitch();
        if(Student.getPeeValue()<= 0)
        {
            Student.setPeeValue(0);
            stateMachine.SetState(StateID.ExampleGoToLessonState);
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
            System.out.println("iam in or going to the toilet");
            timer = 0;
        }
        Student.setPeeValue(Student.getPeeValue() - stateMachine.getDeltaTime()*3);
    }

    /**
     * this function gets called when you leave this state and after all other functions
     */
    @Override
    protected void leave()
    {
        super.leave();
        System.out.println("going to 'going to lesson state'");
    }
}