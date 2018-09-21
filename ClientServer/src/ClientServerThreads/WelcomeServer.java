package ClientServerThreads;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WelcomeServer {

	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(8030);
			while(true) {
				
				Socket c = server.accept();
				WelcomeServerThread thread = new WelcomeServerThread(c);
				thread.start();
				
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

}
