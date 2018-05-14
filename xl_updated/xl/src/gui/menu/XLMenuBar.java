package gui.menu;

import gui.CurrentStatus;
import gui.StatusLabel;
import gui.XL;
import gui.XLList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class XLMenuBar extends JMenuBar {
    public XLMenuBar(XL xl, XLList xlList, CurrentStatus status) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new PrintMenuItem(xl, status));
        file.add(new SaveMenuItem(xl, status));
        file.add(new LoadMenuItem(xl, status));
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        edit.add(new ClearMenuItem(xl));
        edit.add(new ClearAllMenuItem(xl));
        add(file);
        add(edit);
        add(new WindowMenu(xlList));
    }
}