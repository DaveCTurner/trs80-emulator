package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ArrayElement;
import dct25.trs80.syntaxTree.DimStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class DimStatementTest {

    @Test
    public void shouldParseDimStatement() throws Exception {
        Reader input = new StringReader("10 DIM H(2,4), V(3,6)\n");
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
                            new ArrayElement(new Identifier("V"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(6))
                            )
                    )
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldConvertProgramWithDimStatementCorrectly() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new DimStatement(
                            new ArrayElement(new Identifier("H"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(4))
                            ),
                            new ArrayElement(new Identifier("V"),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(3)),
                                    IntegerExpression.fromIntegerLiteral(new IntegerLiteral(6))
                            )
                    )
                })
        });

        assertEquals("Check program text", "10 DIM H((2),(4)), V((3),(6))\n", p.asBasic());
    }
    
}
