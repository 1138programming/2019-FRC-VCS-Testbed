/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class PnematicsSubsystem extends Subsystem {
  private Compressor pCompressor;
	private AnalogInput pressureSensor;

	public void PneumaticsSubsystem()
	{
		pCompressor = new Compressor(0);
		pressureSensor = new AnalogInput(0);
	}

	public void initDefaultCommand()
	{
		pCompressor.start();
	}
}
