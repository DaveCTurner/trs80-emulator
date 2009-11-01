package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ArrayAssignmentStatement;
import dct25.trs80.syntaxTree.ArrayElement;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.DimStatement;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.NextStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.ScalarAssignmentStatement;
import dct25.trs80.syntaxTree.Statement;

public class ArrayAssignmentTest {
    
    @Test
    public void shouldAssignAndLoop() throws Exception {
        Program loopProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new DimStatement(
                            new ArrayElement(new Identifier("H"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(4))
                            ),
                            new ArrayElement(new Identifier("Z"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3))
                            )
                    )
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ArrayAssignmentStatement(
                            new ArrayElement(new Identifier("H"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                            ),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(42))
                    ),
                    new ScalarAssignmentStatement(
                            new Identifier("X"),
                            IntegerExpression.fromArrayElement(
                                    new ArrayElement(new Identifier("H"),
                                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                                    )
                            )
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
                })
              });
        
        Executable e = new BasicToJavaCompiler("OnTheFlyProgram", "dct25.trs80.examplePrograms.onTheFly").compile(loopProgram);

        InstrumentedEnvironment env = new InstrumentedEnvironment();
        assertEquals("Should not have cleared screen", 0, env.getScreenClearedCount());
        e.execute(env);
        assertEquals("Should now have cleared screen 42 times", 42, env.getScreenClearedCount());
    }
}
