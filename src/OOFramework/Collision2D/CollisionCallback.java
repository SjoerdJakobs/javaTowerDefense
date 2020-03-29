package OOFramework.Collision2D;

import OOFramework.Collision2D.Colliders.Collider2D;

public interface CollisionCallback
{
    void collide(Collider2D other);
}
