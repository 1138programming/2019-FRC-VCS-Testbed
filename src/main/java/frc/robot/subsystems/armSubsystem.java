/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.stopArm;

import static org.junit.Assume.assumeNoException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class driveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final int KArmTalon = 10; 

  private TalonSRX armTalon;

  public driveSubsystem()
  {
    armTalon = new TalonSRX(KArmTalon);
  }

  @Override
  public void initDefaultCommand() {
    //default command for a subsystem here.
   setDefaultCommand(new stopArm());
  }

  public void move(int speed) {
    armTalon.set(ControlMode.PercentOutput, speed);
  }
}
