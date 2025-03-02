package kubot.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WifiSignal {

    public static int getWifiSignal() {
        try {
            // Utilisez /bin/bash pour exécuter des commandes shell sur Linux
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "iwconfig wlan0 | grep 'Signal level'");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Signal level")) {
                    // Extrait la qualité du signal WiFi de la sortie de la commande
                    String[] parts = line.split("Signal level=");
                    if (parts.length > 1) {
                        String signalLevel = parts[1].split(" ")[0].trim();
                        // Extrait uniquement la partie numérique
                        return Integer.parseInt(signalLevel.replace("dBm", "").trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Retourne 0 si la qualité du signal ne peut pas être obtenue
    }
}
