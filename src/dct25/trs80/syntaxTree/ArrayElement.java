/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 * 
 */
public class ArrayElement extends beaver.Symbol {
    Identifier _identifier;

    IntegerExpression[] _subscripts;

    public ArrayElement(Identifier identifier, IntegerExpression subscript1, IntegerExpression subscript2) {
        super();
        _identifier = identifier;
        _subscripts = new IntegerExpression[] { subscript1, subscript2 };
    }

    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ArrayElement)) { return false; }

        ArrayElement other = (ArrayElement) o;
        if (null == this._identifier) {
            if (null != other._identifier) { return false; }
        } else {
            if (!this._identifier.equals(other._identifier)) { return false; }
        }
        for (int i = 0; i < _subscripts.length; i++) {
            if (null == this._subscripts[i]) {
                if (null != other._subscripts[i]) { return false; }
            } else {
                if (!this._subscripts[i].equals(other._subscripts[i])) { return false; }
            }
        }

        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterArrayElement(this);
        for (int i = 0; i < _subscripts.length; i++) {
            v.visitingArrayElementSubscript(this, i);
            _subscripts[i].visit(v);
            v.visitedArrayElementSubscript(this, i);
        }
        v.leaveArrayElement(this);
    }

    public Identifier getIdentifier() {
        return _identifier;
    }

    public IntegerExpression getSubscript(int dimensionIndex) {
        return _subscripts[dimensionIndex];
    }
}
