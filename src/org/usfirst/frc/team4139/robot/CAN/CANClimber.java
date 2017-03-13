package org.usfirst.frc.team4139.robot.CAN;
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
			winder.set(1.0);
		else
			winder.set(0.0);
	}
}
