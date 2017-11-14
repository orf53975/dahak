package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class wanIP
{
	public static String getExtIP()
	{
		try
		{
		     String s = "";
			 URL yahoo = new URL("https://ifconfig.co/ip");
	         URLConnection yc = yahoo.openConnection();
	         BufferedReader in = new BufferedReader(new InputStreamReader(
	                 yc.getInputStream(), "UTF-8"));
	         String inputLine;
	         StringBuilder a = new StringBuilder();
	         while ((inputLine = in.readLine()) != null)
	         {
	             a.append(inputLine);  
	             s = (inputLine.substring(0));
	         }
	         	 return s;
			 } 
			 catch (Exception e)
			 {
				 return "ERROR";
			 }
	}
	
}
