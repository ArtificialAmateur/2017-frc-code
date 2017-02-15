package org.usfirst.frc.team4139.robot.CAN;

import org.usfirst.frc.team4139.robot.Sensors.Gyroscope;
import org.usfirst.frc.team4139.robot.Utils.TurnDir;

import com.ctre.CANTalon;

/*
 * This class was written by Ryan Tannenberg and Daniel Ritchie for the 2017 FIRST Robotics Competition.
 * This class contains methods to invert the motors, drive, and switch between tank and arcade drive 
 * for the teleop portion of the competition. It also contains methods to drive a certain distance and
 * turn to a certain degree for the automated section of the competition.
 */

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;


public class CANWheels
{
	//NOTE: The ID's for the TalonSRXs must be configured manually on the roboRIO
	private CANTalon fLMotor;
	private CANTalon rLMotor;
	private CANTalon fRMotor;
	private CANTalon rRMotor;
	
	private RobotDrive robot;
	
	private int driveMode;
	public static final int TANK_DRIVE = 1;
	public static final int ARCADE_DRIVE = 2;
	
	private Encoder lEncoder;
	private Encoder rEncoder;
	
	private Gyroscope gyro;
	private Timer timer;
	
	public static final double circumference = 6*Math.PI;

	public CANWheels(int idFL, int idRL, int idFR, int idRR)
	{
		//encoder = new Encoder(0,1);
		
		gyro = new Gyroscope();
		gyro.gyroReset();
		//'3' is just a placeholder. We need to calculate the circumference of the wheels in order
		//to gauge the distance that each revolution takes. Please use feet as a unit.
		//encoder.setDistancePerPulse(3);
		fLMotor = new CANTalon(idFL);
		rLMotor = new CANTalon(idRL);
		fRMotor = new CANTalon(idFR);
		rRMotor = new CANTalon(idRR);
		
		robot = new RobotDrive(fLMotor,rLMotor,fRMotor,rRMotor);
		driveMode = TANK_DRIVE;
		
		//temp variables for channels
		lEncoder = new Encoder(0,1);
		lEncoder.setDistancePerPulse(circumference/360);
		rEncoder.setDistancePerPulse(circumference/360);
		
		timer = new Timer();
		timer.reset();
	}
	
	/*
	//This class tells the robot to drive a certain distance in feet, at a speed of 0.3
	//This class needs to be changed to work with the encoder when the encoder is put on the robot.
	public boolean driveDist(double feet)
	{ 	
		this.switchToArcade();
		if(timer.get() == 0){
			timer.start();
		}
		
		double angle = gyro.getGyroAngle();
		double constant = gyro.getGyroConstant();
		
		System.out.println(timer.get());
		
		if(timer.get() < 3){
			this.drive(-.5, -angle*constant);
			System.out.println("Driving");
			return false;
		}
		else {
			timer.reset();
			return true;
		}
	}*/
	
	public boolean driveDist(double feet)
	{ 	
		this.switchToArcade();
		System.out.println(lEncoder.getDistance());
		
		if(lEncoder.getDistance() < feet)
		{
			double angle = gyro.getGyroAngle();
			double constant = gyro.getGyroConstant();
			
			this.drive(-.5,-angle*constant);
			System.out.println("Driving");
			return false;
		}
		else
		{
			lEncoder.reset();
			return true;
		}
	}
	
	//this class tells the robot to turn in a certain direction until it is a certain degree from its initial direction.
	public boolean turn(double degrees, TurnDir turnDir)
	{
		this.switchToArcade();
		
		if(Math.abs(gyro.getGyroAngle()) < Math.abs(degrees)){
			System.out.println("Turning");		
			System.out.println(gyro.getGyroAngle());
			
			switch(turnDir){
			case left:
				this.drive(0,-.7);
				break;
			case right:
				this.drive(0, .7);
				break;
			}	
			return false;
		}
		else {
			gyro.gyroReset();
			return true;
		}
	}
	
	//switches to TankDrive
	public void switchToTank()
	{
		driveMode = TANK_DRIVE;
	}
	
	//switches to ArcadeDrive
	public void switchToArcade()
	{
		driveMode = ARCADE_DRIVE;
	}
	
	//this method drives the robot, based on the current drive mode. Configured to be easily compatible with the Joystick.
	public void drive(double lS,double rS)
	{
		switch(driveMode){
		case TANK_DRIVE:
			robot.tankDrive(-lS, -rS);
			break;
		case ARCADE_DRIVE:
			robot.arcadeDrive(-lS, -rS);
			break;
		default:
			robot.tankDrive(-lS, -rS);
		}
	}
	
	//this method inverts a motor of choice.
	public void setInverted(int motorvalue, boolean bool)
	{
		if(bool)
		{
			switch(motorvalue)
			{
			case 0: fLMotor.setInverted(true);
				break;
			case 1: rLMotor.setInverted(true);
				break;
			case 2: fRMotor.setInverted(true);
				break;
			case 3: rRMotor.setInverted(true);
			}
		}
		else
		{
			switch(motorvalue)
			{
			case 0: fLMotor.setInverted(false);
			break;
			case 1: rLMotor.setInverted(false);
			break;
			case 2: fRMotor.setInverted(false);
			break;
			case 3: rRMotor.setInverted(false);
			}
		}
	}
}