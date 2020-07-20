package org.firstinspires.ftc.teamcode.framework;

public class RobotTask {
    Vector2D relativePosition;
    double rotation;

    public RobotTask(Vector2D _relativePosition, double _rotation) {
        rotation = _rotation;
        relativePosition = _relativePosition;
    }

    @Override
    public String toString() {
        return "{" + relativePosition.toString() + ", " + rotation + '}';
    }
}
