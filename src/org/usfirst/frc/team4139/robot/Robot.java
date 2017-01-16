package org.usfirst.frc.team4139.robot;

public class Robot extends IterativeRobot
{
	public Joystick stick;
	public Pwmwheels wheels;

	public void robotInit()
	{
		stick = new Joystick(0);
		wheels = new Pwmwheels();
	}
	public void teleopInit()
	{
	}
	public void teleopPeriodic()
	{
		while (true)
		{
			wheels.move(Pwmwheels.FOREWARD);
		}
	}
}
