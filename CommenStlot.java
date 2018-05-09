package model;

public class CommentSlot extends Slot {

	private String comment;
	
	public double getValue() {
		// throw exeption, this is not an expression slot
		return 0;
	}
	public String getComment() {
		return comment;
	}
	
}
