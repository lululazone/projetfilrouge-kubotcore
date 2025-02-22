package kubot.core.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SetupFrame extends JPanel{

	public SetupFrame(GUIManager guiManager) {
		// Configuration de la fenêtre
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Bienvenue sur Kubot !", SwingConstants.CENTER);
        JButton startButton = new JButton("Configurer le robot");

        
        add(label, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
	}

}
