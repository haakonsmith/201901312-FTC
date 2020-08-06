package org.firstinspires.ftc.teamcode.framework;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Current implementation for working robot
 */
public class Robot implements IRobot {
    public static double WHEELDIAMETER = 17.9999999941f; // in millimetres
    public static double CIRCUMSCRIBEDCIRCLEDIAMETER = 72f; // in millimetres
    public static int CORRECTIONS = 2;
    public static int DIGITSOFCORRECTION = 1000;
    public static double GYROFFSET = 0.00;
    private final HardwareImpl hardwareImpl;
    public State state = State.Idle;

    public double rotation = 0; // This represents the theoretical rotation that the robot thinks it's at
    public double physicalReferenceRotation = 0;// // This represents the physical reference rotation that the robot's gyroscope thinks it's at. Doesn't change. It's the zero rotation
    public Vector2D position = new Vector2D(0); // This represents the theoretical position that the robot thinks it's at


    public Robot(HardwareImpl _hardwareImpl) {
        hardwareImpl = _hardwareImpl;
        physicalReferenceRotation = hardwareImpl.getHeading() + GYROFFSET;
    }

    /**
     * Take rotation in radians to convert to wheel rotations using diameter and such
     *
     * @param angle In radians
     * @return Number of rotations
     */
    private static double convertAngleToWheelRotations(double angle) {
        return (angle * CIRCUMSCRIBEDCIRCLEDIAMETER / (WHEELDIAMETER * 2 * Math.PI));
    }

    /**
     * Normalizes any number to an arbitrary range
     * by assuming the range wraps around when going below min or above max
     *
     * @param value The actual value
     * @param start Min
     * @param end   Max
     * @return Returns normalised angle
     */
    public static double normalise(double value, double start, double end) {
        double width = end - start;   //
        double offsetValue = value - start;   // value relative to 0

        return (offsetValue - (Math.floor(offsetValue / width) * width)) + start;
        // + start to reset back to start of original range
    }



    /**
     * @inheritDoc
     */
    @Override
    public void rotate(double angle, float power) {
        rotation += angle;
        rotation = normalise(rotation, 0, Math.PI * 2);

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
     * @inheritDoc
     */
    @Override
    public void move(double distance, float power) {
        double numRotations = -(distance / (WHEELDIAMETER * Math.PI));

        DriveData task = new DriveData(new Vector2D(numRotations), new Vector2D(power));

        hardwareImpl.runTask(task);

        state = State.Translating;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBusy() {
        return hardwareImpl.isBusy();
    }

    /**
     * @inheritDoc
     */
    @Override
    public State getState() {
        if (state == State.Rotating && !isBusy()) {
            state = State.RotationCorrection;
            return state;
        }
        if (state == State.RotationCorrection && !isBusy()) {
            state = State.DoneRotating;
            return state;
        }
        if (state == State.Translating && !isBusy()) {
            state = State.Idle;
        }
        return state;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateTelemetryDisplay(Telemetry telemetry) {
        telemetry.addData("Wheel diameter: ", "%.3f", WHEELDIAMETER);
        telemetry.addData("D of circumscribed circle: ", "%.3f", CIRCUMSCRIBEDCIRCLEDIAMETER);
        telemetry.addData("Rotation: ", "%.3f", Math.toDegrees(rotation));
    }

    /**
     * @inheritDoc
     */
    public void correctRotation() {

        for (int i = 0; i <= CORRECTIONS; i++) {
            // corrected actual rotation
            double actualRotation = (hardwareImpl.getHeading() - physicalReferenceRotation);

            actualRotation = normalise(actualRotation, 0, Math.PI * 2);

            double correctionAngle = IRobot.distanceBetweenAngles(actualRotation, rotation);
//            double si = Math.signum(correctionAngle);

            if (Math.abs(correctionAngle) > 1 / (double) DIGITSOFCORRECTION) {
                rotate((float) (Math.floor(correctionAngle * DIGITSOFCORRECTION) / DIGITSOFCORRECTION), 1f);
            } else {
                break;
            }

        }

        state = State.DoneRotating;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public double getRotation() {
        return rotation;
    }
}
