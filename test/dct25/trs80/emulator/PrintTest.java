package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.PrintStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

public class PrintTest {
    
    @Test
    public void shouldPrintText() throws Exception {
        Program clearScreenProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("\"HELLO WORLD\""), new IntegerLiteral(1010))
                }),
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(clearScreenProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should still not have cleared screen", 0, env.getScreenClearedCount());
        assertEquals("Should have printed 'HELLO WORLD'", "HELLO WORLD\n", env.getPrintedOutput());
    }
}
