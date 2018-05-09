package model;

public class SlotTypeException extends Exception {
	private String comment;
	
	
	public SlotTypeException(String str) {
		comment=str;
	}
	
	public String getMessage() {
		return comment;
	}
}
