package model;

public class MainClient {

	public static final int ID_DEFECT = 99;
	private BetTCPClient betTCP;
	private Thread audioUDP;
	private int idHorse;
	private PlayMusic playMusic;

	
	public MainClient () {
		
		betTCP = new BetTCPClient();
		audioUDP = new AudioUDPClient();
		playMusic = new PlayMusic();
		playMusic.start();
		audioUDP.start();
		idHorse = ID_DEFECT;

	}
	
	
	public String bet(String name, String password, int horse, double apuesta) {	
		idHorse = horse;
		return betTCP.bet(name, password, horse, apuesta);
		
	}

	public int getIdHorse() {
		return idHorse;
	}
	
	
	
}
