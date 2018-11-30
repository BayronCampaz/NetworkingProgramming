package modelServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Bet;
import model.User;
/** Clase manejadora de la base de datos **/
public class DataBase {

	public final static String PATH = "C:\\Users\\bayro\\eclipse-workspace\\ApuestasTurf\\src\\data\\fileData.txt";
	private ArrayList<User> users;
	
	public DataBase() {
		users = new ArrayList<User>();
		load(PATH);
	}
	
	
    public void load(String file) {
        try {
        	
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            while(line != null) {
            	
            	String[] data = line.split("\\|");
            	String id = data[0];
            	String password = data[1];
            	String horse = data[2];
            	double bet = Double.parseDouble(data[3]);
            	LocalDate date = LocalDate.parse(data[4]);
            	boolean win = data[5].equals("true");
            	
            	boolean ended = false;
            	for(int i = 0; i< users.size() && !ended; i++) {
            		
            		if(users.get(i).getId().equals(id)) {
            			users.get(i).addBet(horse, bet, date, win);
            			ended = true;
            		}
            	}
            	
        		if(!ended) {
        			addUser(id, password, horse, bet, date, win);	
        		}
            	
            	line =	br.readLine();
            }
            
            br.close();
            fr.close(); 
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    
    
    public String saveInDatabase(String id, String password,  String horse, double bet, LocalDate date, boolean win) {
    	
    	String result = "";
    	
    	boolean ended = false;
    	boolean toRegister = true;
    	for(int i = 0; i< users.size() && !ended; i++) {
    		
    		if(users.get(i).getId().equals(id)) {
    			
    			ended = true;
    			
    			if(users.get(i).getPassword().equals(password)) {
    				users.get(i).addBet(horse, bet, date, win);	
    				result = "Informacion Registrada Correctamente";
    			}else {
    				result ="Contraseña incorrecta";
    				toRegister = false;
    			}
    		}
    		
    	}
    	
		if(!ended) {
			addUser(id, password, horse, bet, date, win);	
			result = "El usuario no existia, hemos creado uno y se ha realizado la apuesta corrrectamente";
		}
		
		if(toRegister) {
		
		BufferedWriter bw = null;
	    FileWriter fw = null;

	    try {
	        String data = "\n" + id + "|" + password + "|" + horse + "|" + bet + "|" + date + "|" + win  ;
	        File file = new File("C:\\Users\\bayro\\eclipse-workspace\\ApuestasTurf\\src\\data\\fileData.txt");
	        fw = new FileWriter(file.getAbsoluteFile(), true);
	        bw = new BufferedWriter(fw);
	        bw.write(data);

	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	                        //Cierra instancias de FileWriter y BufferedWriter
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	      }
		}
    	
	    return result;
    }

	public void addUser(String id, String password, String horseBet, double bet, LocalDate date, boolean horseWin) {
		User user = new User(id, password);
		user.addBet(horseBet, bet, date, horseWin);
		users.add(user);
	}
	
	public boolean isInDataBase(String id) {
		boolean result = false;
		
		for(int i = 0; i< users.size() && !result; i++) {
			
			if(users.get(i).getId().equals(id)) {
				result = true;
			}
		}
		
		return result;
	}
	
	public ArrayList<Bet> betOfUser(String id, String password) throws NotExistUserException, WrongPasswordException {
		ArrayList<Bet> result = null;
		User user = null;
		for(int i = 0; i< users.size() && user == null; i++) {
			
			if(users.get(i).getId().equals(id)) {
				user = users.get(i);
			}
		}
		if(user == null) {
			throw new NotExistUserException();
		}else {	
			if(!user.getPassword().equals(password)) {
				throw new WrongPasswordException();
			}else {
				result = user.getBets();
			}
		}
		
		return result;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
