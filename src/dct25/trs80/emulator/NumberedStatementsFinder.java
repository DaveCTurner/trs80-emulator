package dct25.trs80.emulator;

import java.util.HashMap;

import dct25.trs80.syntaxTree.AbstractVisitor;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;

public class NumberedStatementsFinder extends AbstractVisitor {

    private HashMap<LineNumber, Statement> _numberedStatements;
    private LineNumber _entryPoint;
    
    public LineNumber getEntryLine() { return _entryPoint; }
    public Statement getNumberedStatement(LineNumber lineNumber) { return _numberedStatements.get(lineNumber); }
    
    public void enterProgram(Program p) throws Exception {
        _entryPoint = null;
        _numberedStatements = new HashMap<LineNumber, Statement>();
    }

    private LineNumber _currentLine;
    public void enterProgramLine(ProgramLine pl) {
        if (null == _entryPoint) {
            _entryPoint = pl.getLineNumber();
        }
        _currentLine = pl.getLineNumber(); 
    }

    protected void visitStatement(Statement s) {
        if (null != _currentLine) {
            _numberedStatements.put(_currentLine, s);
            _currentLine = null;
        }
    }
}
