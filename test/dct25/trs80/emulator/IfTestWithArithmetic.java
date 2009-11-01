package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.BooleanExpression;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.IfStatement;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class IfTestWithArithmetic {
    
    @Test
    public void shouldCalculateSum() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new IfStatement(BooleanExpression.equals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3)),
                            IntegerExpression.sum(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                            )
                    ), new LineNumber(30))
                }),
                
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                })
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(p);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen once", 1, env.getScreenClearedCount());
    }
    
    
    @Test
    public void shouldCalculateDifference() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new IfStatement(BooleanExpression.equals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3)),
                            IntegerExpression.difference(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(5)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                            )
                    ), new LineNumber(30))
                }),
                
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                })
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(p);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen once", 1, env.getScreenClearedCount());
    }
}
