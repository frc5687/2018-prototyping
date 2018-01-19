package org.frc5687.powerup.prototyping;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.frc5687.powerup.prototyping.subsystems.DriveTrain;
import com.kauailabs.navx.*;

public class Robot extends IterativeRobot  {

    public static Robot robot;
    public static OI oi;

    private Command autoCommand;

    public static DriveTrain driveTrain;
    public static AHRS ahrs;
    public static SerialPort serialPort;

    public Robot() {
    }

    @Override
    public void startCompetition() {
        super.startCompetition();
    }

    @Override
    public void robotInit() {
        robot = this;

        ahrs = new AHRS(SPI.Port.kMXP);
        driveTrain = new DriveTrain();

        oi = new OI();

        serialPort = new SerialPort(115200, SerialPort.Port.kMXP);
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {
        if (autoCommand != null) {
            autoCommand.start();
        }
    }

    @Override
    public void teleopInit() {
        if (autoCommand != null) autoCommand.cancel();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void robotPeriodic() {
        String stringInBuffer = serialPort.readString();
        if (stringInBuffer.length() > 0) {
            DriverStation.reportError(stringInBuffer, true);
        }
        updateDashboard();
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
    }

    public void updateDashboard() {
    }

}
