package org.firstinspires.ftc.teamcode.framework;

/**
 * Stores the values needed to complete a movement
 */
class DriveData {
    // x is left, y is right
    // How many ticks needed to complete the movement
    Vector2D wheelEncoderValues;
    // How fast the movement is completed
    Vector2D wheelPowerValues;

    public DriveData(Vector2D _wheelEncoderValues, Vector2D _wheelPowerValues) {
        wheelEncoderValues = _wheelEncoderValues;
        wheelPowerValues = _wheelPowerValues;
    }
}
