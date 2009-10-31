package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;


public class NumberedStatements {

    @Test
    public void shouldSetNumberedStatements() throws Exception {
        LineNumber l10 = new LineNumber(10);
        LineNumber l20 = new LineNumber(20);
        
        Statement s10 = new ClearScreenStatement();
        Statement s11 = new GotoStatement(l20);
        Statement s20 = new ClearScreenStatement();
        Statement s21 = new ClearScreenStatement();
        Statement s22 = new GotoStatement(l10);
        
        Program myProgram = new Program(
                new ProgramLine[] {
                        new ProgramLine(l10, new Statement[] { s10, s11 }),
                        new ProgramLine(l20, new Statement[] { s20, s21, s22 })
                });
        
        NumberedStatementsFinder finder = new NumberedStatementsFinder();
        myProgram.visit(finder);
        
        assertEquals("Check entry line", l10, finder.getEntryLine());
        assertSame("Check line 10", s10, finder.getNumberedStatement(l10));
        assertSame("Check line 20", s20, finder.getNumberedStatement(l20));
        assertSame("Check line 20 again", s20, finder.getNumberedStatement(new LineNumber(20)));
        assertSame("Check nonexistent line 30", null, finder.getNumberedStatement(new LineNumber(30)));
    }
}
