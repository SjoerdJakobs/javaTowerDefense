package OOFramework.Modules;

public final class CONSTANTS {
    public static final String TITLE = "SPHERE DEFENSE";        // title of the game
    public static final int CANVAS_WIDTH = 1920;    // width and height of the drawing canvas
    public static final int CANVAS_HEIGHT = 1080;
    public static final int UPDATES_PER_SEC = 144;  // number of game update per second(not used atm)
    public static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds

    public static final String STANDARD_SAVE_FILE_PATH = "OOFSaveFile";
    public static final String STANDARD_SOUND_FILENAME_PREFIX = "sounds/";
    public static final String STANDARD_IMAGE_FILENAME_PREFIX = "/images/";

    public static final boolean DEBUG_MODE = false;
    public static final boolean DEBUG_FPS  = true;
    public static final boolean DEBUG_BFS  = false;
    public static final boolean DEBUG_COLLISION = true;
    public static final boolean DEBUG_ASTAR = false;
}
