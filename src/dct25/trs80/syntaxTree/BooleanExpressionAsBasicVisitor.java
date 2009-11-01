package dct25.trs80.syntaxTree;

import java.io.Writer;

public class BooleanExpressionAsBasicVisitor extends
        AbstractBooleanExpressionVisitor {
    
    private Writer _out;
    
    public BooleanExpressionAsBasicVisitor(Writer out) {
        _out = out;
        setIntegerExpressionVisitor(new IntegerExpressionAsBasicVisitor(_out));
    }

    private void openParenthesis() throws Exception {
        _out.write("(");
    }

    private void closeParenthesis() throws Exception {
        _out.write(")");
    }
    
    public void enterConjunction(Conjunction c) throws Exception {
        openParenthesis();
    }

    public void visitConjunction(Conjunction c) throws Exception {
        _out.write(" AND ");
    }
    
    public void leaveConjunction(Conjunction c) throws Exception {
        closeParenthesis();
    }

    public void visitNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        openParenthesis();
        ne.getLeftExpression().visit(getIntegerExpressionVisitor());
        _out.write("<>");
        ne.getRightExpression().visit(getIntegerExpressionVisitor());
        closeParenthesis();
    }
}
