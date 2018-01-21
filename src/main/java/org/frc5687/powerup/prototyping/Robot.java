package org.frc5687.powerup.prototyping;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.powerup.prototyping.subsystems.DriveTrain;
import com.kauailabs.navx.*;
import org.frc5687.powerup.prototyping.utils.JeVoisProxy;
import org.json.*;

public class Robot extends IterativeRobot  {

    public static Robot robot;
    public static OI oi;

    private Command autoCommand;

    public static DriveTrain driveTrain;
    public static AHRS ahrs;
    //public static SerialPort serialPort;
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

        //serialPort = new SerialPort(115200, SerialPort.Port.kMXP);
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
        /*
        String stringInBuffer = serialPort.readString();
        if (stringInBuffer.length() > 15) {
            if (stringInBuffer.substring(0, 13).equals("JeVoisTargets")) {
                String json_string = stringInBuffer.substring(15, stringInBuffer.length());
                // JSONObject obj = new JSONObject(json_string);
                // double aX = obj.getDouble("aX");
                //SmartDashboard.putNumber("JeVois/aX", aX);
                SmartDashboard.putString("JeVois/json", json_string);
            }
            DriverStation.reportError(stringInBuffer, true);
        }*/
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
        //SmartDashboard.putNumber("JeVois/x", jeVoisProxy.GetX());
        //SmartDashboard.putNumber("JeVois/y", jeVoisProxy.GetY());
    }

}
