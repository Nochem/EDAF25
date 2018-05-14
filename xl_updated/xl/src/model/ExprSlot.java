package model;

import java.io.IOException;

import expr.Expr;
import expr.ExprParser;

public class ExprSlot implements Slot {
	
	Expr expr;
	
	public ExprSlot(String expr) throws IOException {
		ExprParser parser = new ExprParser();
		this.expr = parser.build(expr);
	}
	
	public double getValue(Sheet sheet) {
		return expr.value(sheet);
	}

	public String getStringExpr() {
		return expr.toString();
	}

}