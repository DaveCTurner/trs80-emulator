package dct25.trs80;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.IntegerLiteral;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.PrintStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.StringLiteral;

public class PrintStatementTest {

    @Test
    public void shouldParsePrintStatement() throws Exception {
        Reader input = new StringReader("10 PRINT @ 1204, \"HELLO WORLD\"");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("HELLO WORLD"), new IntegerLiteral(1204)) 
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void shouldParsePrintStatementWithComma() throws Exception {
        Reader input = new StringReader("10 PRINT @ 1204, \"HELLO, WORLD\"");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("HELLO, WORLD"), new IntegerLiteral(1204)) 
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    
    @Test
    public void shouldParsePrintStatementWithNoLocation() throws Exception {
        Reader input = new StringReader("10 PRINT \"HELLO, WORLD\"");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
        Object o = parser.parse(scanner);

        Program expectedProgram = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("HELLO, WORLD"), null) 
                })
              });

        assertEquals("Check parsed program is as expected", expectedProgram, o);
    }
    
    @Test
    public void ShouldConvertProgramWithPrintCorrectly() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("HELLO WORLD"), new IntegerLiteral(1204)) 
                })});

        assertEquals("Check program text", "10 PRINT @ 1204, \"HELLO WORLD\"\n", p.asBasic());
    }
    
    @Test
    public void ShouldConvertProgramWithPrintWithNoLocationCorrectly() throws Exception {
        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new PrintStatement(new StringLiteral("HELLO WORLD"), null) 
                })});

        assertEquals("Check program text", "10 PRINT \"HELLO WORLD\"\n", p.asBasic());
    }
}
