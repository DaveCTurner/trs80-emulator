package dct25.trs80.syntaxTree;

public class IntegerIdentifierExpression extends IntegerExpression {
    private Identifier _i;
    
    public IntegerIdentifierExpression(Identifier i) {
        super();
        _i = i;
    }

    public void visit(AbstractIntegerExpressionVisitor v) throws Exception {
        v.visitIntegerIdentifierExpression(this);
    }
    
    public String toString() {
        return _i.toString();
    }
}
