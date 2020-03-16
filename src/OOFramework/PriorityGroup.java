package OOFramework;

import java.util.ArrayList;

public final class PriorityGroup
{
    public boolean shouldUse;
    public int priorityNr;
    public ArrayList<StandardObject> standardObjects;

    public PriorityGroup(int priorityNr)
    {
        this.priorityNr = priorityNr;
        this.shouldUse = true;
        standardObjects = new ArrayList<StandardObject>();
    }

    //for comparing when sorting list
    public int getPriorityNr() {
        return priorityNr;
    }
}
