package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {

	private String id;
	private String password;
	private ArrayList<Bet> bets;
	
	
	public ArrayList<Bet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Bet> bets) {
		this.bets = bets;
	}

	public User (String id, String password) {
		bets = new ArrayList<Bet>();
		this.setId(id);
		this.setPassword(password);
	}
	
	public void  addBet(String horseBet, double bet, LocalDate date, boolean horseWin) {		
		Bet theBet = new Bet(horseBet, bet, date, horseWin);
		bets.add(theBet);
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
