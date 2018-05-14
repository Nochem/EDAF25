package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import util.XLException;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<String, Slot> map) {
        try {
            while (ready()) {
                String line = readLine();
                int index = line.indexOf('=');
                SlotFactory factory = new SlotFactory();
                map.put(line.substring(0, index), factory.newSlot(line.substring(index + 1, line.length())));
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
