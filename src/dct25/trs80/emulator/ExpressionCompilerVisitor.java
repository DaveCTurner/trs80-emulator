package dct25.trs80.emulator;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;

import dct25.trs80.syntaxTree.AbstractIntegerExpressionVisitor;
import dct25.trs80.syntaxTree.IntegerIdentifierExpression;
import dct25.trs80.syntaxTree.IntegerLiteralExpression;

public class ExpressionCompilerVisitor extends AbstractIntegerExpressionVisitor {
    private AST _ast;
    public ExpressionCompilerVisitor(AST ast) {
        _ast = ast;
    }
    
    private Expression _currentExpression;
    
    public void visitIntegerLiteralExpression(IntegerLiteralExpression i) {
        _currentExpression = _ast.newNumberLiteral(i.toString());
    }

    public void visitIntegerIdentifierExpression(IntegerIdentifierExpression ii) {
        _currentExpression = _ast.newName(ii.toString());
    }
    
    public Expression getExpression() { return _currentExpression; }
}
