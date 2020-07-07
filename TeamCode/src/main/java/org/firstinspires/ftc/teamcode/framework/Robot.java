package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.R;

public class Robot {

    DcMotor leftDrive;
    DcMotor rightDrive;

    DcMotor middleTurnTable;
    Servo elbow;
    Servo wrist;
    Servo grabber;
    
    public void setWheelDrive(Vector2D power) {
        
    }

    // generate and send the correct commands to the robot
    void sendRobotCommands() {

        Vector2D velocity = uniToDiff(float v, float omega);

        setWheelDrive( velocity );
    }

    void uniToDiff(float velocity, float omega):
        // v = translational velocity (m/s)
        // omega = angular velocity (rad/s)

        R = self.robot_wheel_radius;
        L = self.robot_wheel_base_length;

        v_l = ( (2.0 * v) - (omega*L) ) / (2.0 * R);
        v_r = ( (2.0 * v) + (omega*L) ) / (2.0 * R);

        return v_l, v_r;
}
