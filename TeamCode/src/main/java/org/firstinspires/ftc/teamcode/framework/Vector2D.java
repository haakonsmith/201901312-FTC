package org.firstinspires.ftc.teamcode.framework;

public class Vector2D {
    float x;
    float y;

    public Vector2D(float _x, float _y) {
        x = _x;
        y = _y;
    }

    public Vector2D(double _x, double _y) {
        x = (float) _x;
        y = (float) _y;
    }

    public Vector2D(double _z) {
        x = (float) _z;
        y = (float) _z;
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(other.x - x, other.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    public double distanceTo(Vector2D other) {
        return Math.sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y));
    }

    public double angleBetween(Vector2D other) {
        Vector2D translatedVector = this.subtract(other);

        return Math.atan2(translatedVector.x, translatedVector.y);
    }
}
