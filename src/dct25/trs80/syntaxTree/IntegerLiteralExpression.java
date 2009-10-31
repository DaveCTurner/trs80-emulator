package dct25.trs80.syntaxTree;

public class IntegerLiteralExpression extends IntegerExpression {
    private IntegerLiteral _i;
    
    public IntegerLiteralExpression(IntegerLiteral i) {
        super();
        _i = i;
    }

    public void visit(AbstractIntegerExpressionVisitor v) throws Exception {
        v.visitIntegerLiteralExpression(this);
    }
    
    public String toString() {
        return _i.toString();
    }
}
