package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

public class Ultrasonic
{
	private AnalogInput sonic;
	private double currentDist;
	public static final double VOLTS_TO_DISTANCE = 0.00922706; // factor to convert sensor values to a distance in inches
	
	public Ultrasonic(int input)
	{
		sonic    = new AnalogInput(input); //Input		
	}
	
	// Returns the ultrasonic range in inches.
	public double getSonicDist()
	{
		currentDist = sonic.getVoltage() / VOLTS_TO_DISTANCE;
		return currentDist;
	}
	
	public void printSonicDist()
	{
		System.out.println("Ultrasonic: "+getSonicDist()+"in.");
	}
}
