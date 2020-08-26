package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.framework.HardwareImpl;
import org.firstinspires.ftc.teamcode.framework.Vector2D;


/**
 * 1:1 linear remote control uses linear mapping and one stick for each side
 */
@TeleOp(name="Linear Pov Remote Control", group="Linear Opmode")
//@Disabled
public class RobotLinearPovTankControl extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        HardwareImpl hardware = new HardwareImpl(
                hardwareMap.get(DcMotor.class, "left_back_motor"),
                hardwareMap.get(DcMotor.class, "right_back_motor"), true);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double drive = -Range.clip(gamepad1.left_stick_y, -1.0, 1.0);
            double turn  =  Range.clip(gamepad1.left_stick_x, -1.0, 1.0);

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double leftPower    = drive + turn;
            double rightPower   = drive - turn;

            Vector2D motorPower = new Vector2D(leftPower, rightPower);

            hardware.setMotorPower(motorPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Sticks", "left stick: (%.2f), right stick (%.2f)", gamepad1.left_stick_x, gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}
