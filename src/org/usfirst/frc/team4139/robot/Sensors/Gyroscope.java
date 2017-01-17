package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Gyroscope
{
	private Gyro mygyro;

	public Gyroscope()
	{
		mygyro = new AnalogGyro(1);
	}
	
	public void reset()
	{
		mygyro.reset();
	}
	
	public double getangle()
	{
		return mygyro.getAngle();
	}
}
