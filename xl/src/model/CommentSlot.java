package model;

public class CommentSlot implements Slot {

	private String comment;
	
	public CommentSlot(String comment) {
		this.comment=comment;
	}
	
	public double getValue(Sheet sheet) {
		return -1;
	}
	
	public String getComment() {
		return comment;
	}
	
}