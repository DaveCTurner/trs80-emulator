package dct25.trs80.emulator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dct25.trs80.syntaxTree.BooleanExpression;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IfStatement;
import dct25.trs80.syntaxTree.InputStatement;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

@RunWith(Parameterized.class)
public class IfTestWithInput {
    
    @Parameters
    public static Collection literals() {
        return Arrays.asList(new Object[][] {
                { 1, 1, 2, 2, false },
                { 1, 3, 2, 2, false },
                { 1, 1, 2, 4, false },
                { 1, 3, 2, 4, true },
        });
    }
    
    public IfTestWithInput(int a, int b, int c, int d, boolean outcome) {
        _a = a;
        _b = b;
        _c = c;
        _d = d;
        _outcome = outcome;
    }
    
    private int _a, _b, _c, _d;
    private boolean _outcome;
    
    @Test
    public void shouldSkipLine() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new InputStatement(new StringLiteral("\"GIVE ME DATA\""), new Identifier("X"), new Identifier("Y"))
                }),
                
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new IfStatement(BooleanExpression.conjunction(BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(_a)),
                            IntegerExpression.fromIdentifier(new Identifier("X"))
                    ), BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(_c)),
                            IntegerExpression.fromIdentifier(new Identifier("Y"))
                    )), new LineNumber(40))
                }),
                
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                }),
                
                new ProgramLine(new LineNumber(40), new Statement[] {
                    new ClearScreenStatement()
                })
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(p);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        env.addInputValue(_b);
        env.addInputValue(_d);
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen once", _outcome ? 1 : 2, env.getScreenClearedCount());
    }
}
