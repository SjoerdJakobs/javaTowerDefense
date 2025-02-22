package OOFramework.ExampleClasses;

import OOFramework.FrameworkProgram;
import javafx.scene.input.KeyEvent;

public class ExampleProgram extends FrameworkProgram {
    /**
     * happens at: when this object is made
     * how often: once
     * execution order: 0
     */
    ExampleProgram() {

    }

    /**
     * happens at: when this object is made
     * how often: once
     * execution order: 1
     */
    @Override
    protected void Init() {
        super.Init();

        System.out.println("Hello World!");


    }

    /**
     * happens at: when this object is made
     * how often: every program loop
     * execution order: 2
     * <p>
     * NOTE: everything put here happens before anything that happens in the update loops of the objects
     */
    @Override
    protected void AddToLoop() {
        super.AddToLoop();

    }

    /**
     * happens at: when the program is exited(when you stop running)
     * how often: once
     * execution order: 3
     * <p>
     * NOTE: this is the very last thing you do before you exit the program.
     */
    @Override
    public void ExitProgram() {
        super.ExitProgram();
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
