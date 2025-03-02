package kubot.core.gui;

import javax.swing.*;
import kubot.core.utils.WifiSignal;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MainFrame extends JPanel {
    private String date;
    private JLabel dateLabel;
    private JLabel wifiSignal;
    private RobotEyesPanel eyesPanel;

    public MainFrame(GUIManager guiManager) {
        // Configuration de la fenêtre
        setLayout(new BorderLayout());
        setBackground(guiManager.getTheme().getBackgroundColor());

        JButton startButton = new JButton("Démarrer le robot");

        // Barre d'outils (Toolbar)
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // Permet d'aligner à droite
        toolBar.setBackground(getBackground());
        toolBar.setFloatable(false); // Désactiver le déplacement de la barre

        // Label pour la date
        dateLabel = new JLabel();
        dateLabel.setForeground(guiManager.getTheme().getFontColor());

        // Icône WiFi
        ImageIcon wifiIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/NoWifi.png"));
        Image img = wifiIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        wifiSignal = new JLabel(resizedIcon);

        // Ajouter les éléments dans la toolbar
        toolBar.add(dateLabel);
        toolBar.add(Box.createHorizontalGlue()); // Pousse le WiFi à droite
        toolBar.add(wifiSignal);

        // Ajout des composants
        add(toolBar, BorderLayout.NORTH);
        add(startButton, BorderLayout.SOUTH);

        // **Ajout du panneau des yeux**
        
     // Ajout du panneau des yeux
        eyesPanel = new RobotEyesPanel();
        eyesPanel.setOpaque(false); // S'assurer qu'il ne masque pas l'arrière-plan
        add(eyesPanel, BorderLayout.CENTER);


        // Mise à jour automatique
        Timer timer = new Timer(1000, e -> updateStates());
        timer.start();
        
        Timer timerEyes = new Timer(10000, e -> simulateEyeActions());
        timerEyes.start();

        // Simulation d'interaction avec les yeux
        
    }

    private void updateStates() {
        updateClock();
        updateWifi();
        
    }

    private void updateWifi() {
        int wifiQuality = WifiSignal.getWifiSignal();
        String imgName;

        if (wifiQuality == 0)
            imgName = "NoWifi";
        else if (wifiQuality < 25)
            imgName = "WifiMedium";
        else if (wifiQuality < 50)
            imgName = "WifiGood";
        else
            imgName = "WifiVeryGood";

        System.out.println(imgName);
        ImageIcon wifiIcon = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + imgName + ".png"));
        Image img = wifiIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        wifiSignal.setIcon(resizedIcon);
    }

    private void updateClock() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        dateLabel.setText(dateTime.format(formatter));
    }

    private void simulateEyeActions() {
        Timer actionTimer = new Timer(5000, e -> {
            eyesPanel.lookLeft();
            Timer resetTimer = new Timer(1000, e1 -> {
                eyesPanel.lookRight();
            });
            resetTimer.setRepeats(false);
            resetTimer.start();
        });
        actionTimer.setRepeats(false);
        actionTimer.start();
    }
}
