package org.usfirst.frc.team4139.robot.Utils;

import org.usfirst.frc.team4139.robot.CAN.CANWheels;

public class TimedDriveInstruction implements Instruction
{

	private CANWheels wheels;
	private double seconds;
	public TimedDriveInstruction(CANWheels cWheels, double driveTime)
	{
		wheels = cWheels;
		seconds = driveTime;
	}
	@Override
	public boolean execute(double param)
	{
		return wheels.driveTime(seconds);
	}
}
