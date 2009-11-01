package dct25.trs80.syntaxTree;

import java.io.Writer;

public class AsBasicVisitor extends SyntaxTreeVisitor {

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
        IntegerLiteral position = ps.getPosition();
        if (null == position) {
            _out.write("PRINT " + ps.getText());
        } else {
            _out.write("PRINT @ " + position + ", " + ps.getText());
        }
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

    public void enterForStatement(ForStatement fs) throws Exception {
        startStatement(fs);
        _out.write("FOR ");
        Identifier currentLoopVariable = fs.getLoopVariableIdentifier();
        _out.write(currentLoopVariable.toString());
        _out.write(" = ");
    }
    
    public void visitedLowerBoundInForStatement(ForStatement fs) throws Exception {
        _out.write(" TO ");
    }
    
    public void visitInputStatement(InputStatement is) throws Exception {
        startStatement(is);
        _out.write("INPUT " + is.getPrompt() + "; ");
        _out.write(is.getIdentifier1() + "," + is.getIdentifier2());
    }
    
    public void enterIfStatement(IfStatement is) throws Exception {
        startStatement(is);
        _out.write("IF ");
    }
    
    public void leaveIfStatement(IfStatement is) throws Exception {
        _out.write(" THEN " + is.getTarget().toString());
    }
    
    public void enterConjunction(Conjunction c) throws Exception {
        openParenthesis();
    }

    public void visitedLeftOperandOfConjunction(Conjunction c) throws Exception {
        _out.write(" AND ");
    }
    
    public void leaveConjunction(Conjunction c) throws Exception {
        closeParenthesis();
    }
    
    public void enterNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        openParenthesis();
    }
    
    public void visitedLeftOperandOfNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        _out.write("<>");
    }
    
    public void leaveNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        closeParenthesis();
    }
    
    private void openParenthesis() throws Exception {
        _out.write("(");
    }

    private void closeParenthesis() throws Exception {
        _out.write(")");
    }
    
    public void visitIntegerIdentifierExpression(IntegerIdentifierExpression ii) throws Exception {
        openParenthesis();
        _out.write(ii.toString());
        closeParenthesis();
    }
    
    public void visitIntegerLiteralExpression(IntegerLiteralExpression i) throws Exception {
        openParenthesis();
        _out.write(i.toString());
        closeParenthesis();
    }
    
    public void enterDimStatement(DimStatement ds) throws Exception {
        startStatement(ds);
        _out.write("DIM ");
    }
    
    public void visitingDimStatementArray(DimStatement ds, int arrayNumber) throws Exception {
        if (0 != arrayNumber) {
            _out.write(", ");
        }
    }
    
    public void enterArrayElement(ArrayElement ae) throws Exception {
        _out.write(ae.getIdentifier().toString());
        _out.write("(");
    }
    
    public void visitingArrayElementSubscript(ArrayElement ae, int dimensionIndex) throws Exception {
        if (0 != dimensionIndex) {
            _out.write(",");
        }
    }

    public void leaveArrayElement(ArrayElement ae) throws Exception {
        _out.write(")");
    }
}
