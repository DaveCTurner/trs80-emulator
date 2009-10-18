/**
 * 
 */
package dct25.trs80;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

/**
 * @author dct25
 * 
 */
public class RepeatedLines {

    @org.junit.Test
    public void shouldParseThreeLinesOfCLSCommands() throws Exception {
        Reader input = new StringReader("10 CLS: CLS\n20 CLS\n30 CLS:CLS :CLS");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);
        System.out.println(o);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement(), 
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement()
                }),
                new ProgramLine(new LineNumber(30), new Statement[] {
                    new ClearScreenStatement(), 
                    new ClearScreenStatement(), 
                    new ClearScreenStatement()
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
}
