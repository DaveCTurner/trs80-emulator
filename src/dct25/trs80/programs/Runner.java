package dct25.trs80.programs;

import dct25.trs80.emulator.EnumeratingEnvironment;
import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;

public class Runner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Executable program = new amazing_test_bas();
        Environment e = new EnumeratingEnvironment(System.out);
        
        for (int i = 0; i < 100; i++) {
            program.execute(e);
        }
    }

}
