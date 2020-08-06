package org.firstinspires.ftc.teamcode.framework;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Robot interface used from TaskManager
 * Do not interface with Hardware implementation,
 * that way we can separate the logic for driving commands between the math and the actual high level input
 */
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
    void move(double distance, float power);

    /**
     * @return returns true if any motors on robot are moving
     */
    boolean isBusy();

    /**
     * @return Returns the current state of the robot.
     */
    Robot.State getState();

    /**
     * @param telemetry
     */
    void updateTelemetryDisplay(Telemetry telemetry);

    /**
     * Try's to sync the actual rotation and theoretical rotation. This won't propagate error, as the it will contain the error, although maybe it will. We'll see.
     */
    void correctRotation();

    enum State {
        Idle, Rotating, RotationCorrection, DoneRotating, Translating, DoneTranslating
    }

    public Vector2D getPosition();
    public double getRotation();

    /**
     * Shortest distance (angular) between two angles.
     * It will be in range [0, Math.PI].
     */
    public static double distanceBetweenAngles(double alpha, double beta) {
        double phi = Math.abs(beta - alpha) % (Math.PI * 2);       // This is either the distance or Math.PI * 2 - distance
        double distance = phi > Math.PI ? (Math.PI * 2) - phi : phi;
        int sign = (alpha - beta >= 0 && alpha - beta <= Math.PI) || (alpha - beta <= -Math.PI && alpha - beta >= -(Math.PI * 2)) ? 1 : -1;
        return distance * sign;
    }

}
