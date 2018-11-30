package modelServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

/** Envio de microfono **/
public class UDPMulticastServer {

	private MulticastSocket multicastSocket;
	private InetAddress inetAddress;
	private byte[] data;
	private DatagramPacket packet;

	public UDPMulticastServer() {
		
		try {
			
		multicastSocket = new MulticastSocket();
	    inetAddress = InetAddress.getByName("228.5.6.6");
		multicastSocket.joinGroup(inetAddress);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public void send(String message) {
			
		data =  message.getBytes();
		packet =  new DatagramPacket(data, message.length(), inetAddress, 9877);
		
		try {		
			multicastSocket.send(packet);			
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
}
