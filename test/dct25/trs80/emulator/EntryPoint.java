package dct25.trs80.emulator;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;


public class EntryPoint {

    @Test
    public void shouldSetEntryPoint() throws Exception {
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
        
        myProgram.visit(new SetEntryPointVisitor());
        Statement entryPoint = myProgram.getEntryPoint();
        
        assertSame(s10, entryPoint);
        assertNotSame(s11, entryPoint);
        assertNotSame(s20, entryPoint);
        assertNotSame(s21, entryPoint);
        assertNotSame(s22, entryPoint);
    }
}
