package kubot.core.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BootFrame extends JPanel{

	public BootFrame(GUIManager guiManager) {
		setBackground(new Color(0,0,0));
        setLayout(new BorderLayout());
        // add the image label
        ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("images/kubotboot.gif"));
        
        
        JLabel imageLabel = new JLabel(ii,SwingConstants.CENTER);
        
        imageLabel.setMaximumSize(new Dimension(50,50));
        //loadingText.setFont();
        add(imageLabel, java.awt.BorderLayout.CENTER);
        
	}

}
