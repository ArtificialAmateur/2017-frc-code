package org.usfirst.frc.team4139.robot;

import java.util.LinkedList;

import org.usfirst.frc.team4139.robot.CAN.CANWheels;
import org.usfirst.frc.team4139.robot.Sensors.Controller;
import org.usfirst.frc.team4139.robot.Sensors.Ultrasonic;
import org.usfirst.frc.team4139.robot.Utils.DriveInstruction;
import org.usfirst.frc.team4139.robot.Utils.Instruction;
import org.usfirst.frc.team4139.robot.Utils.NoInstruction;
import org.usfirst.frc.team4139.robot.Utils.TurnDir;
import org.usfirst.frc.team4139.robot.Utils.TurnInstruction;

import edu.wpi.first.wpilibj.IterativeRobot;

//Testing accessing the camera feed.
import edu.wpi.first.wpilibj.CameraServer;

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
		//More camera testing stuff
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void autonomousInit()
	{
		wheels = new CANWheels(2,3,4,1);
		wheels.switchToArcade();
		
		currentInstruction = new NoInstruction();
		
<<<<<<< HEAD
		instructions = new LinkedList<Instruction>();
		//instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		instructions.add(new TurnInstruction(wheels, 90,TurnDir.left));
		instructions.add(new DriveInstruction(wheels, 1));
		
		//instructions.add(new TurnInstruction(wheels, 90, TurnDir.right));
		
		/*instructions.add(new DriveInstruction(wheels, 2));
		instructions.add(new TurnInstruction(wheels, -90, TurnDir.right));
		instructions.add(new DriveInstruction(wheels, 2));*/
=======
		instructions = new Stack<Instruction>();
		instructions.push(new DriveInstruction(wheels, 2));
		instructions.push(new TurnInstruction(wheels, -90, TurnDir.right));
		instructions.push(new DriveInstruction(wheels, 2));
		instructions.push(new TurnInstruction(wheels, -90, TurnDir.right));
		instructions.push(new DriveInstruction(wheels, 2));
>>>>>>> 420beb57aaf1a9d0d4613a598f95cdee10e7c956
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public synchronized void autonomousPeriodic()
	{		
		//first time will always return true
		if(currentInstruction.execute()){
			if(instructions.isEmpty()){
				currentInstruction = new NoInstruction();
			}
			else if(!instructions.isEmpty()){
				currentInstruction = instructions.pop();
			}
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
		wheels.switchToArcade();
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

