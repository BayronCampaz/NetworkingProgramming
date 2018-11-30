package modelServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
/** Clase ayudante en la conexion TCP por donde se hacen as apuestas **/
public class BetTCPServerThread extends Thread {
	
	Socket client;
	BufferedReader readerServer;
	PrintWriter writerServer;
	
	
	public BetTCPServerThread(Socket request) {
		super();
		client = request;
		
	}
		
	public void run() {
		try {
						
			readerServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writerServer = new PrintWriter(client.getOutputStream(),true);
			
			//Get name and host of user
			String name = readerServer.readLine();
			String password = readerServer.readLine();
			int horse = Integer.parseInt(readerServer.readLine());
			double bet = Double.parseDouble(readerServer.readLine());
			
			//ESTO TOCA CAMBIARLO
			boolean isWin = false;
			LocalDate date = LocalDate.now();
					
			//Create message of responses and send to the client
			String message = MainServer.dataBase.saveInDatabase(name, password, horse+"", bet, date, isWin);
			writerServer.println(message); 
			
			if(!message.equals("Contraseña incorrecta")) {
				BetTCPServer.horses[horse] += bet;
			}		
			//Close streams and socket asociated to the request
			writerServer.close();
			readerServer.close();
			client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
