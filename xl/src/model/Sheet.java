package model;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Observable;
import java.util.Observer;


public class Sheet extends Observable implements expr.Environment  {

	private Map<String, Slot> map;
	private String lastCreatedSlot;
	private SlotFactory factory;

	public Sheet() {
		map = new TreeMap<String, Slot>();
		lastCreatedSlot=null;
		factory = new SlotFactory();
	}

	public void setSlot(String slotAddress, String enteredExpr) throws IOException {
		if(enteredExpr.equals("")) {
			map.remove(slotAddress);
		} else {
			map.put(slotAddress, factory.newSlot(enteredExpr));	
		}
		lastCreatedSlot = slotAddress;
		updateObservers();
	}

	public double value(String str) {
		return map.get(str).getValue(this) ;
	}
	
	public void updateObservers() {
		this.setChanged();
		this.notifyObservers(map);
	}
	
	

}