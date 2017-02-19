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

	private CANWheels   wheels;
	private CANClimber  climber;
	private Controller  stick;
	private Instruction currentInstruction;
	private Camera      webcam;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		wheels = new CANWheels(2,3,4,1);
		webcam = new Camera();
		climber = new CANClimber(5);
	}

	@Override
	public void autonomousInit()
	{
		
		wheels.switchToArcade();
		
		currentInstruction = new NoInstruction();
		
		instructions = new LinkedList<Instruction>();
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public synchronized void autonomousPeriodic()
	{		
		//first time will always return true
		if(currentInstruction.execute())
		{
			if(instructions.isEmpty())
				currentInstruction = new NoInstruction();
			else if(!instructions.isEmpty())
				currentInstruction = instructions.pop();
		}
		webcam.getXPos();
	}
	
	@Override
	public void teleopInit()
	{
		wheels.switchToTank();
		stick = new Controller();
		stick.setTank();
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
			Timer.delay(1);
		}
		
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
		webcam.getXPos();
	}
}

