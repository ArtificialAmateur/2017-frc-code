package org.usfirst.frc.team4139.robot;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team4139.robot.CAN.*;
import org.usfirst.frc.team4139.robot.PWM.*;
import org.usfirst.frc.team4139.robot.Sensors.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	private PWMWheels wheels;
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    private RobotDrive robot;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		robot = new RobotDrive(0,1,2,12);
	}

	@Override
	public void autonomousInit()
	{
		
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		
	}
		@Override
	public void teleopInit()
	{
			
			gyro = new ADXRS450_Gyro();
			gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
			robot.arcadeDrive(-1.0, -1.0);
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

