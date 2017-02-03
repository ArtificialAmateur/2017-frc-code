package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.XboxController;

public class Controller
{
	private XboxController xbox;
	public static final int LEFT_STICK = 1; //Im not sure the stick ints are right
	public static final int RIGHT_STICK = 5;
	
	public Controller()
	{
		xbox = new XboxController(0);
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
		System.out.println("Controller RS: "+xbox.getRawAxis(RIGHT_STICK));
		return xbox.getRawAxis(RIGHT_STICK);
	}
	
}
