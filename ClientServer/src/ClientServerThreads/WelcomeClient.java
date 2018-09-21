package ClientServerThreads;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WelcomeClient {

	public static void main(String[] args) {
		
		Socket client;
		BufferedReader readerClient;
		PrintWriter writerClient;
		String name, response, host;
		int port;
		
		try {
			//Create the socket and stream asociated
			client = new Socket("127.0.0.1",8030);
			readerClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writerClient = new PrintWriter(client.getOutputStream(), true);
			
			//Get the name of user and its host
			host = client.getLocalAddress().getHostName();
			port = client.getPort();
			System.out.println("Digite su nombre");
			BufferedReader readerEntry = new BufferedReader(new InputStreamReader(System.in));
			name = readerEntry.readLine();
			
			//Send data to server
			System.out.println(port);
			writerClient.println(name);
			writerClient.println(host + "(puerto:"+ port + ")");
			
			//Get the response of server and show for cmd
			response = readerClient.readLine();
			System.out.println(response);
			
			//Close streams and close the socket
			readerClient.close();
			writerClient.close();
			
		}catch(IOException e) {
			
		}

	}

}
