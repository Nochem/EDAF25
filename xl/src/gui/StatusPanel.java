package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
    protected StatusPanel(StatusLabel statusLabel, CurrentSlot currentSlot) {
    	CurrentLabel currentLabel = new CurrentLabel();
    	currentSlot.addObserver(currentLabel);
        add(WEST, currentLabel);
        add(CENTER, statusLabel);
    }
}