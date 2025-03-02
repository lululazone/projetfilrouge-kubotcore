package kubot.core.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RobotEyesPanel extends JPanel {
    private float eyeOffsetX = 0; // Position courante des yeux
    private float targetEyeOffsetX = 0; // Position cible pour l'animation
    private float eyeHeight = 30; // Hauteur des yeux (clignement)
    private float targetEyeHeight = 30; // Hauteur cible
    private int eyeState = 0; // 0 = normal, 1 = clignement, 2 = gauche, 3 = droite, 4 = soleil, 5 = nuage, 6 = pluie
    private final int sizeMultiplier = 7;
    private Timer animationTimer;

    public RobotEyesPanel() {
        setPreferredSize(new Dimension(1920, 1080));

        // Timer pour l'animation fluide (60 FPS)
        animationTimer = new Timer(16, e -> smoothAnimation()); 
        animationTimer.start();

        // Timer pour cligner des yeux périodiquement
        new Timer(getRandomTime(), e -> blink()).start();
    }

    private int getRandomTime() {
    	Random rd = new Random();
		return rd.nextInt(2, 10)*1000;
	}

	private void smoothAnimation() {
        // Interpolation pour le mouvement horizontal
        if (Math.abs(eyeOffsetX - targetEyeOffsetX) > 0.1) {
            eyeOffsetX += (targetEyeOffsetX - eyeOffsetX) * 0.2;
        }

        // Interpolation pour le clignement des yeux
        if (Math.abs(eyeHeight - targetEyeHeight) > 0.5) {
            eyeHeight += (targetEyeHeight - eyeHeight) * 0.2;
        }

        repaint();
    }

    public void blink() {
        targetEyeHeight = 5; // Ferme les yeux
        Timer reopenTimer = new Timer(200, e -> targetEyeHeight = 30); // Réouvre après 200ms
        reopenTimer.setRepeats(false);
        reopenTimer.start();
    }

    public void lookLeft() {
        targetEyeOffsetX = -15 * sizeMultiplier; // Déplace les yeux à gauche
    }

    public void lookRight() {
        targetEyeOffsetX = 15 * sizeMultiplier; // Déplace les yeux à droite
    }

    public void centerEyes() {
        targetEyeOffsetX = 0; // Recentre les yeux
    }

    public void showWeather(String weather) {
        switch (weather) {
            case "sun":
                eyeState = 4;
                break;
            case "cloud":
                eyeState = 5;
                break;
            case "rain":
                eyeState = 6;
                break;
            default:
                eyeState = 0;
                break;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner la zone des yeux
        g2d.setColor(Color.BLACK);
        g2d.fillOval(sizeMultiplier * 50, sizeMultiplier * 20, sizeMultiplier * 200, sizeMultiplier * 100);

        // Dessiner les yeux selon l'état
        g2d.setColor(Color.CYAN);

        // Position des yeux animée
        int leftEyeX = (sizeMultiplier * 90) + (int) eyeOffsetX;
        int rightEyeX = (sizeMultiplier * 170) + (int) eyeOffsetX;

        if (eyeState == 0) {
            // Normal
            g2d.fillOval(leftEyeX, sizeMultiplier * 50, sizeMultiplier * 20, (int) (sizeMultiplier * eyeHeight));
            g2d.fillOval(rightEyeX, sizeMultiplier * 50, sizeMultiplier * 20, (int) (sizeMultiplier * eyeHeight));
        } else if (eyeState == 1) {
            // Clignement progressif
            g2d.fillRect(leftEyeX, sizeMultiplier * 60, sizeMultiplier * 20, (int) (sizeMultiplier * eyeHeight));
            g2d.fillRect(rightEyeX, sizeMultiplier * 60, sizeMultiplier * 20, (int) (sizeMultiplier * eyeHeight));
        }

        // Affichage météo (au-dessus des yeux)
        if (eyeState == 4) {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(sizeMultiplier * 130, sizeMultiplier * 10, sizeMultiplier * 40, sizeMultiplier * 40);
        } else if (eyeState == 5) {
            g2d.setColor(Color.GRAY);
            g2d.fillOval(sizeMultiplier * 110, sizeMultiplier * 10, sizeMultiplier * 50, sizeMultiplier * 30);
        } else if (eyeState == 6) {
            g2d.setColor(Color.BLUE);
            g2d.fillOval(sizeMultiplier * 120, sizeMultiplier * 10, sizeMultiplier * 40, sizeMultiplier * 40);
        }
    }
}
