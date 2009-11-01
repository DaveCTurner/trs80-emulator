package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.SyntaxTreeVisitor;


public class SetStatementNameVisitor extends SyntaxTreeVisitor {

    private StatementNameGenerator _nameGenerator;
    public SetStatementNameVisitor(StatementNameGenerator nameGenerator) {
        _nameGenerator = nameGenerator;
    }
    
    private int m_currentStatementIndex;
    private LineNumber m_currentLineNumber;
    public void enterProgramLine(ProgramLine pl) throws Exception {
        m_currentLineNumber = pl.getLineNumber();
        m_currentStatementIndex = 0; 
    }

    public void leaveProgramLine(ProgramLine pl) throws Exception { 
        m_currentLineNumber = null;
    }
    
    protected void visitStatement(Statement s) {
        s.setName(_nameGenerator.getStatementName(m_currentLineNumber, m_currentStatementIndex));
        m_currentStatementIndex += 1;
    }
}
