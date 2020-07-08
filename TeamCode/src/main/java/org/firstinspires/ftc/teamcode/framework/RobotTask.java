package org.firstinspires.ftc.teamcode.framework;

class RobotTask {
    Vector2D wheelEncoderValues;
    Vector2D wheelPowerValues;

    public RobotTask(Vector2D _wheelEncoderValues, Vector2D _wheelPowerValues) {
        wheelEncoderValues = _wheelEncoderValues;
        wheelPowerValues = _wheelPowerValues;
    }
}
