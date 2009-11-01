package dct25.trs80.syntaxTree;

public class ArrayElementExpression extends IntegerExpression {
    private ArrayElement _ae;
    
    public ArrayElementExpression(ArrayElement ae) {
        super();
        _ae = ae;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterArrayElementExpression(this);
        _ae.visit(v);
        v.leaveArrayElementExpression(this);
    }
    
    public String toString() {
        return _ae.toString();
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ArrayElementExpression)) { return false; }
        
        ArrayElementExpression other = (ArrayElementExpression)o;
        if (null == this._ae) {
            if (null != other._ae) { return false; }
        } else {
            if (!this._ae.equals(other._ae)) { return false; }
        }
               
        return true;
    }
}
