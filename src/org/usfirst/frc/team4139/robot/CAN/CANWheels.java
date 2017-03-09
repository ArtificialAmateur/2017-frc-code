package org.usfirst.frc.team4139.robot.CAN;

import org.usfirst.frc.team4139.robot.Sensors.Gyroscope;
import org.usfirst.frc.team4139.robot.Utils.TurnDir;
import org.usfirst.frc.team4139.robot.Sensors.Ultrasonic;

import com.ctre.CANTalon;

/*
 * This class was written by Ryan Tannenberg and Daniel Ritchie for the 2017 FIRST Robotics Competition.
 * This class contains methods to invert the motors, drive, and switch between tank and arcade drive 
 * for the teleop portion of the competition. It also contains methods to drive a certain distance and
 * turn to a certain degree for the automated section of the competition.
 */

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
	
	private boolean totalInvert;
	private int driveMode;
	public static final int TANK_DRIVE = 1;
	public static final int ARCADE_DRIVE = 2;
	
	private Gyroscope gyro;
	private Timer timer;
	private Ultrasonic sonic; //gota go faast
	
	public static final double circumference = (6*Math.PI);

	public CANWheels(int idFL, int idRL, int idFR, int idRR)
	{		
		gyro = new Gyroscope();
		gyro.gyroReset();
		
		sonic = new Ultrasonic(0);
				
		fLMotor = new CANTalon(idFL);
		rLMotor = new CANTalon(idRL);
		fRMotor = new CANTalon(idFR);
		rRMotor = new CANTalon(idRR);
		
//		rRMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		rRMotor.reverseSensor(true);
//		rRMotor.configEncoderCodesPerRev(1040);
//		rRMotor.setPosition(0);
		
		robot = new RobotDrive(fLMotor,rLMotor,fRMotor,rRMotor);
		driveMode = TANK_DRIVE;
		totalInvert = false;
		
		timer = new Timer();
		timer.reset();
	}
	
	public void start(){
		gyro.gyroReset();
		rRMotor.setPosition(0);
		timer.reset();
	}

	//This class tells the robot to drive for a certain amount of time (the parameter), at a speed of 0.5
	public boolean driveTime(double mru)
	{ 	
		this.switchToArcade();
		if(timer.get() == 0){
			timer.start();
		}
		
		System.out.println(timer.get());
		
		if(timer.get() < mru){
			this.drive(-.5,0.0);
			System.out.println("Driving");
			return false;
		}
		else {
			timer.stop();
			timer.reset();
			return true;
		}
	}
	
	public boolean driveDist(double mru)
	{ 	
		this.switchToArcade();
		System.out.println("Encoder Position: "+rRMotor.getEncPosition());
		System.out.println("Distance in Inches: "+rRMotor.getEncPosition()*circumference);
		if(circumference * rLMotor.getEncPosition() < mru)
		{
			this.drive(-.5, 0.0);
			System.out.println("Driving");
			return false;
		}
		else
		{
			rRMotor.setPosition(0);
			this.drive(0.0, 0.0);
			gyro.gyroReset();
			return true;
		}
	}
	
	//this class tells the robot to turn in a certain direction until it is a certain degree rLom its initial direction.
	public boolean turn(double degrees, TurnDir turnDir)
	{
		this.switchToArcade();
		
		if(Math.abs(gyro.getGyroAngle()) < Math.abs(degrees-10)){
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
			this.drive(0.0, 0.0);
			return true;
		}
	}
	
	public void invertToggle(){
		totalInvert = !totalInvert;
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
		if(totalInvert){
			switch(driveMode){
			case TANK_DRIVE:
				robot.tankDrive(rS, lS);
				break;
			case ARCADE_DRIVE:
				robot.arcadeDrive(lS, rS);
				break;
			default:
				robot.tankDrive(rS, lS);
			}
		}
		else{
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
	}
	
	public void toggleDriveMode(){
		if(driveMode == TANK_DRIVE){
			this.switchToArcade();
		}
		else{
			this.switchToTank();
		}
	}
}