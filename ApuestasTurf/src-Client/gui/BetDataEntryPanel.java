package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BetDataEntryPanel extends JPanel implements ActionListener{

	private JLabel labelUser;
	private JLabel labelPassword;
	private JLabel labelBet;
	private JLabel labelHorse;
	private JButton btBet;
	private JComboBox<String> cbHorse;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtBet;
	private ClientFrame frame;
	
	
	
	public BetDataEntryPanel(ClientFrame frame) {
		
		this.frame = frame;
		setLayout(new GridLayout(5,2,10,10));
		
		labelUser = new JLabel("  Usuario");
		add(labelUser);
		
		txtUser = new JTextField();
		add(txtUser);
		
		labelPassword = new JLabel("  Contraseña");
		add(labelPassword);
		
		txtPassword = new JPasswordField();
		add(txtPassword);
		
		labelHorse = new JLabel("  Caballo a apostar");
		add(labelHorse);
		
		cbHorse = new JComboBox<String>();
		add(cbHorse);
		
		cbHorse.addItem("Sparky");
		cbHorse.addItem("Rocco");
		cbHorse.addItem("Coronel");
		cbHorse.addItem("Dragon");
		cbHorse.addItem("Sparrow");
		cbHorse.addItem("Jobs");
		
		
		
		labelBet = new JLabel("  Valor apuesta");
		add(labelBet);
		
		txtBet = new JTextField();
		add(txtBet);
		
		btBet = new JButton("Apostar");
		btBet.addActionListener(this);
		btBet.setActionCommand("Apostar");
		add(btBet);
		
		Font font = new Font("Helvetica", Font.PLAIN, 16);
		labelUser.setFont(font);
		txtUser.setFont(font);
		labelPassword.setFont(font);
		labelHorse.setFont(font);
		cbHorse.setFont(font);
		btBet.setFont(font);
		labelBet.setFont(font);
		txtBet.setFont(font);
		

		
		add(new JLabel(" "));
	}
	
	public void setEnableBtBet(boolean enabled) {
		btBet.setEnabled(enabled);
	}
	
	public String[] getValuesBet() {
		
		//Here exception
		String values[] = new String[4];
		values[0] = txtUser.getText();
		
		values[1] = new String(txtPassword.getPassword());
		
		int  horse = 0;
		switch((String)cbHorse.getSelectedItem()) 
        { 
            case "Sparky": 
                horse = 0;
                break; 
            case "Rocco": 
            	horse = 1;
                break; 
            case "Coronel": 
                horse = 2;
                break;
            case "Dragon": 
                horse = 3;
                break; 
            case "Sparrow": 
            	horse = 4;
                break; 
            case "Jobs": 
                horse = 5;
                break;
            default: 
                System.out.println("no match"); 
        }  
		
		values[2] = ""+horse;
		values[3] = txtBet.getText();
		
		return values;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command.equals("Apostar")) {
			frame.bet();
		}
		
	}
}
