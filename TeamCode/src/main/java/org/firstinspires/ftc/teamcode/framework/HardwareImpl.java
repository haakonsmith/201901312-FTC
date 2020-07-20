package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class HardwareImpl {
    DcMotor leftDrive;
    DcMotor rightDrive;

    public GyroSensor gyroscope;

    public static double GEAR_RATIO = 1.0; // for simulator - ours should be 0.5f;
    public static double WHEEL_RADIUS = 5.0;  // 5 cm
    public static double TICKS_PER_ROTATION = 1120.0;  // From NeveRest (for simulator)  GoBilda should be 383.6f

    public static double CM_PER_TICK = (2 * Math.PI * GEAR_RATIO * WHEEL_RADIUS) / TICKS_PER_ROTATION;

    public boolean manualMode;
    
    public HardwareImpl(DcMotor _leftDrive, DcMotor _rightDrive, boolean _manualMode) {
        leftDrive = _leftDrive;
        rightDrive = _rightDrive;

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if (manualMode) {
            return;
        }

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
    }

    private void setMotorPosition(Vector2D position) {
        leftDrive.setTargetPosition((int) (Math.floor(position.x * TICKS_PER_ROTATION)));
        rightDrive.setTargetPosition((int) (Math.floor(position.y * TICKS_PER_ROTATION)));

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void setMotorPower(Vector2D power) {
        leftDrive.setPower(power.x);
        rightDrive.setPower(power.y);
    }

    public void runTask(DriveData task) {
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setMotorPosition(task.wheelEncoderValues);
        setMotorPower(task.wheelPowerValues);
    }

    /**
     * Starts calibrating...
     * Check state to see if finished calibrating.
     */
    public void calibrate() {

    }

    enum State {
        Calibrating, Ready
    }
}
