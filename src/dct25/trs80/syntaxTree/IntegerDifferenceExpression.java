package dct25.trs80.syntaxTree;

public class IntegerDifferenceExpression extends IntegerExpression {
    private IntegerExpression _i1, _i2;
    
    public IntegerDifferenceExpression(IntegerExpression i1, IntegerExpression i2) {
        super();
        _i1 = i1;
        _i2 = i2;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterIntegerDifferenceExpression(this);
        _i1.visit(v);
        v.visitedLeftOperandOfIntegerDifference(this);
        _i2.visit(v);
        v.visitedRightOperandOfIntegerDifference(this);
        v.leaveIntegerDifferenceExpression(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof IntegerDifferenceExpression)) { return false; }
        
        IntegerDifferenceExpression other = (IntegerDifferenceExpression)o;
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
