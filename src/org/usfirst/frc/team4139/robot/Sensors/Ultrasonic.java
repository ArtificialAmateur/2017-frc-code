package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

public class Ultrasonic
{
	private AnalogInput sonic;
	private double currentDist;
	public static final double INCH_CONVERT = 0.125; // factor to convert sensor values to a distance in inches
	
	public Ultrasonic()
	{
		sonic    = new AnalogInput(1); //Input		
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
}
