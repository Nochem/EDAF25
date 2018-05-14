package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import gui.XL;

class ClearMenuItem extends JMenuItem implements ActionListener {
	private XL xl;
	
    public ClearMenuItem(XL xl) {
        super("Clear");
        addActionListener(this);
        this.xl = xl;
    }

    public void actionPerformed(ActionEvent e) {
        xl.clearCurrentSlot();
    }
}