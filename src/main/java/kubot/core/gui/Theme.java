package kubot.core.gui;

import java.awt.Color;
import java.awt.Font;

import kubot.core.utils.PropertiesLoader;


public class Theme {
	
	PropertiesLoader props;
	
	public Theme(PropertiesLoader props) {
		this.props = props;
	}
	
	public Color getBackgroundColor() {
		return props.getBackgroundColor();
	}
	
	public Color getFontColor() {
		return props.getFontColor();
	}
	

}
