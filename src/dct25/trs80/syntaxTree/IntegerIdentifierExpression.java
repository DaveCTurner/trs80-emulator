package dct25.trs80.syntaxTree;

public class IntegerIdentifierExpression extends IntegerExpression {
    private Identifier _i;
    
    public IntegerIdentifierExpression(Identifier i) {
        super();
        _i = i;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitIntegerIdentifierExpression(this);
    }
    
    public String toString() {
        return _i.toString();
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof IntegerIdentifierExpression)) { return false; }
        
        IntegerIdentifierExpression other = (IntegerIdentifierExpression)o;
        if (null == this._i) {
            if (null != other._i) { return false; }
        } else {
            if (!this._i.equals(other._i)) { return false; }
        }
               
        return true;
    }
}
