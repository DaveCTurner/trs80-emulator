package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.Visitor;


public class SetStatementNameVisitor implements Visitor {

    private StatementNameGenerator _nameGenerator;
    public SetStatementNameVisitor(StatementNameGenerator nameGenerator) {
        _nameGenerator = nameGenerator;
    }
    
    public void enterProgram(Program p) throws Exception { /* Do nothing */ }

    private int m_currentStatementIndex;
    private LineNumber m_currentLineNumber;
    public void enterProgramLine(ProgramLine pl) throws Exception {
        m_currentLineNumber = pl.getLineNumber();
        m_currentStatementIndex = 0; 
    }

    public void leaveProgram(Program p) throws Exception { /* Do nothing */ }

    public void leaveProgramLine(ProgramLine pl) throws Exception { 
        m_currentLineNumber = null;
    }
    
    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception { 
        visitStatement(cls);
    }

    public void visitGotoStatement(GotoStatement gs) throws Exception {
        visitStatement(gs);
    }

    private void visitStatement(Statement s) {
        s.setName(_nameGenerator.getStatementName(m_currentLineNumber, m_currentStatementIndex));
        m_currentStatementIndex += 1;
    }
}
