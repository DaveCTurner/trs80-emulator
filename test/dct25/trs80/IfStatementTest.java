package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.BooleanExpression;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.IfStatement;
import dct25.trs80.syntaxTree.IntegerExpression;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class IfStatementTest {

    @Test
    public void shouldParsePrintStatement() throws Exception {
        Reader input = new StringReader("10 IF 1<>1 AND 2<>2 THEN 30\n20 CLS\n30 CLS");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new IfStatement(BooleanExpression.conjunction(BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1))
                    ), BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                    )), new LineNumber(30))
                }),
                
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void ShouldConvertProgramWithIfStatementCorrectly() throws Exception {

        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new IfStatement(BooleanExpression.conjunction(BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1))
                    ), BooleanExpression.notEquals(
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(2))
                    )), new LineNumber(30))
                }),
                
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement()
                })
        });

        assertEquals("Check program text", "10 IF (((1)<>(1)) AND ((2)<>(2))) THEN 30\n20 CLS\n30 CLS\n", p.asBasic());
    }
}
