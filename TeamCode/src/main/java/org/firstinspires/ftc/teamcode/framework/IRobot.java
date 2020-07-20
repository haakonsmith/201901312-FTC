package org.firstinspires.ftc.teamcode.framework;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IRobot {
    /**
     * Changes state to `Rotating`
     *
     * @param angle Angle in radians to rotate
     * @param power Power to apply to motor
     */
    void Rotate(float angle, float power);

    /**
     * Currently a unimplemented
     *
     * @param distance the distance in mm that each wheel must turn
     * @param power
     */
    void Move(Vector2D distance, float power);

    /**
     * @return returns true if any motors on robot are moving
     */
    boolean IsBusy();

    /**
     * @return Returns the current state of the robot.
     */
    Robot.State GetState();

    void updateTelemetryDisplay(Telemetry telemetry);

    enum State {
        Idle, Rotating, DoneRotating, Translating
    }
}
