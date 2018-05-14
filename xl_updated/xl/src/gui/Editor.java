package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JTextField;

import model.Sheet;
import model.Slot;
import util.XLException;

public class Editor extends JTextField implements ActionListener, Observer {

	private Sheet sheet;
	private String currentAddress;
	private CurrentStatus status;

	public Editor(Sheet sheet, CurrentStatus status) {
		setBackground(Color.WHITE);
		this.sheet = sheet;
		this.addActionListener(this);
		currentAddress = "A1";
		this.status = status;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sheet.setSlot(currentAddress, this.getText());
			status.updateStatus("");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (XLException e2) {
			status.updateStatus("Error: " + e2.getMessage());
		}
	}

	@Override
	public void update(Observable o, Object object) {
		if (o instanceof CurrentSlot) {
			currentAddress = (String) object;
		} else {
			Set<Entry<String, Slot>> entrySet = (Set) object; 
		/*	if(entrySet .containsKey(currentAddress)) {
				Slot slot = map.get(currentAddress);
				this.setText(slot.getStringExpr());	
			} else {
				this.setText("");
			}*/
			boolean found = false;
			for (Entry<String, Slot> entry : entrySet) {
				if (entry.getKey().equals(currentAddress)) {
					found = true;
					this.setText(entry.getValue().getStringExpr());
				} 
			}
			if (!found) {
				this.setText("");
			}
		}
	}
}