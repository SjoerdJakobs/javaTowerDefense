package OOFramework.Collision2D;

import OOFramework.Debug.DebugDrawer;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FrameworkProgram;
import OOFramework.Modules.ASSERT_MSG;
import OOFramework.StandardObject;

import java.util.ArrayList;

public class CollisionSystem extends StandardObject
{
    //this should ALWAYS be 0 or 1
    public static int amountOfInstances = 0;
    private static CollisionSystem instance = null;

    public static CollisionSystem getInstance()
    {
        return instance;
    }

    public ArrayList<Collider2D> allColliders;

    public CollisionSystem(FrameworkProgram frameworkProgram)
    {
        super(frameworkProgram, false, true, false, true, 1000, 1_000_000);
        instance = this;
        amountOfInstances ++;
        ASSERT_MSG.ASSERT_MSG_TERMINATE(!(amountOfInstances <= 1),"MULTIPLE COLLISION SYSTEMS HAVE BEEN MADE");
        allColliders = new ArrayList<Collider2D>();

    }

    @Override
    protected void Start()
    {
        super.Start();
    }

    @Override
    protected void MainLoop(double deltaTime)
    {
        super.MainLoop(deltaTime);
        boolean hasCollided;
        for (int i = 0; i < allColliders.size(); i++) {
            hasCollided = false;
            for (int j = 0; j < allColliders.size(); j++) {
                if(i != j)
                {
                    if(allColliders.get(i).Collide(allColliders.get(j).getColliderType(), allColliders.get(j)))
                    {
                        hasCollided = true;
                        allColliders.get(i).getCollisionCallback().collide(allColliders.get(j));
                        allColliders.get(j).getCollisionCallback().collide(allColliders.get(i));
                    }
                }
            }
            allColliders.get(i).setIsColliding(hasCollided);
        }
        DebugDrawer.DebugCollision(allColliders);
    }
}
