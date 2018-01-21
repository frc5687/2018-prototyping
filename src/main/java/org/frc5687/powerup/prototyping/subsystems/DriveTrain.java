package org.frc5687.powerup.prototyping.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.frc5687.powerup.prototyping.Constants;
import org.frc5687.powerup.prototyping.RobotMap;
import org.frc5687.powerup.prototyping.commands.DriveWith2Joysticks;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Baxter on 3/22/2017.
 */
public class DriveTrain extends Subsystem {

    private VictorSP leftFrontMotor;
    private VictorSP leftRearMotor;
    private VictorSP rightFrontMotor;
    private VictorSP rightRearMotor;

    private AnalogInput IRSensor;

    public DriveTrain() {
        leftFrontMotor = new VictorSP(RobotMap.DriveTrain.LEFT_FRONT_MOTOR);
        leftRearMotor = new VictorSP(RobotMap.DriveTrain.LEFT_REAR_MOTOR);
        rightFrontMotor = new VictorSP(RobotMap.DriveTrain.RIGHT_FRONT_MOTOR);
        rightRearMotor = new VictorSP(RobotMap.DriveTrain.RIGHT_REAR_MOTOR);
        IRSensor = new AnalogInput(RobotMap.DriveTrain.IR_SENSOR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWith2Joysticks());
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftFrontMotor.set(leftSpeed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1));
        leftRearMotor.set(leftSpeed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1));
        rightFrontMotor.set(rightSpeed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1));
        rightRearMotor.set(rightSpeed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1));
    }
    public void updateDashboard(){
        SmartDashboard.putNumber("IR raw",IRSensor.getValue());
    }

}
