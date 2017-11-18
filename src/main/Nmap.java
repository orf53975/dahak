package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Nmap 
{
	 public static ArrayList<String> checkHosts(String subnet, int mode) throws IOException
	 {
		   ArrayList<String> hosts = new ArrayList<>();
		   int timeout=1000;
		   for (int i=1;i<255;i++)
		   {
		       String host=subnet + "." + i;
		       if (InetAddress.getByName(host).isReachable(timeout))
		       {
		    	   hosts.add(host);
		           Chocolat.println(host + " is reachable");
		           if(mode == 1)
		           {
		        	   portScan(1, host);
		           }
		       }
		   }
		   System.out.println("Scan is finished");
		   return hosts;
		}
	   public static ArrayList<Integer> portScan(int mode, String url)
	   {
		   	   ArrayList<Integer> ports = new ArrayList<>();
			   if(!url.matches("127.0.0.1"))
			   {
				   Chocolat.println("Beginning port scan for IP " + url);
			   }
			   else 
			   {
				   Chocolat.println("Beginning port scan on local system...");
			   }
			   for (int port = 1; port <= 65535; port++)
			   {
			         try 
			         {
			            Socket socket = new Socket();
			            socket.connect(new InetSocketAddress(url, port), 100);
			            socket.close();
			            ports.add(port);
			            Chocolat.print("Port " + port + " is open");
			            if (port == 21)
			            {
			            	Chocolat.print(" (ftp) \n"); 
			            }
			            else if (port == 22)
			            {
			            	Chocolat.print(" (ssh) \n");
			            }
			            else if (port == 23)
			            {
			            	Chocolat.print(" (telnet) \n");
			            }
			            else if (port == 25)
			            {
			            	Chocolat.print(" (smtp) \n");
			            }
			            else if (port == 42)
			            {
			            	Chocolat.print(" (wins replication) \n");
			            }
			            else if (port == 43)
			            {
			            	Chocolat.print(" (whois) \n");
			            }
			            else if (port == 49)
			            {
			            	Chocolat.print(" (tacacs) \n");
			            }
			            else if (port == 53)
			            {
			            	Chocolat.print(" (dns) \n");
			            }
			            else if (port == 80)
			            {
			            	Chocolat.print(" (http) \n");
			            }
			            else if (port == 88)
			            {
			            	Chocolat.print(" (kerberos) \n");
			            }
			            else if (port == 102)
			            {
			            	Chocolat.print(" (msexchange) \n");
			            }
			            else if (port == 110)
			            {
			            	Chocolat.print(" (pop3) \n");
			            }
			            else if (port == 135)
			            {
			            	Chocolat.print(" (rpc) \n");
			            }
			            else if (port == 137)
			            {
			            	Chocolat.print(" (nbns) \n");
			            }
			            else if (port == 389)
			            {
			            	Chocolat.print(" (ldap) \n");
			            }
			            else if (port == 420)
			            {
			            	Chocolat.print(" (woah man....... woah dude.... trippy stuff man) \n");
			            }
			            else if (port == 443)
			            {
			            	Chocolat.print(" (https) \n");
			            }
			            else if (port == 445)
			            {
			            	Chocolat.print(" (microsoft-ds) \n");
			            }
			            else if (port == 464)
			            {
			            	Chocolat.print(" (kerberos) \n");
			            }
			            else if (port == 5432)
			            {
			            	Chocolat.print(" (mysql) \n");
			            }
			            else
			            {
			            	Chocolat.print("\n");
			            }
			        } 
			         catch (Exception ex) {}
			  }
			   return ports;
	   }
}
