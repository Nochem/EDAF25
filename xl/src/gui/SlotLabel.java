package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.ExprSlot;
import model.Sheet;
import model.Slot;

public class SlotLabel extends ColoredLabel implements Observer {
	
	private String address;
	private CurrentSlot currentSlot;
	private Sheet sheet;
	
	
	private class ClickListener extends MouseAdapter {
		
		
		public void mouseClicked(MouseEvent event) {
			currentSlot.updateAddress(address);
			setBackground(Color.yellow);
			sheet.updateObservers();
		}
	}
	
    public SlotLabel(String address, CurrentSlot currentSlot, Sheet sheet) {
        super("                    ", Color.WHITE, RIGHT);
        addMouseListener(new ClickListener());
        this.address = address;
        this.currentSlot = currentSlot;
        this.sheet = sheet;
        currentSlot.addObserver(this);
        sheet.addObserver(this);
    }

	@Override
	public void update(Observable o, Object object) {
		if (o instanceof CurrentSlot) {
			this.setBackground(Color.white);
		} else {
			Map<String, Slot> map = (Map) object; 
			if(map.containsKey(address)) {
				Slot slot = map.get(address);
				if (slot instanceof ExprSlot) {
					this.setText(String.valueOf(slot.getValue(sheet)));
				} else {
					this.setText(slot.getComment().substring(1, slot.getComment().length()));
				}
			} else {
				this.setText("");
			}
		}	
	}
	
	public String getAddress() {
		return address;
	}
    
}