package model;

import java.util.Random;

public class Horse extends Thread implements Comparable<Horse>{

	public final static int BASE_VELOCITY = 8;
	
	private int id;
	private String name;
	private int distanceTraveled;
	private int velocity;
	
	
	public Horse(int id, String name, int distanceTraveled, int velocity) {
		super();
		this.id = id;
		this.name = name;
		this.distanceTraveled = distanceTraveled;
		this.velocity = velocity;
	}


	public String getNameHorse() {
		return name;
	}

	public void setNameHorse(String name) {
		this.name = name;
	}

	public void setDistanceTraveled(int distance) {
		this.distanceTraveled = distance;
	}


	public int getVelocity() {
		return velocity;
	}


	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}


	public int getIdHorse() {
		return id;
	}
	
	public void run() {
		
		while(!Race.ended && distanceTraveled < Race.GOAL) {
			distanceTraveled += velocity;
			
			if(distanceTraveled >= Race.GOAL) {
				Race.ended = true;
			}
			try {
				Horse.sleep(1000);
				Random random = new Random();
				velocity = (int) ((int) BASE_VELOCITY +  (random.nextDouble()*BASE_VELOCITY)) ;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	
	@Override
	public int compareTo(Horse o) {
		// TODO Auto-generated method stub
		int result = 0;
		if(this.distanceTraveled > o.distanceTraveled ) {
			return -1;
		}else if(!(this.distanceTraveled  > o.distanceTraveled )) {
			return 1;
		}
		return result;
	}
	
}
