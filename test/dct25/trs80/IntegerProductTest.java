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

public class IntegerProductTest {

    @Test
    public void shouldParseAssignmentStatement() throws Exception {
        Reader input = new StringReader("10 Q=2*5\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ScalarAssignmentStatement(
                            new Identifier("Q"),
                            IntegerExpression.product(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(5))
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
                            IntegerExpression.product(
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(5))
                            )
                    )
                })
        });

        assertEquals("Check program text is as expected", "10 Q=((2)*(5))\n", p.asBasic());
    }
}
