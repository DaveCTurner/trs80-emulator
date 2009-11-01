package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.InputStatement;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.NextStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

public class InputTest {
    
    @Test
    public void shouldSkipLine() throws Exception {
        Program loopProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new InputStatement(new StringLiteral("\"GIVE ME VALUES\""), new Identifier("X"), new Identifier("Y")), 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ForStatement(new Identifier("JJ"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIdentifier(new Identifier("Y"))
                    ) 
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIdentifier(new Identifier("X"))
                    ) 
                }),
                new ProgramLine(new LineNumber(40), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(50), new Statement[] {
                    new NextStatement()
                }),
                new ProgramLine(new LineNumber(60), new Statement[] {
                    new NextStatement()
                }),
                new ProgramLine(new LineNumber(70), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIdentifier(new Identifier("X"))
                    ) 
                }),
                new ProgramLine(new LineNumber(80), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(90), new Statement[] {
                    new NextStatement()
                })
        });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(loopProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        env.addInputValue(3);
        env.addInputValue(2);
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should have printed 'GIVE ME VALUES'", "GIVE ME VALUES\n", env.getPrintedOutput());
        assertEquals("Should now have cleared screen nine times", 9, env.getScreenClearedCount());
    }
}
