package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
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

public class OnGotoTest {

    @Test
    public void shouldParseOnGotoCommand() throws Exception {
        Reader input = new StringReader("10 X=3\n20 ON X GOTO 30, 40,50\n30 CLS\n40 CLS\n50 CLS\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("X"), 
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new OnGotoStatement(
                            IntegerExpression.fromIdentifier(new Identifier("X")),
                            new LineNumber[] {
                                new LineNumber(30), 
                                new LineNumber(40), 
                                new LineNumber(50) 
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

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldPrintOnGotoCommand() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("X"), 
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new OnGotoStatement(
                            IntegerExpression.fromIdentifier(new Identifier("X")),
                            new LineNumber[] {
                                new LineNumber(30), 
                                new LineNumber(40), 
                                new LineNumber(50) 
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

        assertEquals("Check parsed program is as expected", 
                "10 X=(3)\n20 ON (X) GOTO 30, 40, 50\n30 CLS\n40 CLS\n50 CLS\n", p.asBasic());
    }   
}
