/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class RPi extends Subsystem {
	private NetworkTableEntry camEntry;
	//private byte[] received;

	public RPi()
	{
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("VisionInfo");
		camEntry = table.getEntry("CamInfo");
	}

	public void sendMyName() {
		double[] arr = camEntry.getDoubleArray(new double[]{-1, -1, -1, -1, -1});
		System.out.println("Cam info:");
		for (double i : arr) {
			System.out.println(i);
		}
	}

	public void initDefaultCommand()
	{
	}
}
