package main;

public class Chocolat {
	static boolean verboseLogging = false;
	public static void print(String sk) 
	{
		if (verboseLogging)
		{
			System.out.print(sk);
		}
	}
	public static void println(String sk) 
	{
		if (verboseLogging)
		{
			System.out.println(sk);
		}
	}
}