package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.HashMap;
import java.util.Map;

public abstract class DriveTrain {
    private final Map<String, DcMotor> motorMap = new HashMap<String, DcMotor>();

    public void setTargetPositions(Map<String, Integer> motorPositions) {
        for (String motorName : motorPositions.keySet()) {
            motorMap.get(motorName).setTargetPosition(motorPositions.get(motorName));
        }
    }
}
