package dct25.trs80.syntaxTree;

public class Conjunction extends BooleanExpression {
    private BooleanExpression _i1, _i2;
    
    public Conjunction(BooleanExpression i1, BooleanExpression i2) {
        super();
        _i1 = i1;
        _i2 = i2;
    }

    public void visit(AbstractBooleanExpressionVisitor v) throws Exception {
        v.enterConjunction(this);
        _i1.visit(v);
        v.visitConjunction(this);
        _i2.visit(v);
        v.leaveConjunction(this);
    }
    
    public BooleanExpression getLeftExpression() { return _i1; }
    public BooleanExpression getRightExpression() { return _i2; }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof Conjunction)) { return false; }
        
        Conjunction other = (Conjunction)o;
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
