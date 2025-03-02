package kubot.core.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoadingFrame extends JPanel{
	
	
	
	JLabel headerLabel = new JLabel();

	public LoadingFrame(GUIManager guiManager) {
        setBackground(new Color(0,0,0));
        setLayout(new BorderLayout());
        // add the image label
        ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource(
                "images/loading.gif"));
        
        JLabel imageLabel = new JLabel(ii,SwingConstants.CENTER);
        
        JLabel loadingText = new JLabel("Chargement... Veuillez patienter...",SwingConstants.CENTER);
        //loadingText.setFont();
        add(imageLabel, java.awt.BorderLayout.CENTER);
        add(loadingText,java.awt.BorderLayout.NORTH);
        
        
	}

}
