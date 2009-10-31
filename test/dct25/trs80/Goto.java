package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class Goto {

    @Test
    public void shouldParseGotoCommand() throws Exception {
        Reader input = new StringReader("10 GOTO 20\n20 CLS");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new GotoStatement(new LineNumber(20)), 
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void ShouldConvertProgramWithGotoCorrectly() throws Exception {

        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement(),
                    new GotoStatement(new LineNumber(20))
                }), new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement(),
                    new ClearScreenStatement(),
                    new ClearScreenStatement()
                })});

        assertEquals("Check program text", "10 CLS : GOTO 20\n20 CLS : CLS : CLS\n", p.asBasic());
    }
    
}
