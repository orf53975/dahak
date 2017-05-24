
import java.io.*;
import java.net.*;
public class ashiatonikki
{
  public static void main(String[] args) throws Exception
  {
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
          int filesize=Integer.MAX_VALUE - 10; 
     	  int bytesRead; 
     	  int currentTot = 0; 
     	  byte [] bytearray = new byte [filesize]; 
     	 InputStream is = sock.getInputStream(); 
 		FileOutputStream fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") + "/Documents/" + receiveMessage); 
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
 			System.out.println("File received and saved from " + receiveMessage + ".");
 			} 
        }         
      }               
}                    
