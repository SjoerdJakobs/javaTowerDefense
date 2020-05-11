package OOFramework.Maths;

public final class Vector3
{
    public double x;
    public double z;
    public double y;

    public Vector3() {
        this.x = 0.0d;
        this.z = 0.0d;
        this.y = 0.0d;
    }

    public Vector3(double x, double z, double y) {
        this.x = x;
        this.z = z;
        this.y = y;
    }

    public Vector3(Vector2 v) {
        this.x = v.x;
        this.z = 0;
        this.y = v.y;
    }

    public Vector3(Vector3 v) {
        this.x = v.x;
        this.z = v.z;
        this.y = v.y;
    }
}
