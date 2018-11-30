package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.MainClient;
import model.UDPMulticastClient;

public class ClientFrame extends JFrame {

	private MainClient mainClient;
	private ImagePanel imagePanel;
	private BetDataEntryPanel betData;
	private StreamingPanel streamingPanel;
	private AnimationPanel animationPanel;
	private UDPMulticastClient multicastClient;
	private String nameHorseChose;
	
	public ClientFrame() {
		
		setTitle( "Simulation Surf" );
        setSize( new Dimension(850, 650 ));
       
        setResizable( false);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout(20,20 ) );
        
        imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.NORTH);
        
        JPanel panelAux = new JPanel();
        panelAux.setLayout(new GridLayout(2,1,50,50));
        JPanel panelAux2 = new JPanel();
        panelAux2.setLayout(new GridLayout(1,2));
        
        betData = new BetDataEntryPanel(this);
        streamingPanel = new StreamingPanel(this);
        animationPanel = new AnimationPanel(this);
        
        panelAux2.add(betData);
        panelAux2.add(streamingPanel);
        
        panelAux.add(panelAux2);
        panelAux.add(animationPanel);
        
		multicastClient = new UDPMulticastClient(this);
		multicastClient.start();
        
        
        add(panelAux,BorderLayout.CENTER);
        		
		mainClient = new MainClient();
	}
	
	public void bet() {
		
		String values[] = betData.getValuesBet();
		String name = values[0];
		String password = values[1];
		int horse = Integer.parseInt(values[2]);
		double apuesta = Double.parseDouble(values[3]);
		
		String result = mainClient.bet(name, password, horse, apuesta);		
		JOptionPane.showMessageDialog(null, result, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		if(!result.equals("Contraseña incorrecta")) {
			betData.setEnableBtBet(false);
			streamingPanel.alreadyBet = true;
		
		
		int  horse2 = mainClient.getIdHorse();
		switch(horse2) 
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
	 }
	}
	
	public void setEnableBtBet(boolean enable) {
		betData.setEnableBtBet(enable);
	}
	
	
	public void repaintStreaming() {
		streamingPanel.repaint();
	}
	
	public void repaintAnimation() {
		animationPanel.repaint();
	}
	
	public UDPMulticastClient getMulticast() {
		return multicastClient;
	}
	
	public int getHorseChose() {
		return mainClient.getIdHorse();
	}
	
	public String nameHorseChose() {
		return nameHorseChose;
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new ClientFrame();
		frame.setVisible(true);
	}

}
