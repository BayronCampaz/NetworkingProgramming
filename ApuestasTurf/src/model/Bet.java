package model;

import java.time.LocalDate;

public class Bet {

	private String horseBet;
	private double bet;
	private LocalDate date;
	private boolean horseWin;
	
	
	public Bet(String horseBet, double bet, LocalDate date, boolean horseWin) {
		super();
		this.horseBet = horseBet;
		this.bet = bet;
		this.date = date;
		this.horseWin = horseWin;
	}


	public String getHorseBet() {
		return horseBet;
	}


	public void setHorseBet(String horseBet) {
		this.horseBet = horseBet;
	}


	public double getBet() {
		return bet;
	}


	public void setBet(double bet) {
		this.bet = bet;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public boolean isHorseWin() {
		return horseWin;
	}


	public void setHorseWin(boolean horseWin) {
		this.horseWin = horseWin;
	}
	
	
}
