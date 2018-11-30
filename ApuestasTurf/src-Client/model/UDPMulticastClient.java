package model;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import gui.ClientFrame;

public class UDPMulticastClient extends Thread {

	
	private MulticastSocket multicastSocket;
	InetAddress inetAddress;
	
	private boolean inBets;
	private boolean inRace;
	private boolean inWait;
	private int minutes;
	private int seconds;
	private int[] distancesHorses;
	private ClientFrame clientFrame;
	private String[] ranking;
	
	
	
	public UDPMulticastClient(ClientFrame frame) {
		try {
		clientFrame = frame;
		multicastSocket = new MulticastSocket(9877);
		inetAddress = InetAddress.getByName("228.5.6.6");
		multicastSocket.joinGroup(inetAddress);
		distancesHorses = new int[6];
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		try {		
			byte[] data =  new byte[256];
			DatagramPacket packet =  new DatagramPacket(data, data.length);
			
			while(true)
			{
				multicastSocket.receive(packet);
				String message = new String(packet.getData(), 0, packet.getLength());
				updateState(message);
				
				
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updateState(String message) {
		//System.out.println(message);
		String[] values = message.split("\\|");
		inWait = setBoolean(values[0]);
		inBets = setBoolean(values[1]);
		inRace = setBoolean(values[2]);
		minutes = Integer.parseInt(values[3]);
		seconds = Integer.parseInt(values[4]);
		
		
		if(inRace) {
			distancesHorses[0] = Integer.parseInt(values[5]);
			distancesHorses[1] = Integer.parseInt(values[6]);
			distancesHorses[2] = Integer.parseInt(values[7]);
			distancesHorses[3] = Integer.parseInt(values[8]);
			distancesHorses[4] = Integer.parseInt(values[9]);
			distancesHorses[5] = Integer.parseInt(values[10]);
			
			ranking = values[11].split("\\#");
			clientFrame.repaintAnimation();
		}
			clientFrame.repaintStreaming();
	}
	
	public boolean setBoolean(String bool) {
		if(bool.equals("true")) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isInBets() {
		return inBets;
	}

	public boolean isInRace() {
		return inRace;
	}

	public boolean isInWait() {
		return inWait;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public int[] getDistancesHorses() {
		return distancesHorses;
	}

	public String[] getRanking() {
		return ranking;
	}
	
	

}
