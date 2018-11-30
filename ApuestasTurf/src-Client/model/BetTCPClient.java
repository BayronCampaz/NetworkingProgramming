package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class BetTCPClient  {

	public static final String TRUSTTORE_LOCATION = "C:\\Program Files (x86)\\Java\\jdk1.8.0_172\\bin\\keystore.jks";
	

	public String bet(String name, String password, int horse, double apuesta) {
		
		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		Socket client;
		BufferedReader readerClient;
		PrintWriter writerClient;
		String response = "Ha sucedido un error"; 


		try {
			//Create the socket and stream asociated
			
			client = sf.createSocket("localhost", 8030);
			readerClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writerClient = new PrintWriter(client.getOutputStream(), true);
		
			//Send data to server
			writerClient.println(name);
			writerClient.println(password);
			writerClient.println(horse);
			writerClient.println(apuesta);
			
			//Get the response of server and show for cmd
			response = readerClient.readLine();
			return response;
									
			//Close streams and close the socket			
			
		}catch(IOException e) {
			
			e.printStackTrace();
			return response;
		}

	}
		
		
}
