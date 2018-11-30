package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Race;
import model.UDPMulticastClient;

public class AnimationPanel extends JPanel {

	private ClientFrame frame;
	public boolean alreadyBet;
	
	public AnimationPanel(ClientFrame frame) {
		this.frame = frame;
		alreadyBet = false;
	}
		
	public void  paintComponent(Graphics g){
		
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		
		g2.addRenderingHints( new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON ) );
				
		UDPMulticastClient multicast = frame.getMulticast();
			
		Font font3 = new Font("Helvetica", Font.PLAIN, 18);
		g2.setFont(font3);			
		
		if(multicast.isInWait()) {	
						
			if(multicast.isInRace()) {
				
				for(int i = 0; i< multicast.getRanking().length; i++) {

					if(frame.nameHorseChose() != null) {
					if(multicast.getRanking()[i].contains(frame.nameHorseChose())) {					
						g2.setColor(Color.GREEN);
					}
					}
					g2.drawString(multicast.getRanking()[i], 600, 30*(i+1));
					g2.setColor(Color.BLACK);
					
				} 
					
				g2.drawLine(50 + Race.GOAL, 0, Race.GOAL + 50, 200);
				
				for(int i = 0; i< multicast.getDistancesHorses().length; i++) {
					if(frame.getHorseChose()==i) {
						g2.setColor(Color.GREEN);
					}
					g2.drawLine(50, 30*(i+1) , multicast.getDistancesHorses()[i] + 50 , 30*(i+1) );
				
					g2.setColor(Color.BLACK);
				}
				
				for(int i = 0; i< multicast.getDistancesHorses().length; i++) {
					if(frame.getHorseChose()==i) {
					Image img = new ImageIcon("C:\\Users\\bayro\\Downloads\\imag2 (2).png").getImage();
				    g2.drawImage(img, multicast.getDistancesHorses()[i] + 50, 30*(i), 65, 30, this);	
					}else {
						Image img = new ImageIcon("C:\\Users\\bayro\\Downloads\\imag2 (1).png").getImage();
					    g2.drawImage(img, multicast.getDistancesHorses()[i] + 50, 30*(i), 65, 30, this);
					}
				}
			}
			
			}else {
				
				if(multicast.isInRace()) {
					
					for(int i = 0; i< multicast.getRanking().length; i++) {
						if(frame.nameHorseChose()!= null) {
						if(multicast.getRanking()[i].contains(frame.nameHorseChose())) {					
							g2.setColor(Color.GREEN);
						}
						}
						g2.drawString(multicast.getRanking()[i], 600, 30*(i+1));
						
						g2.setColor(Color.BLACK);		
					}
					
					g2.drawLine(50 + Race.GOAL, 0, Race.GOAL + 50, 200);
					for(int i = 0; i< multicast.getDistancesHorses().length; i++) {
						if(frame.getHorseChose()==i) {
							g2.setColor(Color.GREEN);
						}		
						g2.drawLine(50, 30*(i+1) , multicast.getDistancesHorses()[i]+ 50 , 30*(i+1) );
						g2.setColor(Color.BLACK);
				}
					
					for(int i = 0; i< multicast.getDistancesHorses().length; i++) {

						Image img = new ImageIcon("C:\\Users\\bayro\\Downloads\\imag.gif").getImage();
					    g2.drawImage(img, multicast.getDistancesHorses()[i] + 50, 30*(i), 60, 40, this);			    					
					}
					
					}
				}									
	}
}
