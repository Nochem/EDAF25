package model;

import java.io.IOException;

import expr.Expr;
import expr.ExprParser;
import util.XLException;

public class CircularSlot implements Slot {
	
	private Expr expr;
	
	public CircularSlot(String expr) throws IOException {
		ExprParser parser = new ExprParser();
		this.expr = parser.build(expr);
	}

	@Override
	public double getValue(Sheet sheet) {
		throw new XLException("circular reference");
	}
	
	@Override
	public String getStringExpr() {
		throw new XLException("circular reference");
	}

	public void evaluate(Sheet sheet) {
		expr.value(sheet);
	}

}
