package org.frc5687.powerup.prototyping;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.frc5687.powerup.prototyping.subsystems.DriveTrain;
import com.kauailabs.navx.*;
import org.frc5687.powerup.prototyping.utils.PDP;

public class Robot extends IterativeRobot  {

    public static Robot robot;
    public static OI oi;

    private Command autoCommand;

    public static DriveTrain driveTrain;
    public static AHRS ahrs;

    private PDP pdp;
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

        pdp = new PDP();
        oi = new OI();
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
        pdp.updateDashboard();
    }

}
