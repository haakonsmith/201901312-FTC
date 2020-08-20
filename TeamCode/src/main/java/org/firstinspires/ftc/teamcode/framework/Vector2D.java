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

    /**
     * Non mutating
     *
     * @return result
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(other.x - x, other.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    /**
     * Non mutating
     *
     * @return result
     */
    public double distanceTo(Vector2D other) {
//        return Math.sqrt(other.subtract(this).square().sum());
        return Math.sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y));
    }

    /**
     * Non mutating
     *
     * @return result
     */
    public Vector2D multiply(Vector2D other) {
        return new Vector2D(x * other.x, y * other.y);
    }

    /**
     * Non mutating
     *
     * @return sum of elements
     */
    public double sum() {
        return x + y;
    }

    /**
     * Non mutating
     *
     * @return result
     */
    public Vector2D square() {
        return multiply(this);
    }

    /**
     * Non mutating
     *
     * @return result
     */
    public double angleBetween(Vector2D other) {
        Vector2D translatedVector = this.subtract(other);

        return Math.atan2(translatedVector.x, translatedVector.y);
    }
}
