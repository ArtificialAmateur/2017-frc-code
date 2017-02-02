package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

// Distance = voltage*scale factor

public class Ultrasonic
{
	private AnalogInput sonic;
	private double currentDist, currentSpeed;
	public static final double HOLD_DISTANCE = 12.0; // distance in inches the robot wants to stay from an object
	public static final double INCH_CONVERT = 0.125; // factor to convert sensor values to a distance in inches
	public static final double CONSTANT = 0.05; // proportional speed constant
	
	public Ultrasonic()
	{
		sonic = new AnalogInput(1); //Input && Output
	}
	
	// Returns the ultrasonic range in inches.
	public double getSonicDist()
	{
		currentDist = sonic.getValue() * INCH_CONVERT;
		return currentDist;
	}
	
	public void printSonicDist()
	{
		currentDist = sonic.getValue() * INCH_CONVERT;
		System.out.println("Ultrasonic: "+currentDist+"in.");
	}
	
	public double getSonicSpeed()
	{
		currentSpeed = (HOLD_DISTANCE - currentDist) * CONSTANT;
		return currentSpeed;
	}
	
	public void printSonicSpeed()
	{
		currentSpeed = (HOLD_DISTANCE - currentDist) * CONSTANT;
		System.out.println("Sonic speed: "+currentSpeed);
	}
}
