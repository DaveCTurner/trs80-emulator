package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.examplePrograms.SingleClearScreenProgram;

public class ClearScreenTest {
    @Test
    public void shouldClearScreen() {
        InstrumentedEnvironment env = new InstrumentedEnvironment();
        
        assertFalse("Should not have cleared screen", env.getScreenCleared());
        Executable e = new SingleClearScreenProgram();
        e.execute(env);
        assertTrue("Should now have cleared screen", env.getScreenCleared());
    }
}
