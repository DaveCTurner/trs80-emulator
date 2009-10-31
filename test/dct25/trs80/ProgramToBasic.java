package dct25.trs80;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class ProgramToBasic {

    @Test
    public void ShouldConvertCLSToBasicCorrectly() throws Exception {

        Program p = new Program(
                new ProgramLine[] { new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement()
                })});

        assertEquals("Check program text", "10 CLS\n", p.asBasic());
    }
    
    @Test
    public void ShouldConvertCLSx2ToBasicCorrectly() throws Exception {

        Program p = new Program(
                new ProgramLine[] { new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement(),
                    new ClearScreenStatement()
                })});

        assertEquals("Check program text", "10 CLS : CLS\n", p.asBasic());
    }
    
    
    @Test
    public void ShouldConvertMultiLineProgramsToBasicCorrectly() throws Exception {

        Program p = new Program(new ProgramLine[] {
                new ProgramLine(new LineNumber(10), new Statement[] {
                    new ClearScreenStatement(),
                    new ClearScreenStatement()
                }), new ProgramLine(new LineNumber(20), new Statement[] {
                    new ClearScreenStatement(),
                    new ClearScreenStatement(),
                    new ClearScreenStatement()
                })});

        assertEquals("Check program text", "10 CLS : CLS\n20 CLS : CLS : CLS\n", p.asBasic());
    }
    
    

}
