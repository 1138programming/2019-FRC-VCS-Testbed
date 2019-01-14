/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

import java.io.IOException;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class RPi extends Subsystem {
	private SerialPort wire;
	//private byte[] received;

	public void RPi()
	{
		System.out.println("LED Subsystem Initializing...");
		//received = new byte[6];
		wire = new SerialPort(9600, Port.kOnboard); // Connect to device ID #4
		
		try {
			sendMyName();
			System.out.println("LED Subsystem Initialized!");
		} catch (IOException e) {
			System.out.println("LED Subsystem Failed!");
			System.out.println(e);
		}
	}

	public void sendMyName() throws IOException {
		// Turn the mode into a byte to send (from the enum declaration)
		byte[] toSend = new byte[6];
		toSend[0] = 'e';
		toSend[1] = 'd';
		toSend[2] = 'w';
		toSend[3] = 'a';
		toSend[4] = 'r';
		toSend[5] = 'd';
		
		if (wire != null && toSend != null) {
			// Check that we have a proper I2C connection to avoid
			// NullPointerExceptions
			wire.write(toSend, 6);
		}
		
		// Receive a response to check for an error
		// Wire.readOnly(received, 1);
	}

	public void initDefaultCommand()
	{
	}
}
