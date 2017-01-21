package org.usfirst.frc.team4139.robot.PWM;

public class PWMWheels
{
	
	public void setMovement(double inOne, double inTwo)// Take in joystick input, make the robot move.
		{
			if(stick.getRawAxis(5)<0 && stick.getRawAxis(5)>=-0.25)// right joystick pushed forward 25%
			{
		robot.tankDrive(0,0.25);//move right wheels forward 25%
			}
	else(stick.getRawAxis(5)>0 && stick.getRawAxis(5)<=0.25)//right joystick pushed back 25%
	{
		robot.tankDrive(0,-0.25);//move right wheels back 25%
	}
			if(stick.getRawAxis(2)<0 && stick.getRawAxis(2)>=-0.25)//left joystick pushed forward 25%
	{
	robot.tankDrive(0.25,0);//move left wheels forward 25%
	}
			else(stick.getRawAxis(2)>0 && stick.getRawAxis(2)<=0.25)//left joystick pushed back 25%
	{
		robot.tankDrive(-0.25,0);//move left wheels backward 25%
	}
		

			{
				if(stick.getRawAxis(5)<-0.25 && stick.getRawAxis(5)>=-0.5)// right joystick pushed forward 50%
				{
			robot.tankDrive(0,0.5);//move right wheels forward 50%
				}
		else(stick.getRawAxis(5)>0.25 && stick.getRawAxis(5)<=0.5)//right joystick pushed back 50%
		{
			robot.tankDrive(0,-0.5);//move right wheels back 50%
		}
				if(stick.getRawAxis(2)<-0.25 && stick.getRawAxis(2)>=-0.5)//left joystick pushed forward 50%
		{
		robot.tankDrive(0.5,0);//move left wheels forward 50%
		}
				else(stick.getRawAxis(2)>0.25 && stick.getRawAxis(2)<=0.5)//left joystick pushed back 50%
		{
			robot.tankDrive(-0.5,0);//move left wheels backward 50%
		}
				
				
		
		{
					if(stick.getRawAxis(5)<-0.5 && stick.getRawAxis(5)>=-1)// right joystick pushed forward 50%
					{
				robot.tankDrive(0,0.85);//move right wheels forward 50%
					}
			else(stick.getRawAxis(5)>0.5 && stick.getRawAxis(5)<=1)//right joystick pushed back 50%
			{
				robot.tankDrive(0,-0.85);//move right wheels back 50%
			}
					if(stick.getRawAxis(2)<-0.5 && stick.getRawAxis(2)>=-1)//left joystick pushed forward 50%
			{
			robot.tankDrive(0.85,0);//move left wheels forward 50%
			}
					else(stick.getRawAxis(2)>0.5 && stick.getRawAxis(2)<=1)//left joystick pushed back 50%
			{
				robot.tankDrive(-0.85,0);//move left wheels backward 50%
			}		
		}
		
			
	/*		if(stick.getRawAxis(5)==-0.5)// right joystick pushed forward halfway
			{
		robot.tankDrive(0,0.5);//move right wheels forward halfspeed
			}
	else(stick.getRawAxis(5)==0.5)//right joystick pushed back halfway
	{
		robot.tankDrive(0,-0.5);//move right wheels back halfspeed
	}
	if(stick.getRawAxis(2)==-0.5)//left joystick pushed forward halfway
	{
	robot.tankDrive(0.5,0);//move left wheels forward halfspeed
	}
	else(stick.getRawaxis(2)==0.5)//left joystick pushed back halfway
	{
		robot.tankDrive(-0.5,0);//move left wheels backward halfspeed
	}



	if(stick.getRawAxis(5)==-1)// right joystick pushed forward 
	{
	robot.tankDrive(0,0.75);//move right wheels forward 
	}
	else(stick.getRawAxis(5)==1)//right joystick pushed back 
	{
	robot.tankDrive(0,-0.75);//move right wheels back
	}
	if(stick.getRawAxis(2)==-1)//left joystick pushed forward 
	{
	robot.tankDrive(0.75,0);//move left wheels forward
	}
	else(stick.getRawaxis(2)==1)//left joystick pushed back
	{
	robot.tankDrive(-0.75,0);//move left wheels backward
	}

	}*/

				


	public void stop()
		{
			robot.tankDrive(0,0);// Make the robot stop moving. maybe. hopefully.
		}



	public void setTurbo()// Push down joystick to move wheels at 100% speed
	{
			while(stick.getRawButton(10))
			{
			if(stick.getRawAxis(5)==-1)
			{
			robot.tankDrive(0,-1);
			}
			else
			{
				if(stick.getRawAxis(5)==1)
				{
					robot.tankDrive(0,1);
				}
			}
				}
			while(stick.getRawButton(9))
			{
				if(stick.getRawAxis(2)==-1)
				{
				robot.tankDrive(-1,0);
				}
				else
				{
					if(stick.getRawAxis(2)==1)
					{
						robot.tankDrive(1,0);
					}
				}
					}
		}
		
		





