/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.ShiftDrive;
import frc.robot.commands.Diagnostic;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DriveBaseForward;
import frc.robot.commands.EncoderTest;

import frc.robot.MotionProfile.CrossLine;
import frc.robot.MotionProfile.Middle2Right;
import frc.robot.MotionProfile.LeftProfiles;
import frc.robot.MotionProfile.RightProfiles;
import frc.robot.AutoCommand.TestMotionProfile;

import frc.robot.MotionProfile.Ways;
import frc.robot.AutoCommand.TrajectoryCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.

  //Controller Constants 
  public static final int KLogitechDrive = 0;
  public static final int KXboxArms = 1;

  //DeadZone
  public static final double KDeadZoneAxis = 0.2; 
  public static final double KDeadZone = 0.2; 

  //Logitech Button Constants 
  public static final int KButton1 = 1;
	public static final int KButton2 = 2;
	public static final int KButton3 = 3;
	public static final int KButton4 = 4;
	public static final int KButton5 = 5;
	public static final int KButton6 = 6;
	public static final int KButton7 = 7;
	public static final int KButton8 = 8;

  //Xbox Button Constants 
  public static final int KButtonA = 1;
	public static final int KButtonB = 2;
	public static final int KButtonX = 3;
	public static final int KButtonY = 4;
	public static final int KLeftBumper = 5;
	public static final int KRightBumper = 6;
	public static final int KStartButton = 8;
	public static final int KLeftTrigger = 9;
  public static final int KRightTrigger = 10;

  public Joystick logitech, xbox;
	public JoystickButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8; // Logitech Button
	public JoystickButton btnA, btnB, btnX, btnY, btnLB, btnRB, btnStrt, btnLT, btnRT; // Xbox Buttons

  private static final CrossLine crossLine = new CrossLine();
  private static final Middle2Right middle2Right = new Middle2Right();
  private static final LeftProfiles leftProfiles = new LeftProfiles();
  private static final RightProfiles rightProfiles = new RightProfiles();

  public OI(){
    //Controllers 
    logitech = new Joystick(KLogitechDrive);
    xbox = new Joystick(KXboxArms);

    //Logitech Buttons
		btn1 = new JoystickButton(logitech, KButton1);
		btn2 = new JoystickButton(logitech, KButton2);
		btn3 = new JoystickButton(logitech, KButton3);
		btn4 = new JoystickButton(logitech, KButton4);
		btn5 = new JoystickButton(logitech, KButton5);
		btn6 = new JoystickButton(logitech, KButton6);
		btn7 = new JoystickButton(logitech, KButton7);
		btn8 = new JoystickButton(logitech, KButton8);

		//XBox Buttons
		btnA = new JoystickButton(xbox, KButtonA);
		btnB = new JoystickButton(xbox, KButtonB);
		btnX = new JoystickButton(xbox, KButtonX);
		btnY = new JoystickButton(xbox, KButtonY);
		btnLB = new JoystickButton(xbox, KLeftBumper);
		btnRB = new JoystickButton(xbox, KRightBumper);
		btnStrt = new JoystickButton(xbox, KStartButton);
		btnLT = new JoystickButton(xbox, KLeftTrigger);
    btnRT = new JoystickButton(xbox, KRightTrigger);
    
    //Button Assigned Commands 
    btn5.whenPressed(new ShiftDrive());
    btn2.whenPressed(new Diagnostic());
    btnLT.whenPressed(new ArmUp());
    btnLB.whenPressed(new ArmDown());
    btnX.whenPressed(new DriveBaseForward());
    btnA.whenPressed(new EncoderTest());
    //btnB.whenPressed(new TestMotionrofile(crossLine.left, crossLine.right));
    //btnB.whenPressed(new TestMotionProfile(middle2Right.left_Part1, middle2Right.right_Part1));
    //btnB.whenPressed(new TestMotionProfile(leftProfiles.U_Turn, rightProfiles.U_Turn));
    btnB.whenPressed(new TrajectoryCommand(Ways.CROSS_LINE, 8, 5, 70, 0.05, 0.635));
  }

  public double getRightAxis() {
    if(logitech.getThrottle() > KDeadZoneAxis || logitech.getThrottle() < -KDeadZoneAxis){
      return -logitech.getThrottle(); 
    }
    else {
      return 0; 
    }
  }
  public double getLeftAxis() {
    if(logitech.getY() > KDeadZoneAxis || logitech.getY() < -KDeadZoneAxis){
      return -logitech.getY(); 
    }
    else {
      return 0; 
    }
  }

  public double getXboxAxis() {
    if(xbox.getThrottle() > KDeadZoneAxis || xbox.getThrottle() < -KDeadZoneAxis){
      return xbox.getY(); 
    }
    else {
      return 0; 
    }
  }
  //Joystick stick = new Joystick(port);
  //Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
