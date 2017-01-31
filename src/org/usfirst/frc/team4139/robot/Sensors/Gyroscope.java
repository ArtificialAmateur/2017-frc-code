package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Gyroscope
{
	private Gyro myGyro; 

	public Gyroscope()
	{
		myGyro = new AnalogGyro(1);
	}
	
	public void reset()
	{
		myGyro.reset();
	}
	
	public double getAngle()
	{
		return myGyro.getAngle();
	}
}
