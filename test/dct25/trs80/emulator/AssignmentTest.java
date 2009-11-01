package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.AssignmentStatement;
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

public class AssignmentTest {
    
    @Test
    public void shouldSkipLine() throws Exception {
        Program loopProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new AssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(7))
                    )
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIdentifier(new Identifier("Q"))
                    ) 
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(40), new Statement[] {
                    new NextStatement()
                })
              });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(loopProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen seven times", 7, env.getScreenClearedCount());
    }
}
