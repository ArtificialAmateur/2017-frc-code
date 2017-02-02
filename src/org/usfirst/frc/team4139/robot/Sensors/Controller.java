package org.usfirst.frc.team4139.robot.Sensors;

import edu.wpi.first.wpilibj.XboxController;

public class Controller
{
	private XboxController xbox;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int LEFT_STICK = 1; //Im not sure the stick ints are right
	public static final int RIGHT_STICK = 5;
	
	public Controller()
	{
		xbox = new XboxController(0);
	}
	
	public boolean getButtonA()
	{
		System.out.println("Controller: A");
		return xbox.getAButton();
	}
	
	public boolean getButtonB()
	{
		System.out.println("Controller: B");
		return xbox.getBButton();
	}
	
	public boolean getButtonX()
	{
		System.out.println("Controller: X");
		return xbox.getXButton();
	}
	
	public boolean getButtonY()
	{
		System.out.println("Controller: Y");
		return xbox.getYButton();
	}
	
	public boolean getButtonStart()
	{
		System.out.println("Controller: Start");
		return xbox.getStartButton();
	}
}
