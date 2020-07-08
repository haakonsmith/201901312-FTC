package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Queue;


public class Robot {

    DcMotor leftDrive;
    DcMotor rightDrive;

    DcMotor middleTurnTable;
    Servo elbow;
    Servo wrist;
    Servo grabber;

    public boolean manualMode;

    Queue<RobotTask> tasks;
    RobotTask currentTask = null;

    public final float wheelRadius = 10; //in millimetres
    public final float baseLength = 10; //in millimetres
    
    public void setMotorPower(Vector2D power) {
        leftDrive.setPower(power.x);
        rightDrive.setPower(power.y);
    }

    public void setMotorPosition(Vector2D position) {
        leftDrive.setTargetPosition((int) Math.floor(position.x));
        rightDrive.setTargetPosition((int) Math.floor(position.y));

        // set motors to run to target encoder position and stop with brakes on.
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    Vector2D uniToDiff(float velocity, float omega) {
        // velocity = translational velocity (m/s)
        // omega = angular velocity (rad/s)

        float R = wheelRadius;
        float L = baseLength;

        double v_l = ((2.0 * velocity) - (omega * L)) / (2.0 * R);
        double v_r = ((2.0 * velocity) + (omega * L)) / (2.0 * R);

        return new Vector2D((float) v_l, (float)  v_r);
    }

    private void applyPhysicalChanges(RobotTask task) {
        setMotorPower(task.wheelPowerValues);
        setMotorPosition(task.wheelEncoderValues);
    }

    void nullifyTask() {
        if (leftDrive.isBusy() && rightDrive.isBusy()) {
            currentTask = null;
        }
    }

    public void update() {
        nullifyTask();

        if (currentTask != null) {
            return;
        }

        currentTask = tasks.remove();
        applyPhysicalChanges(currentTask);
    }

    public void submitTask(RobotTask task) {
        tasks.add(task);
    }

    public void rotate90() {
        submitTask(new RobotTask(uniToDiff(0, 1.57079f), new Vector2D(2.0)));
    }

    public Robot(DcMotor _leftDrive, DcMotor _rightDrive, boolean _manualMode) {
        leftDrive = _leftDrive;
        rightDrive = _rightDrive;

        manualMode = _manualMode;

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        if (manualMode) {
            return;
        }

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
