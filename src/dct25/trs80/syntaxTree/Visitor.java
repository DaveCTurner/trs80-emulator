package dct25.trs80.syntaxTree;

public interface Visitor {
    public void enterProgram(Program p) throws Exception;
    public void leaveProgram(Program p) throws Exception;
    public void enterProgramLine(ProgramLine pl) throws Exception;
    public void leaveProgramLine(ProgramLine pl) throws Exception;
    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception;
    public void visitGotoStatement(GotoStatement gs) throws Exception;
    public void visitEndStatement(EndStatement es) throws Exception;
    public void visitForStatement(ForStatement fs) throws Exception;
    public void visitNextStatement(NextStatement ns) throws Exception;
    public void visitPrintStatement(PrintStatement ps) throws Exception;
    public void visitInputStatement(InputStatement is) throws Exception;
}
