package model;

import java.io.IOException;

public class SlotFactory {

	public Slot newSlot(String str) throws IOException {
		if (str.substring(0, 1).equals("#")) {
			return new CommentSlot(str);
		}
		return new ExprSlot(str);
	}
	
}