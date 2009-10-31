package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.AbstractVisitor;
import dct25.trs80.syntaxTree.Statement;

public class SetNextStatementVisitor extends AbstractVisitor {
    private Statement _previousStatement;
    
    protected void visitStatement(Statement s) {
        if (null != _previousStatement) { _previousStatement.setNextStatement(s); }
        _previousStatement = s;
    }
}
