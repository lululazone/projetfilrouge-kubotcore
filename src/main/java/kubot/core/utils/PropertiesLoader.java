package kubot.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	
	private String configFilePath="src/config.properties";
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

}
