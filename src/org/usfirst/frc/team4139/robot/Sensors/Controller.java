package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.XboxController;

public class Controller
{
	private XboxController xbox;
	public static final int LEFT_STICK = 1;
	public static final int RIGHT_STICK_TANK = 5;
	public static final int RIGHT_STICK_ARCADE = 4;
	public static final int ARCADE = 0;
	public static final int TANK = 1;
	
	private int driveMode;
	
	public Controller()
	{
		xbox = new XboxController(0);
		driveMode = TANK;
	}
	
	public boolean getButtonA()
	{
		if(xbox.getAButton())
			System.out.println("Controller: A");
		else
			System.out.println("Controller: NO INPUT");
		return xbox.getAButton();
	}
	
	public boolean getButtonB()
	{
		if(xbox.getBButton())
			System.out.println("Controller: B");
		else
			System.out.println("Controller: NO INPUT");
		return xbox.getBButton();
	}
	
	public boolean getButtonX()
	{
		if(xbox.getXButton())
			System.out.println("Controller: X");
		else
			System.out.println("Controller: NO INPUT");
		return xbox.getXButton();
	}
	
	public boolean getButtonY()
	{
		if(xbox.getYButton())
			System.out.println("Controller: Y");
		else
			System.out.println("Controller: NO INPUT");
		return xbox.getYButton();
	}
	
	public boolean getButtonStart()
	{
		if(xbox.getStartButton())
			System.out.println("Controller: Start");
		else
			System.out.println("Controller: NO INPUT");
		return xbox.getStartButton();
	}
	
	public double getStickLeft()
	{
		System.out.println("Controller LS: "+xbox.getRawAxis(LEFT_STICK));
		return xbox.getRawAxis(LEFT_STICK);
	}
	
	public double getStickRight()
	{
		switch(driveMode)
		{
		case TANK:
			System.out.println("Controller RS: "+xbox.getRawAxis(RIGHT_STICK_TANK));
			return xbox.getRawAxis(RIGHT_STICK_TANK);
		case ARCADE:
			System.out.println("Controller RS: "+xbox.getRawAxis(RIGHT_STICK_ARCADE));
			return xbox.getRawAxis(RIGHT_STICK_ARCADE);
		default:
			return xbox.getRawAxis(0);
		}
	}
	public void setArcade()
	{
		driveMode = ARCADE;
	}
	
	public void setTank()
	{
		driveMode = TANK;
	}
}
