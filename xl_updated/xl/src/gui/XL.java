package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.menu.XLMenuBar;
import model.Sheet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class XL extends JFrame implements Printable, Observer {
    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private Sheet sheet;
    private String currentSlotAddress;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        sheet = new Sheet();
        xlList.add(this);
        counter.increment();
        CurrentSlot currentSlot = new CurrentSlot();
        JPanel statusPanel = new StatusPanel(statusLabel, currentSlot);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, currentSlot, sheet);
        CurrentStatus status = new CurrentStatus();
        status.addObserver(statusLabel);
        Editor editor = new Editor(sheet, status);
        currentSlot.addObserver(editor);
        currentSlot.addObserver(this);
        sheet.addObserver(editor);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, status));
        pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		if (!isSheetSaved()) {
        			int confirmed = JOptionPane.showConfirmDialog(null, 
        					"Are you sure you want to close this sheet without saving?", "Warning",
        					JOptionPane.YES_NO_OPTION);
        			if (confirmed == JOptionPane.YES_OPTION) {
        				dispose();
        			}
        		}
        		else {
        			dispose();
        		}
        	}
        });
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0)
            return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(g2d);
        return PAGE_EXISTS;
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }
    
    public void clearSheet() {
    	sheet.clearSlots();
    }
    
    public void clearCurrentSlot() {
    	sheet.clearSlot(currentSlotAddress);
    }
    
    public boolean isSheetSaved() {
    	return sheet.isSaved();
    }
    
    public void saveSheet(String path) throws FileNotFoundException {
    	sheet.save(path);
    }
    
    public void loadSheet(String path) throws IOException {
    	sheet.load(path);
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }

	@Override
	public void update(Observable o, Object arg) {
		currentSlotAddress = (String) arg;
	}
}