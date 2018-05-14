package gui.menu;

import gui.CurrentStatus;
import gui.XL;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

class SaveMenuItem extends OpenMenuItem {
    public SaveMenuItem(XL xl, CurrentStatus status) {
        super(xl, status, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
    	try {
            xl.saveSheet(path);
            status.updateStatus(path + " saved");
		} catch (FileNotFoundException notFound) {
			status.updateStatus(path + " not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}