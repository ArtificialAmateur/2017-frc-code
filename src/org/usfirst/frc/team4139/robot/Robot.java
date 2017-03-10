package org.usfirst.frc.team4139.robot;

import java.util.LinkedList;
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team4139.robot.CAN.*;
import org.usfirst.frc.team4139.robot.Sensors.*;
import org.usfirst.frc.team4139.robot.Sensors.Ultrasonic;
import org.usfirst.frc.team4139.robot.Utils.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	private LinkedList<Instruction> instructions;
	// Initialize variables here
	/*
	 * AUTO_MODE is a variable that corresponds to our starting position. Facing the field from the pit,
	 * 1 is the left, 2 is the center, 3 is the right.
	 */ 
	private static int AUTO_MODE = 2;
	private CANWheels   wheels;
	private CANClimber  climber;
	private Controller  stick;
	private Instruction currentInstruction;
	private CameraS      webcam;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		wheels = new CANWheels(4,1,3,2);
		webcam = new CameraS();
		climber = new CANClimber(0);
	}

	@Override
	public void autonomousInit()
	{
		wheels.start();
		currentInstruction = new NoInstruction();
		instructions = new LinkedList<Instruction>();
		
//		switch(AUTO_MODE){
//		case 1:
//			instructions.add(new TimedDriveInstruction(wheels, 1.0));
//			instructions.add(new TurnInstruction(wheels, 90, TurnDir.left));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			instructions.add(new TurnInstruction(wheels, 90, TurnDir.right));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			instructions.add(new TurnInstruction(wheels, 45, TurnDir.left));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			break;
//		case 2:
			instructions.add(new TimedSensorDriveInstruction(wheels, 10));
			instructions.add(new TimedDriveInstruction(wheels, 2));
//		case 3:
//			instructions.add(new TimedDriveInstruction(wheels, 1.0));
//			instructions.add(new TurnInstruction(wheels, 90, TurnDir.right));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			instructions.add(new TurnInstruction(wheels, 90, TurnDir.left));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			instructions.add(new TurnInstruction(wheels, 45, TurnDir.right));
//			instructions.add(new TimedDriveInstruction(wheels, .75));
//			break;
//		}
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	// 1 second of timer is the equivalent of 4.777 feet or so we think so just try that
	public void autonomousPeriodic()
	{
		if(currentInstruction.execute(0.0))
		{
			if(instructions.isEmpty())
				currentInstruction = new NoInstruction();
			else
				currentInstruction = instructions.pop();
		}
	}
	@Override
	public void teleopInit()
	{
		wheels.start();
		wheels.switchToTank();
		stick = new Controller();
		stick.setTank();
		currentInstruction = new NoInstruction();
		
		instructions = new LinkedList<Instruction>();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		
		if(stick.getButtonA())
			climber.toggle();
		
		if(stick.getButtonX())
			wheels.toggleDriveMode();
		
		if(stick.getButtonY())
			instructions.add(new TurnInstruction(wheels, 180, TurnDir.left));
		
		if(stick.getButtonB())
			wheels.invertToggle();
		
		if(currentInstruction.execute(0.0))
		{
			if(instructions.isEmpty())
				currentInstruction = new NoInstruction();
			else
				currentInstruction = instructions.pop();
		}
		
		wheels.drive(stick.getStickLeft(), stick.getStickRight());
		climber.climb();
	}

	@Override
	public void testInit()
	{
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		
	}
}

