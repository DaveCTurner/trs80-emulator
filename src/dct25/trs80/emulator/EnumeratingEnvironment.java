package dct25.trs80.emulator;

import java.io.PrintStream;
import java.util.Stack;

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

    // Whether to advance the width and height the next time the program calls
    // 'getInput'.
    private boolean _advanceWidthAndHeightOnNextGetInput;

    // Whether the next getInput() call is asking for height.
    private boolean _nextInputIsHeight;

    public EnumeratingEnvironment(PrintStream out) {
        _out = out;
        _w = 1;
        _h = 1;
        _advanceWidthAndHeightOnNextGetInput = true;
        _nextInputIsHeight = false;
        _randomNumbersGeneratedLastTime = new Stack<Integer>();
        _randomNumbersGeneratedThisTime = new Stack<Integer>();
        _lengthOfFinalSequenceOfMaximumValues = 0;
    }

    public void clearScreen() {
        _out.println("--\nCLS\n--");
    }

    public int getInput() {
        if (!_nextInputIsHeight) {
            resetRandomNumberGenerator();
        }

        if (_advanceWidthAndHeightOnNextGetInput) {
            advanceWidthAndHeight();
            _advanceWidthAndHeightOnNextGetInput = false;
        }

        int nextInput;
        if (_nextInputIsHeight) {
            nextInput = _h;
        } else {
            nextInput = _w;
        }
        _nextInputIsHeight = !_nextInputIsHeight;
        _out.println(nextInput);
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

    int _lengthOfFinalSequenceOfMaximumValues;

    private void resetRandomNumberGenerator() {
        if (_randomNumbersGeneratedThisTime.size() == _lengthOfFinalSequenceOfMaximumValues) {
            // All the generated values were at maximum - time for a different
            // size.
            _advanceWidthAndHeightOnNextGetInput = true;
        } else {
            // Flip the stack over, throw away the terminal sequence of maximum
            // values, and increment the last non-maximum value.
            for (int i = 0; i < _lengthOfFinalSequenceOfMaximumValues; i++) {
                assert(!_randomNumbersGeneratedThisTime.isEmpty());
                _randomNumbersGeneratedThisTime.pop();
            }
            
            assert(!_randomNumbersGeneratedThisTime.isEmpty());
            _randomNumbersGeneratedLastTime
                .push(_randomNumbersGeneratedThisTime.pop() + 1);

            while (!_randomNumbersGeneratedThisTime.isEmpty()) {
                _randomNumbersGeneratedLastTime
                        .push(_randomNumbersGeneratedThisTime.pop());
            }
            
            _lengthOfFinalSequenceOfMaximumValues = 0;
        }
    }

    Stack<Integer> _randomNumbersGeneratedLastTime;

    Stack<Integer> _randomNumbersGeneratedThisTime;

    public int getNextRandomNumber(int maximum) {
        _advanceWidthAndHeightOnNextGetInput = false;

        int nextRandomNumber;

        if (_randomNumbersGeneratedLastTime.isEmpty()) {
            nextRandomNumber = 1;
        } else {
            nextRandomNumber = _randomNumbersGeneratedLastTime.pop();
        }
        
        assert(nextRandomNumber <= maximum);
        if (nextRandomNumber == maximum) {
            _lengthOfFinalSequenceOfMaximumValues += 1;
        } else {
            _lengthOfFinalSequenceOfMaximumValues = 0;
        }

        _randomNumbersGeneratedThisTime.push(nextRandomNumber);
        return nextRandomNumber;
    }

    public void print(String s, boolean newLine) {
        _out.print(s);
        if (newLine) {
            _out.println();
        }
    }
}
