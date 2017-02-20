/*
 * IMPORTANT: Before running this code, make sure to
 * generate code from the GRIP application and put it
 * somewhere this code can access it.
 * 
 * Otherwise, it'll just keep spitting out an "I have
 * absolutely no idea what a 'GripPipeline' is" error.
 */

package org.usfirst.frc.team4139.robot.Sensors;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Camera
{
	private static final int IMG_WIDTH = 640;
	private static final int IMG_LENGTH = 480;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
	
	public Camera()
	{
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_LENGTH);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	        	ArrayList<Rect> rects = new ArrayList<Rect>();
	        	for(MatOfPoint point: pipeline.filterContoursOutput()){
	        		Rect rect = Imgproc.boundingRect(point);
	        		rects.add(rect);
	        	}
	            //Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	               // this.centerX = r.x + (r.width / 2);
	            	this.centerX = averageX(rects);
	            }
	        }
	    });
	    visionThread.start();
	}
	
	public int averageX(ArrayList<Rect> rects){
		int total = 0;
		for(Rect rect: rects){
			total+=rect.x + rect.width/2;
		}
		return total/rects.size();
	}
	
	/*
	 * Should be called once every autonomousPeriodic call, I think?
	 * Or once every tick in whatever setting this Camera class goes in.
	 */
	public double getXPos()
	{
		//double centerX;
		synchronized(imgLock)
		{
			System.out.println("Accessing cameraX instance field...");
			//centerX = this.centerX;
		}
		System.out.println("Current centerX value: " + this.centerX);
		return centerX;
	}
	
	public VisionThread getVisionThread()
	{
		return visionThread;
	}
	
	public Object getImageLock()
	{
		return imgLock;
	}
}
