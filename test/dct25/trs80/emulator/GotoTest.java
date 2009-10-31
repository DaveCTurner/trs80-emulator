package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class GotoTest {
    
    @Test
    public void shouldSkipLine() throws Exception {
        Program clearScreenProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement(), new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement(), new GotoStatement(new LineNumber(40)), new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(40), new Statement[] {
                    new ClearScreenStatement(), new ClearScreenStatement(), new ClearScreenStatement()
                }),
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(clearScreenProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen six times", 6, env.getScreenClearedCount());
    }
}
