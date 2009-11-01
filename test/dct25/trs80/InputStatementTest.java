package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.InputStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

public class InputStatementTest {

    @Test
    public void shouldParseGotoCommand() throws Exception {
        Reader input = new StringReader("10 INPUT \"GIVE ME VALUES\"; X,Y\n");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new InputStatement(new StringLiteral("GIVE ME VALUES"), new Identifier("X"), new Identifier("Y")), 
                })
        });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void ShouldConvertProgramWithGotoCorrectly() throws Exception {

        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new InputStatement(new StringLiteral("GIVE ME VALUES"), new Identifier("X"), new Identifier("Y")), 
                })
        });

        assertEquals("Check program text", "10 INPUT \"GIVE ME VALUES\"; X,Y\n", p.asBasic());
    }
    
}
