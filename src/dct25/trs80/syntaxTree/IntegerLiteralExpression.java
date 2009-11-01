package dct25.trs80.syntaxTree;

public class IntegerLiteralExpression extends IntegerExpression {
    private IntegerLiteral _i;
    
    public IntegerLiteralExpression(IntegerLiteral i) {
        super();
        _i = i;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitIntegerLiteralExpression(this);
    }
    
    public String toString() {
        return _i.toString();
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof IntegerLiteralExpression)) { return false; }
        
        IntegerLiteralExpression other = (IntegerLiteralExpression)o;
        if (null == this._i) {
            if (null != other._i) { return false; }
        } else {
            if (!this._i.equals(other._i)) { return false; }
        }
               
        return true;
    }
}
