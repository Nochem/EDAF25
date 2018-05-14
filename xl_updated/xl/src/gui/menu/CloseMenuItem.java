package gui.menu;

import gui.XL;
import gui.XLList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class CloseMenuItem extends JMenuItem implements ActionListener {
    private XL xl;
    private XLList xlList;

    public CloseMenuItem(XL xl, XLList xlList) {
        super("Close");
        this.xl = xl;
        this.xlList = xlList;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
    	int dialogResult = JOptionPane.YES_OPTION;
    	if (!xl.isSheetSaved()) {
    		dialogResult = JOptionPane.showConfirmDialog(null, 
    									"Are you sure you want to close this sheet without saving?", "Warning", 
    									JOptionPane.YES_NO_OPTION);
    	}
    	if (dialogResult == JOptionPane.YES_OPTION) {
    		xlList.remove(xl);
    		xl.dispose();
    		if (xlList.isEmpty()) {
    			System.exit(0);
    		} else {
    			xlList.last().toFront();
    		}
    	}
    }
}
