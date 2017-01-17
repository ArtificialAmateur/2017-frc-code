package org.usfirst.frc.team4139.robot.CAN;

import com.ctre.*;
import edu.wpi.first.wpilibj.*;

public class CANWheels
{
	public CANTalon frontLeftMotor = new CANTalon(0);
	public CANTalon rearLeftMotor = new CANTalon(1);
	public CANTalon frontRightMotor = new CANTalon(2);
	public CANTalon rearRightMotor = new CANTalon(3);
	
	
	public void setInverted(int motorvalue, boolean bool)
	{
		if(bool)
		{
			switch(motorvalue)
			{
			case 0: frontLeftMotor.setInverted(true);
			break;
			case 1: rearLeftMotor.setInverted(true);
			break;
			case 2: frontRightMotor.setInverted(true);
			break;
			case 3: rearRightMotor.setInverted(true);
			}
		}
		else
		{
			switch(motorvalue)
			{
			case 0: frontLeftMotor.setInverted(false);
			break;
			case 1: rearLeftMotor.setInverted(false);
			break;
			case 2: frontRightMotor.setInverted(false);
			break;
			case 3: rearRightMotor.setInverted(false);
			}
		}
	}
}