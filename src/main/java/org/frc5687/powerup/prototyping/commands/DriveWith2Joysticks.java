package org.frc5687.powerup.prototyping.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.powerup.prototyping.Robot.driveTrain;
import static org.frc5687.powerup.prototyping.Robot.oi;

/**
 * Command for controlling each side of the drive train with a joystick
 */
public class DriveWith2Joysticks extends Command {

    public DriveWith2Joysticks() {
        requires(driveTrain);
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
