package model;

public class SlotTypeException extends RuntimeException {
	
	private String comment;
	
	public SlotTypeException(String comment) {
		this.comment=comment;
	}
	
	public String getMessage() {
		return comment;
	}
}