//I DON'T KNOW WHAT CHANNELS TO PUT IN ANALOGOUTPUT. PLEASE FIX BEFORE RUNNING. THANK.
//ALSO I DON'T KNOW WHAT NUMBER(S) THE SWITCH RETURNS. I'M ASSUMING ZERO MEANS OFF AND ANYTHING ELSE IS ON


package org.usfirst.frc.team4139.robot.CAN;

import edu.wpi.first.wpilibj.AnalogOutput;

public class CANDumper
{
	private CANTalon motor;
	private boolean shouldMove, isUp;
	private AnalogOutput downSwitch;
	private AnalogOutput upSwitch;
	
	public CANDumper()
	{
		motor = new CANTalon(9);
		motor.changeControlMode(CANTalon.TalonControlMode.PercentVbus); //stolen from climber class
		shouldMove = false;
		isUp = false;
		downSwitch = new AnalogOutput(7);
		upSwitch = new AnalogOutput(6);
	}
	
	//should be called whenever the dumper button is pressed in the Robot.java class
	public void activate()
	{
		shouldMove = true;
	}
	
	//should be called with every call to teleopPeriodic() in the Robot.java class
	public boolean update()
	{
		if (upSwitch.getVoltage() != 0)
		{
			shouldMove = false;
			isUp = true;
		}
		
		else if (downSwitch.getVoltage() != 0)
		{
			shouldMove = false;
			isUp = false;
		}
		
		if (shouldMove)
		{
			if (isUp)
				motor.set(-0.5); //half speed
			
			else
				motor.set(0.5);
		}
	}
}

/* Things to ask Kento
 * channels for both AnalogOutputs - anything that is on the roborio
 * what does getVoltage() return? - zero or other numbers
 * does positive motor movement mean up or down? - up?
 * does the dumper start out up or down? - down
 */
 
