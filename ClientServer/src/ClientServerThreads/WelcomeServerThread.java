package ClientServerThreads;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WelcomeServerThread extends Thread {
	
	Socket client;
	BufferedReader readerServer;
	PrintWriter writerServer;
	
	public WelcomeServerThread(Socket request) {
		super();
		client = request;
	}
	
	public void run() {
		try {
			readerServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writerServer = new PrintWriter(client.getOutputStream(),true);
			
			//Get name and host of user
			String name = readerServer.readLine();
			String host = readerServer.readLine();
			
			//Create message of responses and send to the client
			String message = "Hola " + name + " en " + host + " bienvenido(a) !!";
			writerServer.println(message);
			
			//Close streams and socket asociated to the request
			writerServer.close();
			readerServer.close();
			client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
