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
	
	//Initializes gyroscope by reseting angle.
	public void gyroInit()
	{
		gyro.reset();
	}
	
	//Returns the current angle of the robot.
	public double getGyroAngle()
	{
		return angle;
	}
	
	//Prints the current angle of the robot in the console.
	public void printGyroAngle()
	{
		System.out.println("Gyro: "+angle);
	}
	
	public double getGyroConstant()
	{
		return constant;
	}
	public void setGyroConstant(int num)
	{
		constant = num;
	}
}
