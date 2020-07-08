package org.firstinspires.ftc.teamcode.framework;

public class Vector2D {
    float x;
    float y;

    public Vector2D(float _x, float _y) {
        x = _x; y = _y;
    }

    public Vector2D(double _x, double _y) {
        x = (float) _x; y = (float) _y;
    }

    public Vector2D(double _z) {
        x = (float) _z; y = (float) _z;
    }

}
