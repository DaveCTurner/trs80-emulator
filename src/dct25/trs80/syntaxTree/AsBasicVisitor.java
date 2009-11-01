package dct25.trs80.syntaxTree;

import java.io.Writer;

public class AsBasicVisitor extends AbstractVisitor {

    private Writer _out;
    
    public AsBasicVisitor(Writer out) {
        _out = out;
    }
    
    private int _statementIndexInCurrentLine;
    public void enterProgramLine(ProgramLine pl) throws Exception {
        _out.write(pl.getLineNumber().toString());
        _statementIndexInCurrentLine = 0;
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

    public void visitPrintStatement(PrintStatement ps) throws Exception {
        startStatement(ps);
        _out.write("PRINT @ " + ps.getPosition() + ", " + ps.getText());
    }

    public void visitNextStatement(NextStatement ns) throws Exception {
        startStatement(ns);
        Identifier currentLoopVariable = ns.getLoopIdentifier();
        if (null == currentLoopVariable) {
            _out.write("NEXT");
        } else {
            _out.write("NEXT ");
            _out.write(currentLoopVariable.toString());
        }
    }

    public void visitForStatement(ForStatement fs) throws Exception {
        startStatement(fs);
        _out.write("FOR ");
        Identifier currentLoopVariable = fs.getLoopVariableIdentifier();
        _out.write(currentLoopVariable.toString());
        _out.write(" = ");
        AbstractIntegerExpressionVisitor v = new IntegerExpressionAsBasicVisitor(_out);
        fs.getLowerBound().visit(v);
        _out.write(" TO ");
        fs.getUpperBound().visit(v);
    }
    
    public void visitInputStatement(InputStatement is) throws Exception {
        startStatement(is);
        _out.write("INPUT " + is.getPrompt() + "; ");
        _out.write(is.getIdentifier1() + "," + is.getIdentifier2());
    }
    
    public void visitIfStatement(IfStatement is) throws Exception {
        startStatement(is);
        _out.write("IF ");
        AbstractBooleanExpressionVisitor v = new BooleanExpressionAsBasicVisitor(_out);
        is.getCondition().visit(v);
        _out.write(" THEN " + is.getTarget().toString());
    }
}
