package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.AbstractVisitor;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.Statement;

public class SetEntryPointVisitor extends AbstractVisitor {

    private Statement _entryPoint; 
    
    public void enterProgram(Program p) throws Exception {
        _entryPoint = null;
    }

    public void leaveProgram(Program p) throws Exception {
        p.setEntryPoint(_entryPoint);
    }

    protected void visitStatement(Statement s) {
        if (null == _entryPoint) { _entryPoint = s; }
    }
}
