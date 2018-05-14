package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import util.XLException;

import java.util.Observable;


public class Sheet extends Observable implements expr.Environment  {

	private Map<String, Slot> map;
	private SlotFactory factory;
	private boolean isSaved;

	public Sheet() {
		map = new TreeMap<String, Slot>();
		factory = new SlotFactory();
		isSaved = true;
	}

	public void setSlot(String slotAddress, String enteredExpr) throws IOException {
		if(enteredExpr.equals("")) {
			map.remove(slotAddress);
		} else {
			if (!enteredExpr.substring(0, 1).equals("#")){
				CircularSlot circularSlot = new CircularSlot(enteredExpr);
				map.put(slotAddress, circularSlot);
				try {
					circularSlot.evaluate(this);
				} catch (XLException e) {
					map.remove(slotAddress);
					updateObservers();
					throw e;
				}
			}
			map.put(slotAddress, factory.newSlot(enteredExpr));
		}
		isSaved = false;
		updateObservers();
	}

	public double value(String str) {
		if (!map.containsKey(str)) {
			throw new XLException("illegal address: " + str);
		}
		return map.get(str).getValue(this);
	}
	
	public void clearSlots() {
		if (!map.isEmpty()) {
			map.clear();
			isSaved = false;
			updateObservers();
		}
	}
	
	public void clearSlot(String address) {
		if (map.containsKey(address)) {
			map.remove(address);
			isSaved = false;
			updateObservers();
		}
	}
	
	public void save(String path) throws FileNotFoundException {
		XLPrintStream xlps = new XLPrintStream(path);
		xlps.save(map.entrySet());
		xlps.close();
		isSaved = true;
	}
	
	public void load(String path) throws IOException {
		isSaved = true;
		clearSlots();
		XLBufferedReader xlbr = new XLBufferedReader(path);
		xlbr.load(map);
		xlbr.close();
		updateObservers();
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public void updateObservers() {
		this.setChanged();
		this.notifyObservers(map.entrySet());
	}
	
	
}