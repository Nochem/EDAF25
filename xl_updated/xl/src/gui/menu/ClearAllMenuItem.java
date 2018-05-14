package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.XL;

class ClearAllMenuItem extends JMenuItem implements ActionListener {
	private XL xl;
	
    public ClearAllMenuItem(XL xl) {
        super("Clear all");
        addActionListener(this);
        this.xl = xl;
    }

    public void actionPerformed(ActionEvent e) {
    	int dialogResult = JOptionPane.showConfirmDialog(null, 
    									"Are you sure you want to clear all cells?", "Warning", 
    									JOptionPane.YES_NO_OPTION);
    	if (dialogResult == JOptionPane.YES_OPTION) {
    		xl.clearSheet();
    	}
    }
}