package org.usfirst.frc.team4139.robot.Utils;

public enum TurnDir
{
	right(1),left(-1);
	private int value;
	
	TurnDir(final int val)
	{
		value = val;
	}
	
	public int getValue()
	{
		return value;
	}
}
