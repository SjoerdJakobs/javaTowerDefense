package OOFramework.Collision2D.Colliders;

import OOFramework.Collision2D.Enums.ColliderType;
import OOFramework.Maths.Vector2;

public class CircleCollider extends Collider2D {
    public double radius;

    public CircleCollider(Vector2 pos, double radius) {
        super();
        this.radius = radius;
        this.pos = pos;
        this.colliderType = ColliderType.CIRCLE_COLLIDER;
        preCollisionCallback = this::PreCollisons;
        collisionCallback = this::UpdateCollisons;
    }


    //the check for collision call
    @Override
    public boolean Collide(ColliderType collideWith, Collider2D other) {
        switch (collideWith) {
            case CIRCLE_COLLIDER:
                return CircleCollision((CircleCollider) other);
            case BOX_COLLIDER:
                return BoxCollision((BoxCollider) other);
            case LINE_COLLIDER:
                return false;
            case DEFAULT:
                return false;
            default:
                return false;

        }
    }


    @Override
    public boolean CircleCollision(CircleCollider other) {
        return ((this.radius + other.radius) > this.pos.Distance(other.pos));
    }

    @Override
    public boolean BoxCollision(BoxCollider other) {
        double circleDistanceX = Math.abs(this.pos.x - other.pos.x);
        double circleDistanceY = Math.abs(this.pos.y - other.pos.y);

        if (circleDistanceX > (other.width / 2 + this.radius)) {
            return false;
        }
        if (circleDistanceY > (other.height / 2 + this.radius)) {
            return false;
        }

        if (circleDistanceX <= (other.width / 2)) {
            return true;
        }
        if (circleDistanceY <= (other.height / 2)) {
            return true;
        }

        double cornerDistance_sq = Math.pow((circleDistanceX - other.width / 2), 2) +
                Math.pow((circleDistanceY - other.height / 2), 2);

        return (cornerDistance_sq <= (this.radius * this.radius));
    }

    @Override
    public boolean RayIntersection(Object collider) {
        return false;
    }

    @Override
    public boolean ContainsPoint(Vector2 point) {
        return (this.pos.Distance(point) < this.radius);
    }

    @Override
    public void PreCollisons() {

    }

    @Override
    public void UpdateCollisons(Collider2D other) {

    }
}
