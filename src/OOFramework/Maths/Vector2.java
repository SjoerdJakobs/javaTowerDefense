package OOFramework.Maths;

public final class Vector2
{
    public double x;
    public double y;

    public Vector2() {
        this.x = 0.0d;
        this.y = 0.0d;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 Add(Vector2 v)
    {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public static Vector2 Add(Vector2 v1, Vector2 v2)
    {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2 MultiplyByInt(int m)
    {
        return new Vector2(this.x*m,this.y*m);
    }

    public static Vector2 MultiplyByInt(Vector2 v, int m)
    {
        return new Vector2(v.x*m,v.y*m);
    }

    public Vector2 MultiplyByDouble(double m)
    {
        return new Vector2(this.x*m,this.y*m);
    }

    public static Vector2 MultiplyByDouble(Vector2 v, double m)
    {
        return new Vector2(v.x*m,v.y*m);
    }

    public final boolean IsZero()
    {
        return (this.x == 0 && this.y == 0);
    }

    public final static boolean IsZero(Vector2 v)
    {
        return (v.x == 0 && v.y == 0);
    }

    public final boolean Equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public final static boolean Equals(Vector2 v1, Vector2 v2) {
        return (v1.x == v2.x && v1.y == v2.y);
    }

    public final double Distance(Vector2 other) {
        double xDist = other.x - this.x;
        double yDist = other.y - this.y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }

    public final static double Distance(Vector2 vec1, Vector2 vec2) {
        double xDist = vec2.x - vec1.x;
        double yDist = vec2.y - vec1.y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
}
