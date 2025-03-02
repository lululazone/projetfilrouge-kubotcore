package kubot.core.gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SetupFrame extends JPanel {

    public SetupFrame(GUIManager guiManager) {
        // Configuration de la fenêtre
        setLayout(new BorderLayout()); // Permet un meilleur positionnement
        setBackground(guiManager.getTheme().getBackgroundColor());

        
        JLabel label = new JLabel("<html><center>Bienvenue sur Kubot !<br>"
                + "Pour commencer la configuration du robot,<br>"
                + "veuillez télécharger l'application Kubot sur Android.</center></html>",
                SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 24)); // Augmenter la taille du texte
        label.setForeground(new Color(255,255,255));
        label.setPreferredSize(new Dimension(800, 150));

        
        ImageIcon gplayIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/gplay.png"));
        
        
        Image img = gplayIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH); // Redimensionner
        ImageIcon resizedIcon = new ImageIcon(img);
        
        JLabel gplayLabel = new JLabel(resizedIcon, SwingConstants.CENTER);
        gplayLabel.setPreferredSize(new Dimension(150, 50)); // Définir une taille adaptée

        
        JPanel textPanel = new JPanel();
        textPanel.setBackground(guiManager.getTheme().getBackgroundColor());
        textPanel.add(label);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(guiManager.getTheme().getBackgroundColor());
        imagePanel.add(gplayLabel);

        add(textPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
        
        if(!guiManager.getProps().getDebug()) {
        	new Timer(10000, e -> reEnableBluetoothDiscoverable()).start();        	
        }
    }

	private void reEnableBluetoothDiscoverable() {
		try {
			Process p = Runtime.getRuntime().exec("sudo hciconfig hci0 piscan");
			p.waitFor();
			System.out.println("Bluetooth is discoverable");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
