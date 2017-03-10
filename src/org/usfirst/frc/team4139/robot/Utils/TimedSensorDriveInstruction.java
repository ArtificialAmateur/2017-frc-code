package org.usfirst.frc.team4139.robot.Utils;

import org.usfirst.frc.team4139.robot.CAN.CANWheels;

public class TimedSensorDriveInstruction implements Instruction {

	private CANWheels wheels;
	private double seconds;
	public TimedSensorDriveInstruction(CANWheels cWheels, double driveTime)
	{
		wheels = cWheels;
		seconds = driveTime;
	}
	@Override
	public boolean execute(double param) {
		return wheels.driveTimeSensor(seconds);
	}
}
