package model;

public class CircularSlotException extends RuntimeException {

	private String comment;

	public CircularSlotException(String comment) {
		this.comment=comment;
	}

	public String getMessage() {
		return comment;
	}
}
