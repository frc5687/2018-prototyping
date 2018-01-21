package org.frc5687.powerup.prototyping;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.powerup.prototyping.subsystems.DriveTrain;
import org.frc5687.powerup.prototyping.utils.JeVoisProxy;

public class Robot extends IterativeRobot  {

    public static Robot robot;
    public static OI oi;

    private Command autoCommand;

    public static DriveTrain driveTrain;
    public static AHRS ahrs;
    public static JeVoisProxy jeVoisProxy;

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
        jeVoisProxy = new JeVoisProxy();
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
        SmartDashboard.putNumber("JeVois/x", jeVoisProxy.GetX());
        SmartDashboard.putNumber("JeVois/y", jeVoisProxy.GetY());
    }

}
