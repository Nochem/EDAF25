package gui.menu;

import gui.CurrentStatus;
import gui.XL;
import util.XLException;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

class LoadMenuItem extends OpenMenuItem {

	public LoadMenuItem(XL xl, CurrentStatus status) {
		super(xl, status, "Load");
	}

	protected void action(String path) throws FileNotFoundException {
		try {
			xl.loadSheet(path);
			status.updateStatus(path + " loaded");
		}
		catch (XLException XLexception) {
			status.updateStatus(XLexception.getMessage());
			xl.clearSheet();
		} catch (StackOverflowError stackError) {
			status.updateStatus("circular reference");
			xl.clearSheet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int openDialog(JFileChooser fileChooser) {
		return fileChooser.showOpenDialog(xl);
	}
}