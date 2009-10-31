package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.GotoStatement;
import dct25.trs80.syntaxTree.Program;
import dct25.trs80.syntaxTree.ProgramLine;
import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.Visitor;

public class SetEntryPointVisitor implements Visitor {

    private Statement _entryPoint; 
    
    public void enterProgram(Program p) throws Exception {
        _entryPoint = null;
    }

    public void enterProgramLine(ProgramLine pl) throws Exception { }

    public void leaveProgram(Program p) throws Exception {
        p.setEntryPoint(_entryPoint);
    }

    public void leaveProgramLine(ProgramLine pl) throws Exception { }

    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception {
        visitStatement(cls);
    }

    public void visitGotoStatement(GotoStatement gs) throws Exception {
        visitStatement(gs);
    }

    private void visitStatement(Statement s) {
        if (null == _entryPoint) { _entryPoint = s; }
    }
}
