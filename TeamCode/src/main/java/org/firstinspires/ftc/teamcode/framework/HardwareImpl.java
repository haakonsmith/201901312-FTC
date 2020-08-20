package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This is the only class that can issues commands to the robot
 */
public class HardwareImpl {
    public final DcMotor leftBackDrive;
    public final DcMotor rightBackDrive;
//    public final DcMotor leftFrontDrive;
//    public final DcMotor rightFrontDrive;

//    private final GyroSensor gyroscope;

    public static double GEAR_RATIO = 1.0; // for simulator - ours should be 0.5f;
    public static double WHEEL_RADIUS = 5.0;  // 5 cm
    public static double TICKS_PER_ROTATION = 1120.0;  // From NeveRest (for simulator)  GoBilda should be 383.6f

    public static double CM_PER_TICK = (2 * Math.PI * GEAR_RATIO * WHEEL_RADIUS) / TICKS_PER_ROTATION;

    public boolean manualMode;
    
    public HardwareImpl(DcMotor leftBackDrive, DcMotor rightBackDrive, boolean manualMode) {
        this.leftBackDrive = leftBackDrive;
        this.rightBackDrive = rightBackDrive;
        this.manualMode = manualMode;

//        gyroscope = _gyroscope;
//        gyroscope.init();

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if (!manualMode) {
            leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else {
            leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        
    }

    private void setMotorPosition(Vector2D position) {
        leftBackDrive.setTargetPosition((int) (AdvMath.trunc(position.x * TICKS_PER_ROTATION)));
        rightBackDrive.setTargetPosition((int) (AdvMath.trunc(position.y * TICKS_PER_ROTATION)));

        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setMotorPower(Vector2D power) {
        leftBackDrive.setPower(power.x);
        rightBackDrive.setPower(power.y);
    }

    public void runTask(DriveData task) {
        if (!manualMode) {
            leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            setMotorPosition(task.wheelEncoderValues);
            setMotorPower(task.wheelPowerValues);
        }
    }

    public boolean isBusy() {
        return leftBackDrive.isBusy() | rightBackDrive.isBusy();
    }

    /**
     * @return Returns heading from when last calibrated in radians
     */
    public double getHeading() {
        return Math.toRadians(0);
    }

    /**
     * Starts calibrating...
     * Check state to see if finished calibrating.
     */
    public void calibrate() {
//        gyroscope.calibrate();
    }
}
