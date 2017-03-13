package org.usfirst.frc.team4139.robot.CAN;
/*
 * This class was written by Daniel Ritchie for Team 4139 in the FIRST Robotics Competition
 * It is meant to be called towards the end of the competition, when the robots are supposed to climb a rope to a certain point.
 * Once positioned correctly, use toggle() to start and stop the motor moving upward.
 * The method climb() should be called every time that teleopPeriodic() is called in order for the motors to run correctly.
 */
import edu.wpi.first.wpilibj.*;
import com.ctre.*;
public class CANClimber
{	
	//Three instance fields. 'winder' is the CANTalon, and the two booleans are used to "remember" if the robot is supposed to be climbing or descending.
	private Talon winder;
	private boolean isClimbing;
	//Constructor: sets the boolean instance fields to the correct initial value (motionless), initializes the CANTalon,
	//along with making sure that the CANTalon is in the correct mode.
	public CANClimber(int talonID)
	{
		isClimbing = false;
		winder = new Talon(talonID);
	}
	//toggle() is meant to be called to either start or stop the ascent of the robot up the rope.
	public void toggle()
	{
		isClimbing=!isClimbing;
		Timer.delay(0.5);
	}
	//climb() is meant to be called in every teleopPeriodic() call.
	public void climb()
	{
		if(isClimbing)
		{
			winder.set(1.0);
		}
		else
		{
			winder.set(0.0);
		}
	}
}
