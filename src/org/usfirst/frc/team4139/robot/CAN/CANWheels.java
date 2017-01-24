package org.usfirst.frc.team4139.robot.CAN;
/*
 * This class was written by Ryan Tannenberg and Daniel Ritchie for the 2017 FIRST Robotics Competition.
 * This class contains methods to invert the motors, drive, and switch between tank and arcade drive 
 * for the teleop portion of the competition. It also contains methods to drive a certain distance and
 * turn to a certain degree for the automated section of the competition.
 */
import com.ctre.*;
import org.usfirst.frc.team4139.robot.Sensors.*;
import edu.wpi.first.wpilibj.*;


public class CANWheels
{
	//NOTE: The ID's for the TalonSRXs must be configured manually on the roboRIO
	private CANTalon fLMotor = new CANTalon(0);
	private CANTalon rLMotor = new CANTalon(1);
	private CANTalon fRMotor = new CANTalon(2);
	private CANTalon rRMotor = new CANTalon(3);
	
	private RobotDrive robot;
	
	private int driveMode;
	public static final int TANK_DRIVE = 1;
	public static final int ARCADE_DRIVE = 2;
	public static final int RIGHT = 10;
	public static final int LEFT = 11;
	
	private Encoder encoder;
	private Gyroscope gyro;

	public CANWheels()
	{
		robot = new RobotDrive(fLMotor,rLMotor,fRMotor,rRMotor);
		driveMode = TANK_DRIVE;
		
		encoder = new Encoder(0,1);
		
		gyro = new Gyroscope();
		gyro.reset();
		//'3' is just a placeholder. We need to calculate the circumference of the wheels in order
		//to gauge the distance that each revolution takes. Please use feet as a unit.
		encoder.setDistancePerPulse(3);
	}
	
	//This class tells the robot to drive a certain distance in feet, at a speed of 0.3
	public void driveDist(double feet)
	{
		double feetStep = 0.0; 
		
		while(feetStep<=feet)
		{
			switch(driveMode){
			case TANK_DRIVE:
				this.drive(0.3,0.3);
				break;
			case ARCADE_DRIVE:
				this.drive(0.3, 0.3);
				break;
			default:
				this.drive(0, 0);
			}
			feetStep = encoder.getDistance();
		}
		encoder.reset();
	}
	//this class tells the robot to turn in a certain direction until it is a certain degree from its initial direction.
	public void turn(double degrees, int turnDir)
	{
		double degreeStep = 0.0;
		boolean isTank = false;
		if(driveMode==TANK_DRIVE)
		{
			isTank = true;
			driveMode = ARCADE_DRIVE;
		}
		while(degreeStep<degrees)
		{
			switch(turnDir){
			case LEFT:
				this.drive(0.0,-0.3);
				break;
			case RIGHT:
				this.drive(0.0,0.3);
				break;
			default:
				this.drive(0.0,0.0);
			}
			degreeStep = gyro.getAngle();
		}
		gyro.reset();
		if(isTank)
			driveMode = TANK_DRIVE;
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
			robot.tankDrive(lS, rS);
			break;
		case ARCADE_DRIVE:
			robot.arcadeDrive(lS, rS);
		default:
			robot.tankDrive(lS, rS);
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