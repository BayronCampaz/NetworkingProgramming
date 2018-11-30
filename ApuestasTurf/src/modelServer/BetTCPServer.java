package modelServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

/**Clase de la conexion TCP**/
public class BetTCPServer extends Thread {
	
	public static  double[] horses = new double[6];
	
	public static final String KEYSTORE_LOCATION = "C:\\Program Files (x86)\\Java\\jdk1.8.0_172\\bin\\keystore.jks";
	public static final String KEYSTORE_PASSWORD = "password";
	
	private ServerSocket server;

	
	public BetTCPServer() {
		
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			server = ssf.createServerSocket(8030);
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}

	public void run() {
		try {        	
			    	while(true) {
											
						Socket c;						
						c = server.accept();
						BetTCPServerThread thread = new BetTCPServerThread(c);
						thread.start();						
			    	}		
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	}
	
	public void resetBets() {
		
		for(int i = 0; i<horses.length; i++) {
			horses[i] = 0.0;
		}			
	}
}
