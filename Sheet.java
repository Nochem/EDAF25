package model;

import java.util.TreeMap;

public class Sheet implements expr.Environment {

	private TreeMap map;
	private String lastCreatedSlot;

	public void Sheet() {
	}

	public boolean insertSlot(String str) {
		return false;
	}

	public double value(String str) {
		return 0;
	}

}
