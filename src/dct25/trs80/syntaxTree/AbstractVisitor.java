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

    public void visitForStatement(ForStatement fs) throws Exception {
        visitStatement(fs);
    }
    
    public void visitNextStatement(NextStatement ns) throws Exception {
        visitStatement(ns);
    }

    public void visitInputStatement(InputStatement is) throws Exception {
        visitStatement(is);
    }

    public void visitIfStatement(IfStatement is) throws Exception {
        visitStatement(is);
    }
}
