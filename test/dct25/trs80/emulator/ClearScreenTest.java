package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.examplePrograms.SingleClearScreenProgram;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class ClearScreenTest {
    @Test
    public void shouldClearScreen() {
        InstrumentedEnvironment env = new InstrumentedEnvironment();
        
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        Executable e = new SingleClearScreenProgram();
        e.execute(env);
        assertEquals("Should now have cleared screen once", 1, env.getScreenClearedCount());
    }

    @Test
    public void shouldCreateClassWithCorrectName() throws Exception {
        Program clearScreenProgram = new Program(
                new ProgramLine[] { new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement()
                })});
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly")
            .compile(clearScreenProgram);
        
        assertEquals("Check compiled class name",
                "dct25.trs80.examplePrograms.onTheFly.OnTheFlyProgram",
                e.getClass().getCanonicalName());
    }

    @Test
    public void shouldCreateClassWithCorrectName2() throws Exception {
        Program clearScreenProgram = new Program(
                new ProgramLine[] { new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement()
                })});
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram2", "dct25.trs80.examplePrograms.onTheFly2")
            .compile(clearScreenProgram);
        
        assertEquals("Check compiled class name",
                "dct25.trs80.examplePrograms.onTheFly2.OnTheFlyProgram2",
                e.getClass().getCanonicalName());
    }

    
    @Test
    public void shouldClearScreenWithInMemoryClass() throws Exception {
        Program clearScreenProgram = new Program(
                new ProgramLine[] { new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement()
                })});
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(clearScreenProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen once", 1, env.getScreenClearedCount());
    }
}
