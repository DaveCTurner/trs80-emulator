package dct25.trs80.examplePrograms;

import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;

/**
 * Translation of the following TRS-80 BASIC program into Java:
 * 
 * ---
10 CLS
 * ---
 */

public class SingleClearScreenProgram implements Executable {

    /**
     * Execute the program.
     * @param env The environment in which to execute the program.
     */
    public void execute(Environment env) {
        env.clearScreen();
    }
}