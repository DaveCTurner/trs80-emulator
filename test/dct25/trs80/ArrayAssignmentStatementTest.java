package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ArrayAssignmentStatement;
import dct25.trs80.syntaxTree.ArrayElement;
import dct25.trs80.syntaxTree.DimStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class ArrayAssignmentStatementTest {

    @Test
    public void shouldParseArrayAssignmentStatement() throws Exception {
        Reader input = new StringReader("10 DIM H(2,4), Z(1,3)\n20 H(1,2)=42\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
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
                    )
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
}
