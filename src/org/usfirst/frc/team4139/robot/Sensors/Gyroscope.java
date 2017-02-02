package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

public class Gyroscope
{
	private ADXRS450_Gyro gyro;
	private double angle, constant;

	public Gyroscope()
	{
		gyro = new ADXRS450_Gyro();
		angle = gyro.getAngle();
		constant = 0.03;
	}
	
	public void gyroReset()
	{
		gyro.reset();
	}
	
	public double getGyroAngle()
	{
		return angle;
	}
	
	public void printGyroAngle()
	{
		System.out.println("Gyro: "+angle);
	}
	
	public double getGyroConstant()
	{
		return constant;
	}
}
