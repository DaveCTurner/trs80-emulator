package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.OnGotoStatement;
import dct25.trs80.syntaxTree.SyntaxTreeVisitor;
import dct25.trs80.syntaxTree.EndStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.Statement;

public class SetNextStatementVisitor extends SyntaxTreeVisitor {
    private Statement _previousStatement;
    
    public void visitGotoStatement(GotoStatement s) {
        visitStatement(s);
        _previousStatement = null; // No fall-through.
    }
    
    public void enterOnGotoStatement(OnGotoStatement s) {
        visitStatement(s);
        _previousStatement = null; // No fall-through.
    }

    public void visitEndStatement(EndStatement s) {
        visitStatement(s);
        _previousStatement = null; // No fall-through.
    }

    protected void visitStatement(Statement s) {
        if (null != _previousStatement) { _previousStatement.setNextStatement(s); }
        _previousStatement = s;
    }
}
