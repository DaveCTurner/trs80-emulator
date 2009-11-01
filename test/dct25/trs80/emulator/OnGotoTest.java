package dct25.trs80.emulator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.OnGotoStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.ScalarAssignmentStatement;
import dct25.trs80.syntaxTree.Statement;

@RunWith(Parameterized.class)
public class OnGotoTest {
    
    @Parameters
    public static Collection literals() {
        return Arrays.asList(new Object[][] {
                { 1 },
                { 2 },
                { 3 },
        });
    }
    
    public OnGotoTest(int n) {
        _n = n;
    }
    
    private int _n;
    
    @Test
    public void shouldSkipLines() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("X"), 
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(_n))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new OnGotoStatement(
                            IntegerExpression.fromIdentifier(new Identifier("X")),
                            new LineNumber[] {
                                new LineNumber(50), 
                                new LineNumber(40), 
                                new LineNumber(30) 
                            }
                    )
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(40), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(50), new Statement[] {
                    new ClearScreenStatement()
                })
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(p);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen " + _n + " time" + (_n == 1 ? "" : "s"),
                _n, env.getScreenClearedCount());
    }
}
