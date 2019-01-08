/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.driveWithJoysticks;

//import static org.junit.Assume.assumeNoException;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class driveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final int KDriveRightTopTalon = 1; 
  public static final int KDriveLeftTopTalon = 2; 
  public static final int KDriveRightFrontTalon = 3; 
  public static final int KDriveLeftFrontTalon = 4; 
  public static final int KDriveRightRearTalon = 5; 
  public static final int KDriveLeftRearTalon = 6; 
  public static final double KDeadZone = 0.2; 

  public static final int KShifterSolenoid1 = 1;
  public static final int KShifterSolenoid2 = 2; 

  private TalonSRX driveRightTop;
  private TalonSRX driveLeftTop;  
  private TalonSRX driveRightFront; 
  private TalonSRX driveLeftFront;
  private TalonSRX driveRightRear; 
  private TalonSRX driveLeftRear;

  private DoubleSolenoid shifterSolenoid1; 
  private DoubleSolenoid shifterSolenoid2; 

  public driveSubsystem()
  {
    driveRightTop = new TalonSRX(KDriveRightTopTalon);
    driveLeftTop = new TalonSRX(KDriveLeftTopTalon);
    driveRightFront = new TalonSRX(KDriveRightFrontTalon); 
    driveLeftFront = new TalonSRX(KDriveLeftFrontTalon);
    driveRightRear = new TalonSRX(KDriveRightRearTalon);
    driveLeftRear = new TalonSRX(KDriveLeftRearTalon);

    shifterSolenoid = new DoubleSolenoid(KShifterSolenoid1, KShifterSolenoid2);

    driveRightTop.set(ControlMode.Follower, driveRightFront.getDeviceID());
    driveRightRear.set(ControlMode.Follower, driveRightFront.getDeviceID());
    driveLeftTop.set(ControlMode.Follower, driveLeftFront.getDeviceID());
    driveLeftRear.set(ControlMode.Follower, driveLeftFront.getDeviceID());

  }

  @Override
  public void initDefaultCommand() {
    //default command for a subsystem here.
   setDefaultCommand(new driveWithJoysticks());
  }

  public void tankDrive(double rightSpeed, double leftSpeed) {
    if(KDeadZone < rightSpeed || KDeadZone > -rightSpeed) {
      driveRightFront.set(ControlMode.PercentOutput, rightSpeed);
    }
    else {
      driveLeftFront.set(ControlMode.PercentOutput, 0);
    }
    if (KDeadZone < leftSpeed || KDeadZone > -leftSpeed) {
      driveLeftFront.set(ControlMode.PercentOutput, leftSpeed);
    }
    else {
      driveLeftFront.set(ControlMode.PercentOutput, 0);
    }
  }

	public void highShiftBase() {
    shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  
	public void lowShiftBase() {
    shifterSolenoid.set(DoubleSolenoid.Value.kForward);
  }

	public void toggleShift() {
		if (shifterSolenoid.get() == DoubleSolenoid.Value.kForward) {
			highShiftBase();
		}
		else {
			lowShiftBase();
		}
  }
}
