package org.usfirst.frc.team4139.robot.utils;

import org.usfirst.frc.team4139.robot.CAN.CANWheels;

public class TurnInstruction implements Instruction 
{
	private double turnAng;
	private CANWheels wheels;
	private TurnDir turnDir;
	public TurnInstruction(CANWheels cWheels, double angle, TurnDir turnDir)
	{
		turnAng = angle;
		wheels = cWheels;
		this.turnDir = turnDir;
	}
	
	@Override
	public boolean execute() {
		return wheels.turn(turnAng, turnDir);
	}

}
