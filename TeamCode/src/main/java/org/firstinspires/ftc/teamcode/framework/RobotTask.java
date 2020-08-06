package org.firstinspires.ftc.teamcode.framework;

public class RobotTask {
    double relativeDistance;
    double rotation;

    public RobotTask(double _relativePosition, double _rotation) {
        rotation = _rotation;
        relativeDistance = _relativePosition;
    }

    @Override
    public String toString() {
        return "{" + relativeDistance + ", " + rotation + '}';
    }
}
