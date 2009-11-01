package dct25.trs80.emulator;

import java.util.LinkedList;
import java.util.Queue;

public class InstrumentedEnvironment implements Environment {

    public InstrumentedEnvironment() {
        screenClearedCount = 0;
    }
    
    private int screenClearedCount;
    
    public int getScreenClearedCount() { return screenClearedCount; }

    public void clearScreen() {
        screenClearedCount += 1;
    }

    
    public String getPrintedOutput() {
        return _printed.toString();
    }
    private StringBuffer _printed = new StringBuffer();
    public void print(String s, boolean newLine) {
        _printed.append(s);
        if (newLine) { _printed.append("\n"); }
    }

    private Queue<Integer> _inputValues = new LinkedList<Integer>();
    public void addInputValue(int i) {
        _inputValues.add(i);
    }

    public int getInput() {
        return _inputValues.remove();
    }

    private Queue<Integer> _randomValues = new LinkedList<Integer>();
    public void addRandomValue(int i) {
        _randomValues.add(i);
    }
    public int getNextRandomNumber(int maximum) {
        return _randomValues.remove();
    }
}
