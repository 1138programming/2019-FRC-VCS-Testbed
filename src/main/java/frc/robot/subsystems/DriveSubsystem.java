/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final int KMotorRightTopTalon = 1; 
  public static final int KMotorLeftTopTalon = 2; 
  public static final int KMotorRightFrontTalon = 3; 
  public static final int KMotorLeftFrontTalon = 4; 
  public static final int KMotorRightRearTalon = 5; 
  public static final int KMotorLeftRearTalon = 6; 

  public static final int KShiftSolenoid1 = 1;
  public static final int KShiftSolenoid2 = 2; 

  private TalonSRX motorRightTop;
  private TalonSRX motorLeftTop;  
  private TalonSRX motorRightFront; 
  private TalonSRX motorLeftFront;
  private TalonSRX motorRightRear; 
  private TalonSRX motorLeftRear;

  private DoubleSolenoid shiftSolenoid;

  public DriveSubsystem()
  {
    motorRightTop = new TalonSRX(KMotorRightTopTalon);
    motorLeftTop = new TalonSRX(KMotorLeftTopTalon);
    motorRightFront = new TalonSRX(KMotorRightFrontTalon); 
    motorLeftFront = new TalonSRX(KMotorLeftFrontTalon);
    motorRightRear = new TalonSRX(KMotorRightRearTalon);
    motorLeftRear = new TalonSRX(KMotorLeftRearTalon);

    shiftSolenoid = new DoubleSolenoid(KShiftSolenoid1, KShiftSolenoid2);

    motorRightTop.set(ControlMode.Follower, motorRightFront.getDeviceID());
    motorRightRear.set(ControlMode.Follower, motorRightFront.getDeviceID());
    motorLeftTop.set(ControlMode.Follower, motorLeftFront.getDeviceID());
    motorLeftRear.set(ControlMode.Follower, motorLeftFront.getDeviceID());

  }

  @Override
  public void initDefaultCommand() {
    //default command for a subsystem here.
   setDefaultCommand(new DriveWithJoysticks());
  }

  public void tankDrive(double rightSpeed, double leftSpeed) {
    if(OI.KDeadZone < rightSpeed || OI.KDeadZone > -rightSpeed) {
      motorRightFront.set(ControlMode.PercentOutput, rightSpeed);
    }
    else {
      motorLeftFront.set(ControlMode.PercentOutput, 0);
    }
    
    if (OI.KDeadZone < leftSpeed || OI.KDeadZone > -leftSpeed) {
      motorLeftFront.set(ControlMode.PercentOutput, leftSpeed);
    }
    else {
      motorLeftFront.set(ControlMode.PercentOutput, 0);
    }
  }

	public void highShiftBase() {
    shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  
	public void lowShiftBase() {
    shiftSolenoid.set(DoubleSolenoid.Value.kForward);
  }

	public void toggleShift() {
		if (shiftSolenoid.get() == DoubleSolenoid.Value.kForward) {
			highShiftBase();
		}
		else {
			lowShiftBase();
		}
  }
}
