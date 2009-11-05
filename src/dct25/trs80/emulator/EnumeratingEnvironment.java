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

    // Whether to advance the width and height the next time the program calls 'getInput'.
    private boolean _advanceWidthAndHeightOnNextGetInput;

    // Whether the next getInput() call is asking for height.
    private boolean _nextInputIsHeight;

    public EnumeratingEnvironment(PrintStream out) {
        _out = out;
        _w = 1;
        _h = 1;
        _advanceWidthAndHeightOnNextGetInput = true;
        _nextInputIsHeight = false;
    }

    public void clearScreen() {
        _out.println("--\nCLS\n--");
    }

    public int getInput() {
        int nextInput;
        if (_advanceWidthAndHeightOnNextGetInput) {
            advanceWidthAndHeight();
            _advanceWidthAndHeightOnNextGetInput = false;
        }

        if (_nextInputIsHeight) {
            nextInput = _h;
            _advanceWidthAndHeightOnNextGetInput = true;
            // In case the program asks for no random numbers.
        } else {
            nextInput = _w;
        }
        _nextInputIsHeight = !_nextInputIsHeight;

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
        _advanceWidthAndHeightOnNextGetInput = false;
        return 1;
    }

    public void print(String s, boolean newLine) {
        _out.print(s);
        if (newLine) {
            _out.println();
        }
    }

}
