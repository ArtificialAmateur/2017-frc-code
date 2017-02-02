package org.usfirst.frc.team4139.robot.utils;

import org.usfirst.frc.team4139.robot.CAN.CANWheels;

public class DriveInstruction implements Instruction 
{
	private CANWheels wheels;
	private double feet;
	public DriveInstruction(CANWheels cWheels, double feetDrive)
	{
		wheels = cWheels;
		feet = feetDrive;
	}
	@Override
	public boolean execute() {
		return wheels.driveDist(feet);
	}

}
