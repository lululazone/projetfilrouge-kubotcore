package kubot.core.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import kubot.core.utils.PropertiesLoader;

public class GUIManager extends JFrame{
	
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private PropertiesLoader props;
	private String firstScreen;
	private Theme theme;
	
	public GUIManager(PropertiesLoader props) throws IOException {
		this.props = props;
		
		if(!this.props.getDebug()) {
			enableBluetooth();			
		}
		theme = new Theme(props);
		setTitle("Kubot - Interface Graphique");
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0));
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        setupFrames();
        

        add(mainPanel);
        setVisible(true);

        
        new Timer(props.getBootDelay()*1000, e -> switchScreen(firstScreen)).start();
        
        
        
        
	}
	
	

	private void enableBluetooth() throws IOException {
		Process p = Runtime.getRuntime().exec("sudo systemctl enable bluetooth.service");
	}



	private void setupFrames() {
		BootFrame bootFrame = new BootFrame(this);
        LoadingFrame loadingScreen = new LoadingFrame(this);
        MainFrame mainScreen = new MainFrame(this);
        SetupFrame setupFrame = new SetupFrame(this);
        
        
		mainPanel.add(bootFrame,"Boot");
		if(!props.getSetupDone()) {
        	mainPanel.add(setupFrame,"Setup");
        	firstScreen = "Setup";
        }
		else {
			firstScreen = "MainScreen";
		}
        mainPanel.add(loadingScreen, "LoadingScreen");
        mainPanel.add(mainScreen, "MainScreen");
		
	}

	public void switchScreen(String dest) {
        cardLayout.show(mainPanel, dest);
    }
	
	public Theme getTheme() {
		return theme;
	}
	
	public PropertiesLoader getProps() {
		return this.props;
	}

    

}
