package dct25.trs80.emulator;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;


public class NextStatements {

    @Test
    public void shouldSetNextStatements() throws Exception {
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
        
        myProgram.visit(new SetNextStatementVisitor());
        
        assertSame("Check s10 next statement", s11, s10.getNextStatement());
        assertSame("Check s11 next statement", s20, s11.getNextStatement());
        assertSame("Check s20 next statement", s21, s20.getNextStatement());
        assertSame("Check s21 next statement", s22, s21.getNextStatement());
        assertSame("Check s22 next statement", null, s22.getNextStatement());
    }
}
