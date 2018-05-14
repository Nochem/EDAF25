package gui;

import java.util.Observable;

public class CurrentStatus extends Observable {
	String status;
	
	public CurrentStatus() {
		status = "";
	}
	
	public void updateStatus(String status) {
		this.status = status;
		this.setChanged();
		this.notifyObservers(status);
	}
}
