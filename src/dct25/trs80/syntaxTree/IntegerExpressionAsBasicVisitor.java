package dct25.trs80.syntaxTree;

import java.io.Writer;

public class IntegerExpressionAsBasicVisitor extends
        AbstractIntegerExpressionVisitor {
    
    private Writer _out;
    
    public IntegerExpressionAsBasicVisitor(Writer out) {
        _out = out;
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
}
