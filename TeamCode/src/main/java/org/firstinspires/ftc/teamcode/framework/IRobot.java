package org.firstinspires.ftc.teamcode.framework;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IRobot {
    /**
     * Changes state to `Rotating`
     *
     * @param angle Angle in radians to rotate
     * @param power Power to apply to motor
     */
    void rotate(double angle, float power);

    /**
     * Currently a unimplemented
     *
     * @param distance the distance in mm that each wheel must turn
     * @param power
     */
    void move(Vector2D distance, float power);

    /**
     * @return returns true if any motors on robot are moving
     */
    boolean isBusy();

    /**
     * @return Returns the current state of the robot.
     */
    Robot.State getState();

    void updateTelemetryDisplay(Telemetry telemetry);

    /**
     * Try's to sync the actual rotation and theoretical rotation. This won't propagate error, as the it will contain the error, although maybe it will. We'll see.
     */
    public void correctRotation();

    enum State {
        Idle, Rotating, RotationCorrection, DoneRotating, Translating, DoneTranslating
    }
}
