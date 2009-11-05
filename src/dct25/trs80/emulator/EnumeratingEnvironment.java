package dct25.trs80.emulator;

import java.io.PrintStream;

/**
 * A TRS-80 environment which enumerates all possible sequences of random
 * numbers between runs.
 * 
 * @author dct25
 * 
 */
public class EnumeratingEnvironment implements Environment {

    private PrintStream _out;

    // Current width and height.
    private int _w, _h;

    // Whether
    private boolean _advanceWidthAndHeightOnNextGetInput;

    public EnumeratingEnvironment(PrintStream out) {
        _out = out;
        _w = 1;
        _h = 1;
        _advanceWidthAndHeightOnNextGetInput = true;
    }

    public void clearScreen() {
        _out.println("--\nCLS\n--");
    }

    public int getInput() {
        int nextInput;
        if (_advanceWidthAndHeightOnNextGetInput) {
            advanceWidthAndHeight();
            nextInput = _w;
        } else {
            nextInput = _h;
        }
        _advanceWidthAndHeightOnNextGetInput = !_advanceWidthAndHeightOnNextGetInput;
        return nextInput;
    }

    private void advanceWidthAndHeight() {
        _w += 1;
        _h -= 1;
        if (0 == _h) {
            _h = _w;
            _w = 1;
        }
    }

    public int getNextRandomNumber(int maximum) {
        // TODO Auto-generated method stub
        return 0;
    }

    public void print(String s, boolean newLine) {
        _out.print(s);
        if (newLine) {
            _out.println();
        }
    }

}
