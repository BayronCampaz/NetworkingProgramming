package modelServer;
import java.io.BufferedReader;
import model.*;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** Clase ayudante para el manejo del servidor web que administra la conexion con la pagina web **/
public class ClientHandler implements Runnable{
	
	private final Socket socket;

	public ClientHandler(Socket socket)
	{
		this.socket =  socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("\nClientHandler Started for " + this.socket);
		
		while(true) 
		{
			handleRequest(this.socket);
		}
	}
	
	public void handleRequest(Socket socket)
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			
			if(headerLine != null)
			{
				System.out.println(headerLine);
				// A tokenizer is a process that splits text into a series of tokens
				StringTokenizer tokenizer =  new StringTokenizer(headerLine);
				//The nextToken method will return the next available token
				String httpMethod = tokenizer.nextToken();
				// The next code sequence handles the GET method. A message is displayed on the
				// server side to indicate that a GET method is being processed
				if(httpMethod.equals("GET"))
				{
					System.out.println("Get method processed");
					String httpQueryString = tokenizer.nextToken();
					System.out.println(httpQueryString);
					if(httpQueryString.equals("/"))
					{
						StringBuilder responseBuffer =  new StringBuilder();
						String str="";
						BufferedReader buf = new BufferedReader(new FileReader(System.getProperty("user.dir") +"/src/web/PaginaWebTurf.html"));
						while ((str = buf.readLine()) != null) {
							responseBuffer.append(str);
					    }
						sendResponse(socket, 200, responseBuffer.toString());		
					    buf.close();
					}
					if(httpQueryString.contains("/?user="))
					{
						System.out.println("Get method processed");
						String[] response =  httpQueryString.split("user=");
						String[] userAndPassword = response[1].split("&password=");
						StringBuilder responseBuffer =  newPage(userAndPassword[0], userAndPassword[1]);
						
						sendResponse(socket, 200, responseBuffer.toString());		
					    
					}
										    
				}
				
				else
				{
					System.out.println("The HTTP method is not recognized");
					sendResponse(socket, 405, "Method Not Allowed");
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 		}		
					
	}
	
	public StringBuilder newPage(String id, String password) {
		
		StringBuilder page = new StringBuilder();
		
		try {			
			ArrayList<Bet> bets = MainServer.dataBase.betOfUser(id, password);
			
			page
			.append("<html>")
			.append("<head> <title> Registro </title> "
					+ "<style>"
					+ "</style>"
					+ "</head> ")
			.append("<body bgcolor='black'>")
			.append("<p style='color:white'> ID | CABALLO | APUESTA | FECHA | ¿GANO? </p>");
			
			for(int j = 0; j < bets.size(); j++) {
				String gano = ""; 
				if(bets.get(j).isHorseWin()){
					gano = "Ganada";
				}else {
					gano = "Perdida";
				}
				page.append("<p style='color:white'> " + id + " | " + bets.get(j).getHorseBet() + " | " + bets.get(j).getBet()  + " | " + bets.get(j).getDate() + " | " + gano + " </p>");
			}
			
			page
			.append("</body>")
			.append("</html>");
			
			
		} catch (NotExistUserException e) {
			
			try {
			String str="";
			BufferedReader buf = new BufferedReader(new FileReader(System.getProperty("user.dir") +"/src/web/PaginaWebTurf3.html"));
			while ((str = buf.readLine()) != null) {
				page.append(str);
		    }
			
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
		}catch (WrongPasswordException e) {
			try {
				String str="";
				BufferedReader buf = new BufferedReader(new FileReader(System.getProperty("user.dir") +"/src/web/PaginaWebTurf2.html"));
				while ((str = buf.readLine()) != null) {
					page.append(str);
			    }
				}catch(Exception e1) {
					e1.printStackTrace();
				}
		}
		
		return page;
	}
	
	public void sendResponse(Socket socket, int statusCode, String responseString)
	{
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";
		
		try {
			DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) 
			{
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: "
				+ responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
				} 
			else if (statusCode == 405) 
			{
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} 
			else 
			{
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			//out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
