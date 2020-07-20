package org.firstinspires.ftc.teamcode.framework;

public class RobotTask {
    Vector2D relativePosition;
    float rotation;

    public RobotTask(Vector2D _relativePosition, float _rotation) {
        rotation = _rotation;
        relativePosition = _relativePosition;
    }

    @Override
    public String toString() {
        return "{" + relativePosition.toString() + ", " + rotation + '}';
    }
}
