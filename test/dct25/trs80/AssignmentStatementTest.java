package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ScalarAssignmentStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class AssignmentStatementTest {

    @Test
    public void shouldParseAssignmentStatement() throws Exception {
        Reader input = new StringReader("10 Q=1\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1))
                    )
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    

    @Test
    public void shouldParseAssignmentStatementWithRandomNumber() throws Exception {
        Reader input = new StringReader("10 Q=RND(4)\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.randomNumber(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(4))
                            )
                    )
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldOutputAssignmentStatement() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1))
                    )
                })
        });

        assertEquals("Check program text is as expected", "10 Q=(1)\n", p.asBasic());
    }
    

    @Test
    public void shouldOutputAssignmentStatementWithRandomNumber() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.randomNumber(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(4))
                            )
                    )
                })
        });

        assertEquals("Check program text is as expected", "10 Q=RND((4))\n", p.asBasic());
    }
}
