package org.frc5687.powerup.prototyping;

import edu.wpi.first.wpilibj.SerialPort;

public class RobotMap {

    public class DriveTrain {
        public static final int LEFT_FRONT_MOTOR = 0;
        public static final int LEFT_REAR_MOTOR = 1;
        public static final int RIGHT_FRONT_MOTOR = 3;
        public static final int RIGHT_REAR_MOTOR = 4;
    }

    public class Climber {
        public static final int MOTOR_A = 6;
        public static final int MOTOR_B = 7;
    }

    public static class JeVois {
        public static final SerialPort.Port PORT = SerialPort.Port.kMXP;
    }

}
