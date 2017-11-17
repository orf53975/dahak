package main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class CamBot
{
	public static void snapImg(Radio radio) throws IOException
	{
		if (radio.camQeue)
		{
			Webcam wc = Webcam.getDefault();
			wc.open();
			File tgt = new File ("C:/ClassPolicy/cam.png");
			if (tgt.exists()) {
				tgt.delete();
			}
			ImageIO.write(wc.getImage(), "PNG", tgt);
		}
	}
}
