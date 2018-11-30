package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import model.UDPMulticastClient;

public class StreamingPanel extends JPanel {

	private ClientFrame frame;
	public boolean alreadyBet;
	
	public StreamingPanel(ClientFrame frame) {
		this.frame = frame;
		alreadyBet = false;
	}
	
public void  paintComponent(Graphics g){
		
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		
		g2.addRenderingHints( new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON ) );
		
			
		UDPMulticastClient multicast = frame.getMulticast();
		
		Font font = new Font("Helvetica", Font.PLAIN, 25);
		g2.setFont(font);
		g2.drawString("Faltan: ", 147, 30);
		
		Font font2 = new Font("Helvetica", Font.BOLD, 50);
		g2.setFont(font2);			
		g2.drawString(showClock(multicast.getMinutes(), multicast.getSeconds()), 123, 90);	
		
		Font font3 = new Font("Helvetica", Font.PLAIN, 25);
		g2.setFont(font3);			
		
		if(multicast.isInWait()) {	
			frame.setEnableBtBet(false);			
			if(multicast.isInBets()) {
				alreadyBet = false;
				g2.drawString("Para iniciar apuestas", 80, 130);
			}else {
				g2.drawString("Para iniciar la carrera", 80, 130);
			}
			
		}else {
			
			if(multicast.isInBets()) {
				if(!alreadyBet) {
					frame.setEnableBtBet(true);
				}
				g2.drawString("Para que se cierren las apuestas", 17, 130);
			}else if(multicast.isInRace()){
				g2.drawString("Para que termine la carrera", 40, 130);
			}
		}		
						
	}

	public String showClock(int minutes, int seconds) {
		String result = "";
		
		if(seconds >= 10) {
			result = "" + minutes + " : " + seconds;
		}else {
			result = "" + minutes + " : " + "0" + seconds;
		}
		
		return result;
	}


}
