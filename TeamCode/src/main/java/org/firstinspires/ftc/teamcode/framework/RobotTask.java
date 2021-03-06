package org.firstinspires.ftc.teamcode.framework;

/**
 * Contains data required for the robots movement.
 */
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
