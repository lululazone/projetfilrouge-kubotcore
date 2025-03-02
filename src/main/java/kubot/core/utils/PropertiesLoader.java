package kubot.core.utils;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	
	private String configFilePath="config.properties";
	private Properties prop = new Properties();
	
	public PropertiesLoader() throws IOException {
		initProperties();
	}
	
	private void initProperties() throws IOException {
		FileInputStream propsInput = new FileInputStream(configFilePath);
		prop.load(propsInput);
	}
	
	public boolean getSetupDone() {
		return Boolean.parseBoolean(prop.getProperty("SETUP_DONE"));
	}
	
	public int getBootDelay() {
		return Integer.parseInt(prop.getProperty("BOOT_DELAY"));
	}
	
	public Color getBackgroundColor() {
		String[] colorStr = prop.getProperty("BACKGROUND_COLOR").split(","); 
		return strToColor(colorStr);
	}

	public Color getFontColor() {
		String[] colorStr = prop.getProperty("FONT_COLOR").split(","); 
		return strToColor(colorStr);
	}
	
	public Color strToColor(String[] strColor) {
		return new Color(Integer.parseInt(strColor[0]),
				Integer.parseInt(strColor[1]),
				Integer.parseInt(strColor[2]));
	}
	
	public boolean getDebug() {
		return Boolean.parseBoolean(prop.getProperty("DEBUG"));
	}

}
