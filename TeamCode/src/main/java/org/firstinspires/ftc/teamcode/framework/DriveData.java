package org.firstinspires.ftc.teamcode.framework;

class DriveData {
//    x is left, y is right
    Vector2D wheelEncoderValues;
    Vector2D wheelPowerValues;

    public DriveData(Vector2D _wheelEncoderValues, Vector2D _wheelPowerValues) {
        wheelEncoderValues = _wheelEncoderValues;
        wheelPowerValues = _wheelPowerValues;
    }
}
