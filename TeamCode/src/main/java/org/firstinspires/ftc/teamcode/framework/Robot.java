package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Robot implements IRobot {
    public static float wheelDiameter = 17.9999999941f; // in millimetres
    public static float circumscribedCircleDiameter = 72f; // in millimetres
    private final HardwareImpl hardwareImpl;
    public State state = State.Idle;


    public Robot(DcMotor _leftDrive, DcMotor _rightDrive, boolean _manualMode) {
        hardwareImpl = new HardwareImpl(_leftDrive, _rightDrive, _manualMode);
    }

    /**
     * @param velocity
     * @param omega
     * @return
     * @deprecated
     */
    Vector2D uniToDiff(float velocity, float omega) {
        // velocity = translational velocity (m/s)
        // omega = angular velocity (rad/s)

        float R = wheelDiameter / 2;
        float L = circumscribedCircleDiameter;

        double v_l = ((2.0 * velocity) - (omega * L)) / (2.0 * R);
        double v_r = ((2.0 * velocity) + (omega * L)) / (2.0 * R);

        // Velocities for each side
        return new Vector2D((float) v_l, (float) v_r);
    }

    @Override
    public void Rotate(float angle, float power) {
        // Get rotation for both side motors
        double wheelRotations = convertAngleToWheelRotations(angle);
        Vector2D rotation = new Vector2D(wheelRotations, -wheelRotations);

        DriveData task = new DriveData(rotation, new Vector2D(power));

        hardwareImpl.runTask(task);

        state = State.Rotating;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param angle In radians
     * @return Number of rotations
     */
    private double convertAngleToWheelRotations(float angle) {
        return (angle * circumscribedCircleDiameter / (wheelDiameter * 2 * Math.PI));
    }

    @Override
    public void Move(Vector2D distance, float power) {


        Vector2D numRotations = new Vector2D(distance.x / (wheelDiameter * Math.PI), distance.y / (wheelDiameter * Math.PI));

        DriveData task = new DriveData(numRotations, new Vector2D(power));

        hardwareImpl.runTask(task);

        state = State.Translating;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean IsBusy() {
        return hardwareImpl.leftDrive.isBusy() | hardwareImpl.rightDrive.isBusy();
    }

    @Override
    public State GetState() {
        if (state == State.Rotating && !IsBusy()) {
            state = State.DoneRotating;
        }
        if (state == State.Translating && !IsBusy()) {
            state = State.Idle;
        }
        return state;
    }

    @Override
    public void updateTelemetryDisplay(Telemetry telemetry) {
        telemetry.addData("Wheel diameter: ", "%.3f", wheelDiameter);
        telemetry.addData("| Diameter of circumscribed circle: ", "%.3f", circumscribedCircleDiameter);
    }
}
