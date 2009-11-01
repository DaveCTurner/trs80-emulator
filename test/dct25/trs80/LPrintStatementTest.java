package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.PrintStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

public class LPrintStatementTest {

    @Test
    public void shouldParseLPrintStatementWithNoNewline() throws Exception {
        Reader input = new StringReader("10 LPRINT \":--\";");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral(":--"), false) 
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldPrintLPrintStatementWithNoNewline() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral(":--"), false) 
                })
              });

        assertEquals("Check parsed program is as expected", "10 PRINT \":--\";\n", p.asBasic());
    }

    @Test
    public void shouldParseLPrintStatement() throws Exception {
        Reader input = new StringReader("10 LPRINT \":--\"");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral(":--"), true) 
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldPrintLPrintStatement() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral(":--"), true) 
                })
              });

        assertEquals("Check parsed program is as expected", "10 PRINT \":--\"\n", p.asBasic());
    }
}
