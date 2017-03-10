package org.usfirst.frc.team4139.robot.Utils;

import org.usfirst.frc.team4139.robot.CAN.*;

public class WaitInstruction implements Instruction {

	private CANWheels wheels;
	private double seconds;
	public WaitInstruction(CANWheels cWheels, double time){
		seconds = time;
		wheels = cWheels;
	}
	@Override
	public boolean execute(double stupid) {
		return wheels.wait(seconds);
	}

}
