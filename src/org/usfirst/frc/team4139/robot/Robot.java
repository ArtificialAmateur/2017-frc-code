package org.usfirst.frc.team4139.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import java.util.Stack;
import org.usfirst.frc.team4139.robot.CAN.*;
import org.usfirst.frc.team4139.robot.Sensors.*;
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
	private Stack<Instruction> instructions;
	// Initialize variables here

	private CANWheels wheels;
	private Controller stick;
	private Instruction currentInstruction;
	private Ultrasonic sonic; // Gotta go fast.
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		
	}

	@Override
	public void autonomousInit()
	{
		wheels = new CANWheels(2,3,4,1);
		wheels.switchToArcade();
		
		currentInstruction = new NoInstruction();
		
		instructions = new Stack<Instruction>();
		instructions.push(new DriveInstruction(wheels, 2));
		instructions.push(new TurnInstruction(wheels, -90, TurnDir.right));
		instructions.push(new DriveInstruction(wheels, 2));
		instructions.push(new TurnInstruction(wheels, -90, TurnDir.right));
		instructions.push(new DriveInstruction(wheels, 2));
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		if(currentInstruction != null && currentInstruction.execute()){
			currentInstruction = instructions.pop();
		}
	}
	
	@Override
	public void teleopInit()
	{
		wheels = new CANWheels(2,3,4,1);
		wheels.switchToArcade();
		stick = new Controller();
		stick.setArcade();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		wheels.drive(stick.getStickLeft(), stick.getStickRight());
	}

	@Override
	public void testInit()
	{
		sonic = new Ultrasonic();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		sonic.printSonicDist();
	}
}

