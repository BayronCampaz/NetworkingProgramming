package modelServer;

import model.Race;

/** Clase que administra todos los servicios web **/
public class MainServer {
	
	
	public static final int TIME_WAIT = 20;
	public static final int TIME_BETS = 60;
	public static final int TIME_RACE = 60;
	private BetTCPServer betTCP;
	private Thread audioUDP;
	private UDPMulticastServer dataMulticast;
	private Race race;
	private boolean inBets;
	private boolean inRace;
	private boolean inWait;
	private int minutes;
	private int seconds;
	
	public static DataBase dataBase;
	
	
	public MainServer() {
		
		betTCP = new BetTCPServer();
		audioUDP = new AudioUDPServer();
		race = new Race();
		dataMulticast = new UDPMulticastServer();	
		dataBase = new DataBase();
		manageServer();
		
	}
	
	public void advanceTime() {
		
		if(minutes>0) {	
			if(seconds > 0) {
				seconds--;
			}else if(seconds == 0){
				seconds = 59;
				minutes--;
			}	
		}else {
			if(seconds > 0) {
				seconds--;
			}else {
				changeStatus();
			}			
		}	
	}
	
	public void changeStatus() {
		
		if(!inWait) {
			
		if(inBets) {
			inBets = false;
			seconds = TIME_WAIT;
			inWait = true;
			inRace = true;
			
		}else if(inRace) {
			inRace = false;
			seconds = TIME_WAIT;
			inWait = true;
			inBets = true;
		}
			
		}else {
			inWait = false;
			minutes = 1;
			seconds = 0;
		}
	}
		
	public void startRace() {
		
		if(audioUDP.isAlive()) {
			audioUDP.resume();
		}else {
		audioUDP.start();
		}
				
		race.startRace();
		
	}
	
	public String dataToSend() {
		String result = "" ;
		
		result += inWait + "|" + inBets + "|" + inRace + "|" + minutes + "|" + seconds;
		
		if(inRace) {
			result += race.getDistances();
			result += "|" + race.getRanking();
		}
		
		return result;
		
	}
	
	
	public void manageServer() {
		
		minutes = 1;
		seconds = 0;
		
		while(true) {
			
			try {
			if(betTCP.isAlive()) {
				betTCP.resume();
			}else  {
				betTCP.start();
			}
			inBets = true;
			betTCP.resetBets();
			
			for(int i = 0; i<TIME_BETS; i++) {
				
				Thread.sleep(1000);
				advanceTime();
				String data = dataToSend();
				dataMulticast.send(data);			
			}
			
		     for(int i = 0; i< betTCP.horses.length; i++) {
		    	 System.out.println("Por el caballo " + nameHorse(i) + " " + "se aposto "+ betTCP.horses[i]);
		     }
		     
		     betTCP.suspend();
		     
				for(int i = 0; i<TIME_WAIT; i++) {
					Thread.sleep(1000);
					advanceTime();
					String data = dataToSend();
					dataMulticast.send(data);			
				}
		     
		     startRace();
		     
				for(int i = 0; i<TIME_RACE*2; i++) {
					Thread.sleep(500);
					if(i%2 == 0) {
					advanceTime();
					}
					String data = dataToSend();
					dataMulticast.send(data);			
				}
		     
				advanceTime();
				advanceTime();

		     race = new Race();
		     audioUDP.suspend();
		     
		     
				for(int i = 0; i<TIME_WAIT; i++) {
					Thread.sleep(1000);
					advanceTime();
					String data = dataToSend();
					dataMulticast.send(data);			
				}
						     
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String nameHorse (int horse) {
		String nameHorseChose = "";
		switch(horse) 
        { 
            case 0:               
            	nameHorseChose = "Sparky";
                break; 
            case 1:
            	nameHorseChose = "Rocco";
                break; 
            case 2:
            	nameHorseChose = "Coronel"; 
                
                break;
            case 3 :
            	nameHorseChose =  "Dragon"; 
                
                break; 
            case 4:
            	nameHorseChose = "Sparrow"; 
            	
                break; 
            case 5:
            	nameHorseChose = "Jobs"; 
                
                break;
            default: 
            	nameHorseChose = "OOOOO";
        }  
		return nameHorseChose;
	}
	

	public static void main(String[] args) {
		new MainServer();
	}
}
