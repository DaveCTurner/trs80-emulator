package dct25.trs80.syntaxTree;

public class NotEqualsExpression extends BooleanExpression {
    private IntegerExpression _i1, _i2;
    
    public NotEqualsExpression(IntegerExpression i1, IntegerExpression i2) {
        super();
        _i1 = i1;
        _i2 = i2;
    }

    public void visit(AbstractBooleanExpressionVisitor v) throws Exception {
        v.visitNotEqualsExpression(this);
    }
    
    public IntegerExpression getLeftExpression() { return _i1; }
    public IntegerExpression getRightExpression() { return _i2; }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof NotEqualsExpression)) { return false; }
        
        NotEqualsExpression other = (NotEqualsExpression)o;
        if (null == this._i1) {
            if (null != other._i1) { return false; }
        } else {
            if (!this._i1.equals(other._i1)) { return false; }
        }
        if (null == this._i2) {
            if (null != other._i2) { return false; }
        } else {
            if (!this._i2.equals(other._i2)) { return false; }
        }
               
        return true;
    }
}
