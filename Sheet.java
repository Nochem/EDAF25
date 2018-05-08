package model;

import java.util.TreeMap;

public class Sheet implements expr.Environment {

	private TreeMap map;
	private String lastCreatedSlot=null;

	public void Sheet() {
		TreeMap map = new TreeMap<String, Slot>();
		SlotFactory factory = new SlotFactory();
	}

	public boolean insertSlot(String str) {
		Slot newslot=factory.newSlot(str); //Sheet behöver vidarebefodra undantag som kastas i factory till UI:t.
		map.put (/*ID string*/, newslot);
		return true;
	}
	

	public double value(String str) {
		return map.get(str).getValue();
	}

}
