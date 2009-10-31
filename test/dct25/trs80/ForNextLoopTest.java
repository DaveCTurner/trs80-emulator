package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.emulator.MatchLoopsVisitor;
import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
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
    public void shouldParseForNextLoop() throws Exception {
        Reader input = new StringReader("10 FOR II = 1 TO 10\n20 NEXT\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(10))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new NextStatement()
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }

    
    @Test
    public void ShouldConvertProgramWithForNextLoopCorrectly() throws Exception {

        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ForStatement(new Identifier("II"),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(1)),
                            IntegerExpression.fromIntegerLiteral(new IntegerLiteral(10))
                    ) 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new NextStatement()
                })
              });

        p.visit(new MatchLoopsVisitor());
        assertEquals("Check program text", "10 FOR II = (1) TO (10)\n20 NEXT II\n", p.asBasic());
    }
    
}
