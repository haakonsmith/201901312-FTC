package org.firstinspires.ftc.teamcode.framework;

public final class AdvMath {
    public static double trunc(double a) {
        if (a < 0) {
            return Math.ceil(a);
        }
        if (a > 0) {
            return Math.floor(a);
        }
        else {
            return a;
        }
    }

    /**
     * Shortest distance (angular) between two angles.
     * It will be in range [0, Math.PI].
     */
    public static double distanceBetweenAngles(double alpha, double beta) {
        double phi = Math.abs(beta - alpha) % (Math.PI * 2);       // This is either the distance or Math.PI * 2 - distance
        double distance = phi > Math.PI ? (Math.PI * 2) - phi : phi;
        int sign = (alpha - beta >= 0 && alpha - beta <= Math.PI) || (alpha - beta <= -Math.PI && alpha - beta >= -(Math.PI * 2)) ? 1 : -1;
        return distance * sign;
    }
}
