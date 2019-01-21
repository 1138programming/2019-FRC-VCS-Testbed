/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithJoysticks;

//import static org.junit.Assume.assumeNoException;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final int KDriveRightTopTalon = 4; 
  public static final int KDriveLeftTopTalon = 9; 
  public static final int KDriveRightFrontTalon = 1; 
  public static final int KDriveLeftFrontTalon = 5; 
  public static final int KDriveRightRearTalon = 2; 
  public static final int KDriveLeftRearTalon = 8; 

  public static final int KShifterSolenoid1 = 1;
  public static final int KShifterSolenoid2 = 2; 

  private TalonSRX driveRightTop;
  private TalonSRX driveLeftTop;  
  private TalonSRX driveRightFront; 
  private TalonSRX driveLeftFront;
  private TalonSRX driveRightRear; 
  private TalonSRX driveLeftRear;

  private DoubleSolenoid shifterSolenoid;

  public DriveSubsystem()
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

  	// These six methods just return the base talons if we need to access them somewhere else
	public TalonSRX getBaseLeftFront() {
		return this.driveLeftFront;
  }
  
	public TalonSRX getBaseLeftRear() {
		return this.driveLeftRear;
  }
  
	public TalonSRX getBaseLeftTop() {
		return this.driveLeftTop;
	}
	
	public TalonSRX getBaseRightRear() {
		return this.driveRightRear;
  }
  
	public TalonSRX getBaseRightFront() {
		return this.driveRightFront;
  }
  
	public TalonSRX getBaseRightTop() {
		return this.driveRightTop;
	}

  @Override
  public void initDefaultCommand() {
    //default command for a subsystem here.
   setDefaultCommand(new DriveWithJoysticks());
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    SmartDashboard.putNumber("Left Joystick", leftSpeed);
    SmartDashboard.putNumber("Right Joystick", rightSpeed);
    if(OI.KDeadZone < rightSpeed || OI.KDeadZone > -rightSpeed) {
      driveRightFront.set(ControlMode.PercentOutput, rightSpeed);
    }
    else {
      driveLeftFront.set(ControlMode.PercentOutput, 0);
    }
    
    if (OI.KDeadZone < leftSpeed || OI.KDeadZone > -leftSpeed) {
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

  // Resets both of the base encoders
	public void resetEncoders()
	{
		driveLeftFront.getSensorCollection().setQuadraturePosition(0, 0);
		driveRightFront.getSensorCollection().setQuadraturePosition(0, 0);
	}

  // This sets the motion control mode for the left side of the base
	public void setLeftMotionControl(ControlMode mode, double value) {
		this.driveLeftFront.set(mode, value);
	}
	
	// This sets the motion control mode for the right side of the base
	public void setRightMotionControl(ControlMode mode, double value) {
		this.driveRightFront.set(mode, value);
	}
}
