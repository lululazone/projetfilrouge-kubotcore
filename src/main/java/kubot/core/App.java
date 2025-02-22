package kubot.core;

import java.io.IOException;

import kubot.core.gui.GUIManager;
import kubot.core.gui.LoadingFrame;
import kubot.core.gui.MainFrame;
import kubot.core.utils.PropertiesLoader;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	try {
			PropertiesLoader props = new PropertiesLoader();
			GUIManager guiManager = new GUIManager(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        
    }
}
