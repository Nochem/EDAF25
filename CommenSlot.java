package model;

public class CommentSlot extends Slot {

	private String comment;
	
	public CommentSlot(String str) {
		comment=str.replaceFirst("#", "");
	}
	
	public double getValue() throws SlotTypeException {
		throw new SlotTypeException("Not a ExprSlot");
	}
	
	public String getComment() {
		return comment;
	}
	
}
