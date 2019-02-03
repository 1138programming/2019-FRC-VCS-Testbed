/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class EncoderTest extends Command {
  public EncoderTest() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.DRIVE_SUBSYSTEM);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.DRIVE_SUBSYSTEM.getBaseLeftFront().getSensorCollection().setQuadraturePosition(0, 10);
    Robot.DRIVE_SUBSYSTEM.getBaseRightFront().getSensorCollection().setQuadraturePosition(0, 10);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.DRIVE_SUBSYSTEM.tankDrive(0.1, 0.1);
    SmartDashboard.putNumber("Left quadrature position:", Robot.DRIVE_SUBSYSTEM.getBaseLeftFront().getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("Right quadrature position:", Robot.DRIVE_SUBSYSTEM.getBaseRightFront().getSensorCollection().getQuadraturePosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.DRIVE_SUBSYSTEM.getBaseLeftFront().getSensorCollection().getQuadraturePosition() <= -4096 * 4.17;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DRIVE_SUBSYSTEM.tankDrive(0, 0);
    Robot.DRIVE_SUBSYSTEM.getBaseLeftFront().getSensorCollection().setQuadraturePosition(0, 10);
    Robot.DRIVE_SUBSYSTEM.getBaseRightFront().getSensorCollection().setQuadraturePosition(0, 10);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
