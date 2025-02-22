package kubot.core.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JPanel {

	public MainFrame(GUIManager guiManager) {
		// Configuration de la fenêtre
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Bienvenue sur Kubot !", SwingConstants.CENTER);
        JButton startButton = new JButton("Démarrer le robot");

        
        add(label, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);

        
	}
	
	

}
