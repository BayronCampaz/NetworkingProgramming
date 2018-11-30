package gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/** Etiqueta para ubicar la imagen*/	
	public JLabel lbImage;
	
	/** Icono para agregarla a la etiqueta*/
	public ImageIcon icon;
	
	 
	/**
     * Crea el panel imagen*/	
	public ImagePanel( )	   
	 {
	        lbImage = new JLabel( );
	         icon = new ImageIcon( "imgs/banner.png" );
	        lbImage.setIcon( icon );
	      
	        //Establece el layout como una grilla de 1 fila y 1 columnas
			setLayout( new GridLayout( 1, 1) );
	        add( lbImage );	        
	    }

}

