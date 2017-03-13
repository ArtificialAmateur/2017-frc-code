package org.usfirst.frc.team4139.robot.Utils;

import org.usfirst.frc.team4139.robot.CAN.*;

public class CenterInstruction implements Instruction
{
	CANWheels wheels;
	double centerCam;
	double centerStrips;
	public CenterInstruction(CANWheels wheels, double cam, double strips)
	{
		this.wheels = wheels;
		centerCam = cam;
		centerStrips = strips;
	}
	@Override
	public boolean execute(double stripCenter)
	{
		centerStrips = stripCenter;
		if(Math.abs(centerCam - centerStrips) <= 5.0)
			return true;
		else
		{
			wheels.drive(0.0, (centerStrips-centerCam)/(2*centerCam));
			return false;
		}
	}
}
