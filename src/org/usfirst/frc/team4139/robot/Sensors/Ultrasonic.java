package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

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
		printSonicDist();
		currentDist = sonic.getValue() * INCH_CONVERT;
		return currentDist;
	}
	
	public void printSonicDist()
	{
		System.out.println("Ultrasonic: "+getSonicDist()+"in.");
	}
	
	public double getSonicSpeed()
	{
		printSonicSpeed();
		currentSpeed = (HOLD_DISTANCE - getSonicDist()) * CONSTANT;
		return currentSpeed;
	}
	
	public void printSonicSpeed()
	{	
		System.out.println("Ultrasonic: "+getSonicDist()+"in/s");
	}
}
