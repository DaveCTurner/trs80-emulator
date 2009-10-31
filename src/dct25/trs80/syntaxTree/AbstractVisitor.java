package dct25.trs80.syntaxTree;

public class AbstractVisitor implements Visitor {

    public void enterProgram(Program p) throws Exception { }

    public void enterProgramLine(ProgramLine pl) throws Exception { }

    public void leaveProgram(Program p) throws Exception { }

    public void leaveProgramLine(ProgramLine pl) throws Exception { }

    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception {
        visitStatement(cls);
    }

    public void visitGotoStatement(GotoStatement gs) throws Exception {
        visitStatement(gs);
    }
    
    protected void visitStatement(Statement s) throws Exception { }

    public void visitEndStatement(EndStatement es) throws Exception { 
        visitStatement(es);
    }

    public void visitPrintStatement(PrintStatement ps) throws Exception {
        visitStatement(ps);
    }
}
