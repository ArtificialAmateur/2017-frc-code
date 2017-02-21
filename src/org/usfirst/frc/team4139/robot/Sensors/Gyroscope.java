package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.*;

public class Gyroscope
{
	private ADXRS450_Gyro gyro;
	private double constant;

	public Gyroscope()
	{
		gyro = new ADXRS450_Gyro();
		constant = 0.03;
	}
	
	//Initializes gyroscope by reseting angle.
	public void gyroReset()
	{
		gyro.reset();
	}
	
	//Returns the current angle of the robot.
	public double getGyroAngle()
	{
		return gyro.getAngle();
	}
	
	//Prints the current angle of the robot in the console.
	public void printGyroAngle()
	{
		System.out.println("Gyro: "+ getGyroAngle());
	}
	
	public double getGyroConstant()
	{
		return constant;
	}
	public void setGyroConstant(int num)
	{
		constant = num;
	}
	public void calibrate(){
		gyro.calibrate();
	}
}
