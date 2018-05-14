package model;

import util.XLException;

public class CommentSlot implements Slot {

	private String comment;
	
	public CommentSlot(String comment) {
		this.comment=comment;
	}
	
	@Override
	public double getValue(Sheet sheet) {
		throw new XLException("can't get value of a comment");
	}

	@Override
	public String getStringExpr() {
		return comment;
	}
	
}