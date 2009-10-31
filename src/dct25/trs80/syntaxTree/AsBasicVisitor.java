package dct25.trs80.syntaxTree;

import java.io.Writer;

public class AsBasicVisitor implements Visitor {

    private Writer _out;
    
    public AsBasicVisitor(Writer out) {
        _out = out;
    }
    
    public void enterProgram(Program p) {
        // Do nothing
    }

    private int _statementIndexInCurrentLine;
    public void enterProgramLine(ProgramLine pl) throws Exception {
        _out.write(pl.getLineNumber().toString());
        _statementIndexInCurrentLine = 0;
    }

    public void leaveProgram(Program p) {
        // Do nothing
    }

    public void leaveProgramLine(ProgramLine pl) throws Exception {
        _out.write("\n");
    }
    
    private void startStatement(Statement s) throws Exception {
        _statementIndexInCurrentLine += 1;
        if (_statementIndexInCurrentLine == 1) {
            _out.write(" ");
        } else {
            _out.write(" : ");
        }
    }

    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception {
        startStatement(cls);
        _out.write("CLS");
    }

    public void visitGotoStatement(GotoStatement gs) throws Exception {
        startStatement(gs);
        _out.write("GOTO " + gs.getTarget().toString());
    }

    public void visitEndStatement(EndStatement es) throws Exception {
        startStatement(es);
        _out.write("END");
    }
}
