package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.NextStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class ForNextLoopTest {
    
    @Test
    public void shouldLoop() throws Exception {
        Program loopProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(10))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new NextStatement()
                })
              });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(loopProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen ten times", 10, env.getScreenClearedCount());
    }
    
    
    @Test
    public void shouldLoopWithSum() throws Exception {
        Program loopProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.sum(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(7)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(4))
                            )
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new NextStatement()
                })
              });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(loopProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen eleven times", 11, env.getScreenClearedCount());
    }
}
