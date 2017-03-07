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
	private static int AUTO_MODE;
	private CANWheels   wheels;
	private CANClimber  climber;
	private Controller  stick;
	private Instruction currentInstruction;
//	private Camera      webcam;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		wheels = new CANWheels(4,1,3,2);
	//	webcam = new Camera();
		climber = new CANClimber(0);
	
		AUTO_MODE = 1;
	}

	@Override
	public void autonomousInit()
	{
		wheels.start();
		currentInstruction = new NoInstruction();
		
		instructions = new LinkedList<Instruction>();
		instructions.add(new DriveInstruction(wheels, 0.419));
		instructions.add(new TurnInstruction(wheels, 90, TurnDir.left));
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
		wheels.drive(stick.getStickLeft(), stick.getStickRight());
		if(stick.getButtonA())
		{
			climber.toggle();
		}
		if(stick.getButtonX()){
			wheels.toggleDriveMode();
		}
		if(stick.getButtonY()){
			instructions.add(new TurnInstruction(wheels, 180, TurnDir.left));
		}
		if(currentInstruction.execute(0.0))
		{
			if(instructions.isEmpty())
				currentInstruction = new NoInstruction();
			else
				currentInstruction = instructions.pop();
		}
			// Wait do I need a getDrive method to figure out if we're in tank or arcade?
			// Also, I believe we need the inverted control method to be in Wheels.
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
		wheels.switchToArcade();
		stick = new Controller();
		stick.setArcade();
		wheels.drive(stick.getStickLeft(), stick.getStickRight());
//		System.out.println(wheels.testSonic());
//		webcam.getXPos();
	}
}

