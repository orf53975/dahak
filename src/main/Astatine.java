package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Astatine extends Thread {
	private static final String FILENAME = "fillup.txt";
	private BufferedWriter bw;
	private String content = "*sigh*";
	public Astatine() throws IOException {
		this.bw = new BufferedWriter(new FileWriter(FILENAME));
	}
	
	public void run() {
		while(true) {			
			try {
				bw.write(this.content);
				//max 200MB/write to ensure stability
				if(this.content.length()<200000000) {
					this.content += this.content;
				}
				Chocolat.println(this.content.length() + "");
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
