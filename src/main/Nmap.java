package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Nmap 
{
	 public static void checkHosts(String subnet, int mode) throws IOException
	 {
		   int timeout=1000;
		   for (int i=1;i<255;i++){
		       String host=subnet + "." + i;
		       if (InetAddress.getByName(host).isReachable(timeout)){
		           Chocolat.println(host + " is reachable");
		           if(mode == 1) {
		        	   ScanP(1, host);
		           }
		       }
		   }
		   System.out.println("Scan is finished");
		}
	   public static void ScanP(int mode, String url) {
			   if(!url.matches("127.0.0.1")) {
				   Chocolat.println("Beginning port scan for IP " + url);
			   } else {
				   Chocolat.println("Beginning port scan on local system...");
			   }
			   for (int port = 1; port <= 65535; port++) {
			         try {
			            Socket socket = new Socket();
			            socket.connect(new InetSocketAddress(url, port), 100);
			            socket.close();
			            Chocolat.println("Port " + port + " is open");
			        } catch (Exception ex) {
			        }
			  }
	   }
}
