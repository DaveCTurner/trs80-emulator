package dct25.trs80.syntaxTree;

public class RandomNumberExpression extends IntegerExpression {
    private IntegerExpression _i;
    
    public RandomNumberExpression(IntegerExpression i) {
        super();
        _i = i;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterRandomNumberExpression(this);
        _i.visit(v);
        v.leaveRandomNumberExpression(this);
    }
    
    public String toString() {
        return _i.toString();
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof RandomNumberExpression)) { return false; }
        
        RandomNumberExpression other = (RandomNumberExpression)o;
        if (null == this._i) {
            if (null != other._i) { return false; }
        } else {
            if (!this._i.equals(other._i)) { return false; }
        }
               
        return true;
    }
}
