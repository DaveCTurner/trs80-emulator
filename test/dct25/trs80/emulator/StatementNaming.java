package dct25.trs80.emulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;


public class StatementNaming {

    @Test
    public void shouldNameStatements() throws Exception {
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
        
        StatementNameGenerator sng = new StatementNameGenerator();
        myProgram.visit(new SetStatementNameVisitor(sng));
        
        assertEquals("Check s10 name", sng.getStatementName(l10, 0), s10.getName());
        assertEquals("Check s11 name", sng.getStatementName(l10, 1), s11.getName());
        assertEquals("Check s20 name", sng.getStatementName(l20, 0), s20.getName());
        assertEquals("Check s21 name", sng.getStatementName(l20, 1), s21.getName());
        assertEquals("Check s22 name", sng.getStatementName(l20, 2), s22.getName());
    }
}
