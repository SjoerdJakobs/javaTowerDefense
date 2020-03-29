package OOFramework.Modules;

public final class ASSERT_MSG
{
    //if this then write message
    public static final void ASSERT_MSG(boolean condition, String msg)
    {
        if (CONSTANTS.DEBUG_MODE) {
            if (condition) {
                System.out.println(msg);
            }
        }
    }

    //if this then write message and stop program
    public static final void ASSERT_MSG_TERMINATE(boolean condition, String msg)
    {
        if(CONSTANTS.DEBUG_MODE) {
            if (condition) {
                System.out.println(msg);
                System.exit(0);
            }
        }
    }
}
