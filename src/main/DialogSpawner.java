package main;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class DialogSpawner extends Thread {
	final String title;
	final String detail;
	static int numSpawners = 0;
	DialogSpawner child;
	public DialogSpawner(String title, String detail) {
		this.title = title;
		this.detail = detail;
		numSpawners++;
	}

	public void run() {
		Random thisR = new Random();
		JLabel lbl = new JLabel(this.detail);
		if(numSpawners < 100) {
			this.child = new DialogSpawner(this.title, this.detail);
			this.child.run();
		}
		while(true) {
			JDialog d = new JDialog(new JFrame());
			d.setSize(500, 200);
			d.setTitle(this.title);
			d.add(lbl);
			d.setLocation(thisR.nextInt(500)+200, thisR.nextInt(500)+200);
            d.show();
		}
	}

}
