package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer {
	
    public CurrentLabel() {
        super("A1", Color.WHITE);
    }

	@Override
	public void update(Observable o, Object currentSlotAddress) {
		this.setText((String) currentSlotAddress);
	}
    
}