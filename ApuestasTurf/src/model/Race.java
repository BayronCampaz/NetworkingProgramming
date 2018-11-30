package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Race {
	
	public final static int BASE_VELOCITY = 8;
	public final static int GOAL = 450;
	private ArrayList<Horse> horses;
	public static volatile boolean ended;
	private static Object[] horsesArray;

	public Race () {
		
		horses = new ArrayList<Horse>();
		
		Random rand = new Random();
		
		int velocity =  (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse1 = new Horse(1, "Sparky", 0, velocity );
		
		velocity = (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse2 = new Horse(2, "Rocco", 0, velocity );
		
		velocity = (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse3 = new Horse(3, "Coronel", 0, velocity );
		
		velocity = (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse4 = new Horse(4, "Dragon", 0, velocity );
		
		velocity = (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse5 = new Horse(5, "Sparrow", 0, velocity );
		
		velocity = (int) ((int) BASE_VELOCITY +  (rand.nextDouble()*BASE_VELOCITY)) ;
		Horse horse6 = new Horse(6, "Jobs", 0, velocity );

		horses.add(horse1);
		horses.add(horse2);
		horses.add(horse3);
		horses.add(horse4);
		horses.add(horse5);
		horses.add(horse6);
		
	}
	
	public void startRace() {
		ended = false;
		for(Horse horse : horses) {
			horse.start();
			
		}		
	}
	
	
	public String getDistances() {
		String result = "";;
		for(int i = 0; i<horses.size(); i++) {
			result += "|" + horses.get(i).getDistanceTraveled();
		}
		
		return result;
		
	}
	
	public String getRanking() {
		
		String ranking = "";
				
			horsesArray =  horses.toArray();
			Arrays.sort(horsesArray);
		
		for(int i = 0; i< horsesArray.length; i++) {
			Horse horse = (Horse) horsesArray[i];
			
			if(ended && i == 0) {
				ranking +=   (i+1) + ". " +horse.getNameHorse()+ " >> GANADOR " +"#";
			}else {
				ranking +=   (i+1) + ". " +horse.getNameHorse()+ " >> Falta: " + (GOAL - horse.getDistanceTraveled())+"#" ; 
			}			
		}
		
		return ranking;
	}
}
