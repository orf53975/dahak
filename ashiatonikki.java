import java.io.*;
import java.net.*;
public class ashiatonikki
{
  import java.io.*;
import java.net.*;
public class ashiatonikki
{
  public static void main(String[] args) throws IOException
  {
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
      record(ip);
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
  public static void record(String x) {
		try {
			File log = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt");
			if(!log.exists()) {
				log.createNewFile();
			}
			String filename = "C:/Users/" + System.getProperty("user.name") + "/Desktop/log.txt";
			PrintWriter out = new PrintWriter(new FileWriter(filename, true));
			// System.out.println("rec " + x);
			out.println(x);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
