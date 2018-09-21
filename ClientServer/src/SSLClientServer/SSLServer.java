package SSLClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class SSLServer {

	public static void main(String[] args) {

		try {
			
			SSLServerSocketFactory ssf  = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			ServerSocket server = ssf.createServerSocket(8000);
			
			System.out.println("SSL ServerSocket Started");
			
			try {
			Socket socket = server.accept();
			PrintWriter writerServer = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Client socket created");
			String line = null;
			
			while(((line = readerServer.readLine()) != null)) {
				System.out.println(line);
				writerServer.println(line);
			}
			writerServer.close();
			System.out.println("SSLServerSocket Terminated");
			
			}catch(IOException e) {
				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
